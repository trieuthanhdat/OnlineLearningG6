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
@WebServlet(name = "UpdateLessonDetailsServlet", urlPatterns = {"/UpdateLessonDetailsServlet"})
public class UpdateLessonDetailsServlet extends HttpServlet {

    private final String ERROR_PAGE = "LessonDetails";
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
            int lessonID = Integer.parseInt(request.getParameter("txtLessonID"));
            int currSubID = Integer.parseInt(request.getParameter("txtSubjectID"));
            
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO currUser = (UserDTO)session.getAttribute("CURRENT_USER");
                if (currUser != null) {
                    if (currUser.getRole().equals("admin") || currUser.getRole().equals("expert")) {
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
                        
                        session.setAttribute("UPDATE_LESSON_INFO", new LessonDTO(lessonID, currSubID, lessonName, order, lessonType, topicID, true, 
                                                    new LessonDetailsDTO(quizID, videoLink, htmlContent)));
                        LessonDAO dao = new LessonDAO();
                        LessonDTO currLesson = dao.getLessonByID(lessonID);
                        int oldTopicID = currLesson.getTopicID();
                        
                        boolean checkResult = dao.checkValidOrderForUpdating(currSubID, order, oldTopicID, topicID);
                        log("Update Lesson Servlet: checkResult = " + checkResult);
                        if (checkResult) {                            
                            int oldOrder = currLesson.getOrder();

                            LessonDetailsDTO details = new LessonDetailsDTO(quizID, videoLink, htmlContent);
                            LessonDTO updateLesson = new LessonDTO(lessonID, currSubID, lessonName, order, lessonType, topicID, currLesson.isStatus(), details);
                            boolean result = dao.updateLesson(updateLesson, oldOrder, oldTopicID);
                            log("Update Lesson Servlet: result = " + result);
                            if (result) {
                                url = RESULT_PAGE;
                                session.removeAttribute("UPDATE_LESSON_INFO");
                            }
                        }                        
                    }
                }
            }
            if (url.equals(ERROR_PAGE)) {
                url += "?txtSubjectID=" + currSubID + "&txtLessonID=" + lessonID;
            } else url += "?txtSubjectID=" + currSubID;
        } catch (NamingException | SQLException ex) {
            log(ex.toString());
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
