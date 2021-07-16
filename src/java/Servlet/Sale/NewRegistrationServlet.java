/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Sale;

import DAO.Lesson.LessonDAO;
import DAO.SubjectRegistration.MyRegistrationDAO;
import DAO.SubjectRegistration.PackageDAO;
import Temp.UsersDAO;
import Temp.TrackingProgressDAO;
import DTO.Lesson.LessonDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author DELL
 */
@WebServlet(name = "NewRegistrationServlet", urlPatterns = {"/NewRegistrationServlet"})
public class NewRegistrationServlet extends HttpServlet {
private final String MY_REGISTRATION = "DispatchServlet?btAction=View Registration";
private final String CREATE_NEW_USER = "createNewUser.jsp";
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
        
        String userEmail = request.getParameter("txtUserEmail");
        String subjectID= request.getParameter("txtSubjectID");
        String validFrom = request.getParameter("txtValidFrom");
        String validTo = request.getParameter("txtValidTo");
        String pack = request.getParameter("txtPackageID");
        String value = request.getParameter("subjectID");
        request.setAttribute("subjectID", value);
        String url = MY_REGISTRATION;
        try {
            String userid;
            //check if email is exist
            PackageDAO pkgdao = new PackageDAO();
            MyRegistrationDAO dao = new MyRegistrationDAO();
            UsersDAO udao = new UsersDAO();
            boolean exist = udao.checkExistUserEmail(userEmail);//true if email exist
            if(exist && validFrom !=null && validTo !=null){
            //new co ton tai thi convert cac thong tin can thiet roi thuc hien create.
                userid = udao.getUserID(userEmail);
                int subjectid = Integer.parseInt(subjectID);
                int packageID = Integer.parseInt(pack);
                Date from = Date.valueOf(validFrom);
                Date to = Date.valueOf(validTo);
                float  totalCost = pkgdao.getSalePriceID(packageID);
            //lấy ngày hiện tại set cho registration time
                long millis = System.currentTimeMillis(); 
                java.sql.Date registrationTime = new java.sql.Date(millis);
                HttpSession session = request.getSession();
                String updateBy=(String)session.getAttribute("email");  
                boolean status = true;
                boolean result = dao.createNewRegistration(userid, subjectid, packageID, 
                        from, to, totalCost, registrationTime, updateBy, status); 
            //neu doan code tren bi bat loi  co the la do packageID phai tu add vao
                if(result){
                    //Create Deadline Cho Subject dựa vào Access Duration
                    //lay lesson list type là quiz
                    LessonDAO lessondao = new LessonDAO();
                    PackageDAO packagedao = new PackageDAO();
                    ArrayList<LessonDTO> lessondto = lessondao.getLessonIDbySubjectID(subjectid);
                    if(lessondto.size() > 0){
                    //deadline sẽ lấy duration từ pricepackage 
                    double accessduration = packagedao.getAccessDuration(packageID);
                    double numberofQuiz = lessondto.size();
                    long daystodeadline = (long) (((accessduration / numberofQuiz) * 30) * 86400000);
                    log("Size of lesson: "+lessondto.size());
                    log("Days To Deadline : "+daystodeadline);
                    //lấy ra ngày hôm nay + số ngày tới deadline = deadline
                    long millis1  = System.currentTimeMillis() +  daystodeadline;
                    log("Millis1: "+millis1);
                    java.sql.Date deadline = new java.sql.Date(millis1);
                    log("Deadline : "+deadline);    
                    //----------------------------------------------------------------
                    TrackingProgressDAO trackingdao = new TrackingProgressDAO();
                    trackingdao.addnewTrackingProgress(packageID, subjectid, deadline, false);
                }
                    //---------------------------------------------------
                    url = MY_REGISTRATION;
                }else{
            //cai nay de bat loi package id ko the tu them dc se ko create new thanh cong
                    url= "index.html";
                }
            }
            else{
            //new email ko ton tai thi  chuyen sang trang create new user
                url = CREATE_NEW_USER;
            }//end  Registration
        } catch (SQLException ex) {
            log("Check NewRegistrationServlet SQLException - " + ex.getMessage());
        } catch (NamingException ne) {
            log("Check NewRegistrationServlet NamingException - " + ne.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
