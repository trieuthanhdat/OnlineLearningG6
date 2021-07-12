package listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PathListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        HashMap<String, String> conversionMap = new HashMap<>();
        
        ServletContext context = sce.getServletContext();
        String convFilePath = context.getRealPath("/WEB-INF/pathConversion.txt");
       
        try {
            File f = new File(convFilePath);
            if (f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);

                String currLine;
                while ((currLine = bf.readLine()) != null) {
                    // delimiter trong file là "="
                    int delimiterIndex = currLine.indexOf("=");
                    // đọc từ file được lưu dưới dạng (rút_gọn=dài)
                    // lưu vào map: key = rút_gọn, value = dài.
                    if (delimiterIndex == 0) {
                        conversionMap.put("", currLine.substring(delimiterIndex + 1));
                    } else {
                        conversionMap.put(currLine.substring(0, delimiterIndex), 
                                          currLine.substring(delimiterIndex + 1));
                    }
                }
                bf.close(); fr.close();
                
//                for (String key : conversionMap.keySet()) {
//                    System.out.println(key + " - " + conversionMap.get(key));
//                }
            }
            if (conversionMap.isEmpty()) {
                throw new Exception("Empty conversion file 'pathConversion.txt'.");               
            }
            else {
                context.setAttribute("CONVERSION_MAP", conversionMap);
            }
            
        } catch (Throwable t) {
            System.out.println("PathListener: " + t.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
