/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group6.controller;

import group6.entity.lesson.LessonDAO;
import group6.entity.lesson.LessonDTO;
import group6.entity.lesson.LessonDetailsDTO;
import group6.entity.user.UserDTO;
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
@WebServlet(name = "AddNewLessonServlet", urlPatterns = {"/AddNewLessonServlet"})
public class AddNewLessonServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String INPUT_ERROR_PAGE = "NewLesson";
    private final String RESULT_PAGE = "ShowLessons";
    
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
                if (currUser != null) {
                    if (currUser.getRole().equals("admin") || currUser.getRole().equals("expert")) {
                        int currSubID = Integer.parseInt(request.getParameter("txtSubjectID"));
                        String lessonName = request.getParameter("txtLessonName");
                        String lessonType = request.getParameter("txtLessonType");
                        int order = Integer.parseInt(request.getParameter("txtOrder"));
                        int topicID = 0;
                        String videoLink = "";
                        String htmlContent = "";
                        int quizID = 0;
                        
                        if (lessonType.equals("Lesson")) {
                            topicID = Integer.parseInt(request.getParameter("txtTopicID"));
                            videoLink = request.getParameter("txtVideoLink");
                            if (videoLink == null) {
                                videoLink = "";
                            }
                            htmlContent = request.getParameter("txtHtmlContent");
                        } else if (lessonType.equals("Quiz")) {
                            quizID = Integer.parseInt("txtQuizID");                            
                        }
                        
                        log("SubjectID: " + currSubID + " - Order: " + order + " - TopicID: " + topicID);                        
                        LessonDAO dao = new LessonDAO();                        
                        boolean result = dao.checkValidOrderForAdding(currSubID, order, topicID);
                        log("Check valid order result: " + result);
                        if (result) {
                            LessonDetailsDTO details = new LessonDetailsDTO(quizID, videoLink, htmlContent);
                            LessonDTO newLesson = new LessonDTO(0, currSubID, lessonName, order, lessonType, topicID, true, details);
                            boolean addResult = dao.addNewLesson(newLesson);
                            log("Add new lesson result: " + addResult);
                            if (addResult) {
                                url = RESULT_PAGE + "?txtSubjectID=" + currSubID;
                            }
                        }
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.toString());
        } 
        response.sendRedirect(url);
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
