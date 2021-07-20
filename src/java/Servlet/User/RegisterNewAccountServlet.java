/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Mail;
import utils.NewAccount;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RegisterNewAccountServlet", urlPatterns = {"/RegisterNewAccountServlet"})
public class RegisterNewAccountServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String VALIDATION_PAGE = "AccountConfirmation";
    private final int VALIDATION_NUM_SIZE = 6;
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

        String url = WELCOME_PAGE;

        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String fullName = request.getParameter("txtFullName");

            boolean result = true;
            //check duplicated email in DB
            if (result) {
                String fileName = "NewAccount.txt";
                String filePath = getServletContext().getRealPath("WEB-INF" + File.separator + fileName);
                log("RegisterNewAccountServlet: " + filePath);

                File newAccountFile = new File(filePath);
                FileReader fr = new FileReader(newAccountFile);
                BufferedReader br = new BufferedReader(fr);

                List<NewAccount> pendingAccs = new ArrayList<>();
                NewAccount currAcc = null;
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.isEmpty()) {
                        String newAccountInfo[] = line.split("~");
                        String currEmail = newAccountInfo[0];
                        String currPassword = newAccountInfo[1];
                        String currFullname = newAccountInfo[2];
                        String validationNums = newAccountInfo[3];
                             LocalDateTime createTime = LocalDateTime.parse(newAccountInfo[4]);

                        currAcc = new NewAccount(currEmail, currPassword, currFullname, validationNums, createTime);
                        pendingAccs.add(currAcc);
                    }
                }
                br.close();
                fr.close();
                // remove expired pending new account
                Iterator<NewAccount> accIte = pendingAccs.iterator();
                boolean duplicateInFile = false;
                while (accIte.hasNext()) {
                    currAcc = accIte.next();
                    if (currAcc.getEmail().equals(email)) {
                        duplicateInFile = true;
                        //url = ?
                    }
                    // check if create time is invalid then remove pending accs (under 5 minutes since create pending new account)                    
                    ZonedDateTime zdt = currAcc.getCreateTime().atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
                    long createTimeInMilli = zdt.toInstant().toEpochMilli();
                    if ((System.currentTimeMillis() - createTimeInMilli) <= 0
                            && (System.currentTimeMillis() - createTimeInMilli) > AVAILABLE_TIME_LIMIT_FOR_NEW_ACCOUNT) {
                        accIte.remove();
                        log("Acc deleted");
                    }
                }
                // no duplicate in both file and database then add the new account to the list
                if (!duplicateInFile) {
                    Random rd = new Random();
                    String validationNums = "";
                    for (int i = 0; i < VALIDATION_NUM_SIZE; i++) {
                        validationNums += String.valueOf(rd.nextInt(10));
                    }
                   
                    NewAccount acc = new NewAccount(email, password, fullName, validationNums, LocalDateTime.now());
                    
                    pendingAccs.add(acc);
                    Mail.SendEmail(email, validationNums);

                    url = VALIDATION_PAGE;
                }

                // put the new account list back to the file
                FileOutputStream fos = new FileOutputStream(newAccountFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);

                for (NewAccount acc : pendingAccs) {
                    bw.write(acc.getEmail() + "~" + acc.getPassword() + "~" + acc.getFullName() + "~" + acc.getValidationNums()
                            + "~" + acc.getCreateTime().toString());
                    bw.newLine();
                }
                bw.close();
                osw.close();
                fos.close();
            }
            response.sendRedirect(url);
        } catch (MessagingException ex) {
            log(ex.toString());
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
