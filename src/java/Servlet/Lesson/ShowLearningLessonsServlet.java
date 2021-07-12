/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Lesson.LessonDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
 * @author ASUS
 */
@WebServlet(name = "ShowLearningLessonsServlet", urlPatterns = {"/ShowLearningLessonsServlet"})
public class ShowLearningLessonsServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String SHOW_CURRENT_LESSON_DETAILS = "ShowCurrentLessonServlet";
    
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
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO currUser = (UserDTO)session.getAttribute("CURRENT_USER");
                int subID = Integer.parseInt(request.getParameter("txtSubjectID"));
                log("SubjectID: " + subID);
                
                if (currUser != null) {
                    String currUserID = currUser.getUserID();
                    RegistrationDAO regDAO = new RegistrationDAO();
                    
                    //check if the subject is still enabled
                    boolean result = regDAO.checkRegistration(currUserID, subID);
                    log("Check registration result: " + result);
                    if (result) {
                        LessonDAO lessonDAO = new LessonDAO();
                        List<LessonDTO> lessonsList = lessonDAO.getCurrLessons(subID);
                        
                        request.setAttribute("CURRENT_LESSONS", lessonsList);
                        url = SHOW_CURRENT_LESSON_DETAILS;
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
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
