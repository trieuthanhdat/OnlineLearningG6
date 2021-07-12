/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DAO.Quiz.QuizDAO;
import DTO.Lesson.LessonDTO;
import DTO.Lesson.LessonDetailsDTO;
import DTO.Quiz.QuizDTO;
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
@WebServlet(name = "ShowUpdateLessonFormServlet", urlPatterns = {"/ShowUpdateLessonFormServlet"})
public class ShowUpdateLessonFormServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String RESULT_PAGE = "UpdateLessonDetails.jsp";
    
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
                        int currLessonID = Integer.parseInt(request.getParameter("txtLessonID"));
                        
                        LessonDAO lessonDAO = new LessonDAO();
                        List<LessonDTO> lessons = lessonDAO.getLessonsBySubjectID(currSubID);
                        boolean existLessonResult = false;
                        for (LessonDTO lesson : lessons) {
                            if (lesson.getLessonID() == currLessonID) {
                                existLessonResult = true;
                            }
                        }
                        
                        if (existLessonResult) {
                            QuizDAO quizDAO = new QuizDAO();
                            List<QuizDTO> quizzes = quizDAO.getQuizzesBySubjectID(currSubID);
                            List<LessonDTO> topics = lessonDAO.getTopicsBySubjectID(currSubID);
                            LessonDetailsDTO details = lessonDAO.getLessonDetailsByID(currLessonID);
                            LessonDTO currLesson = lessonDAO.getLessonByID(currLessonID);
                            currLesson.setDetails(details);
                            
                            request.setAttribute("QUIZZES_LIST", quizzes);
                            request.setAttribute("TOPICS_LIST", topics);
                            request.setAttribute("CURRENT_LESSON", currLesson);
                            url = RESULT_PAGE;
                        }
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.toString());
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
