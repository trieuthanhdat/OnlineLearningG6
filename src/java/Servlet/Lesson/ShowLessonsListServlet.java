/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group6.controller;

import group6.entity.lesson.LessonDAO;
import group6.entity.lesson.LessonDTO;
import group6.entity.subject.SubjectDAO;
import group6.entity.subject.SubjectDTO;
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
@WebServlet(name = "ShowLessonsListServlet", urlPatterns = {"/ShowLessonsListServlet"})
public class ShowLessonsListServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String LESSONS_LIST_PAGE = "LessonsList.jsp";
    
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
            // check if session still existed
            HttpSession session = request.getSession(false);
            if (session != null) {
                // check if user has logged in
                UserDTO currUser = (UserDTO)session.getAttribute("CURRENT_USER");
                if (currUser != null) {
                    int currSubID = Integer.parseInt(request.getParameter("txtSubjectID"));                    
                    SubjectDAO subDAO = new SubjectDAO();
                    SubjectDTO currSub = subDAO.getSubjectByID(currSubID);
                    
                    // if user is admin or the owner of that subject they can see the lessons list
                    if (currUser.getRole().equals("admin") || currSub.getOwnerID().equals(currUser.getUserID())) {
                        LessonDAO lessonDAO = new LessonDAO();
                        List<LessonDTO> lessonsList = lessonDAO.getLessonsBySubjectID(currSubID);
                        
                        LessonDTO updateLesson = (LessonDTO)session.getAttribute("UPDATE_LESSON_INFO");
                        if (updateLesson != null) session.removeAttribute("UPDATE_LESSON_INFO");
                        
                        request.setAttribute("LESSONS_LIST", lessonsList);
                        request.setAttribute("CURRENT_SUBJECT_ID", currSubID);
                        url = LESSONS_LIST_PAGE;
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
