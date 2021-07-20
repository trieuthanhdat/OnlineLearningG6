package Servlet.User;

import Temp.UsersDAO;
import DTO.User.UserDTO;
import DTO.User.UserProfileDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.NewAccount;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ValidateNewAccountServlet", urlPatterns = {"/ValidateNewAccountServlet"})
public class ValidateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "AccountConfirmation";
    private final String RESULT_PAGE = "CreateSuccessfully";
    private final long AVAILABLE_TIME_LIMIT_FOR_NEW_ACCOUNT = 300000; //300000 millisecs = 5 minutes

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR_PAGE;
        try {
            String email = request.getParameter("txtEmail");
            String checkNums = request.getParameter("txtValidationNums");

            // get all pending new accounts
            String fileName = "NewAccount.txt";
            String filePath = getServletContext().getRealPath("WEB-INF" + File.separator + fileName);
            log("NewAccountServlet: " + filePath);

            File newAccountFile = new File(filePath);
            FileReader fr = new FileReader(newAccountFile);
            BufferedReader br = new BufferedReader(fr);

            List<NewAccount> pendingAccs = new ArrayList<>();
            NewAccount currAcc = null;
            String line;
            //load file for pending accs
            while ((line = br.readLine()) != null) {
                
                if (!line.isEmpty()) {
                    String newAccountInfo[] = line.split("~");
                    String currEmail = newAccountInfo[0];
                    String currPassword = newAccountInfo[1];
                    String currFullName = newAccountInfo[2];
                    String validationNums = newAccountInfo[3];

                    LocalDateTime createTime = LocalDateTime.parse(newAccountInfo[4]);

                    currAcc = new NewAccount(currEmail, currPassword, currFullName, validationNums, createTime);
                    pendingAccs.add(currAcc);
                }
                log(currAcc.getEmail());
            }
            br.close();
            fr.close();

            //find account user try to validate
            Iterator<NewAccount> accIte = pendingAccs.iterator();
            boolean hasNewAcc = false;
            while (accIte.hasNext()) {
                currAcc = accIte.next();
                if (currAcc.getEmail().equals(email)) {
                    if (currAcc.getValidationNums().equals(checkNums)) {
                        // check if create time is valid (under 5 minutes since create pending new account)
                        ZonedDateTime zdt = currAcc.getCreateTime().atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
                        long createTimeInMilli = zdt.toInstant().toEpochMilli();
                        if ((System.currentTimeMillis() - createTimeInMilli) > 0
                                && (System.currentTimeMillis() - createTimeInMilli) <= AVAILABLE_TIME_LIMIT_FOR_NEW_ACCOUNT) {
                            // create new account
                            UsersDAO dao = new UsersDAO();
                            Date currDate = Date.valueOf(LocalDate.now());
                            UserDTO newUser = new UserDTO(currAcc.getEmail(), dao.getNextUserID(), "User", currAcc.getFullName(), currAcc.getPassword(), true, currDate);
                            UserProfileDTO newUserProfile = new UserProfileDTO(currAcc.getEmail(),null, null, null, null);
                            dao.createUserProfileDTO(newUserProfile.getEmail(), null, null, null, null);
                            dao.createNewUser(newUser.getEmail(), newUser.getUserID(), newUser.getRole(), newUser.getFullName(), newUser.getPassword(), true, currDate);
                            hasNewAcc = true;
                            accIte.remove();
                            url = RESULT_PAGE;
                        }
                    } else {
                        url = ERROR_PAGE;
                    }
                    log(currAcc.getEmail()+" Found in file");
                }
                
            }
            // if the new pending acc is created as a user update the pending new account file
            if (hasNewAcc) {
                FileOutputStream fos = new FileOutputStream(newAccountFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                for (NewAccount acc : pendingAccs) {
                    bw.write(acc.getEmail() + "~" + acc.getPassword() + "~"+acc.getFullName()+"~" + acc.getValidationNums() + "~" + acc.getCreateTime().toString());
                   
                    bw.newLine();
                }
                bw.close();
                osw.close();
                fos.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
