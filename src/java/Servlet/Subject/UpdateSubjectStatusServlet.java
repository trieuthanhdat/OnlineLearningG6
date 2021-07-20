/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Subject;

import DAO.Lesson.LessonDAO;
import DAO.Subject.SubjectDAO;
import DTO.Subject.SubjectDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
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
@WebServlet(name = "UpdateSubjectStatusServlet", urlPatterns = {"/UpdateSubjectStatusServlet"})
public class UpdateSubjectStatusServlet extends HttpServlet {
    
    private final String ERROR_PAGE = "ShowSubjects";
    private final String RESULT_PAGE = "ShowSubjects";
    
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
        HttpSession session = request.getSession(false);
        
        try {
            if (session != null) {
                String paramSubjectID = request.getParameter("txtSubjectID");
                
                if (paramSubjectID != null) {
                    int subjectID = Integer.parseInt(paramSubjectID);
                    SubjectDAO subDAO = new SubjectDAO();
                    SubjectDTO currSubject = subDAO.getSubjectByID(subjectID);
                    LessonDAO lessonDAO = new LessonDAO();                    
                    
                    if (currSubject.getNumOfLessons() != 0 && 
                            lessonDAO.checkSubjectHasValidLessons(subjectID)) {
                        boolean result = subDAO.updateSubjectStatus(subjectID);                    
                        if (result) {
                            url = RESULT_PAGE;
                        }
                    }                                        
                }                
            }
        } catch (NamingException | SQLException | NumberFormatException ex) {
            log("ChangeSubjectsStatusServlet: "+ ex);
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
