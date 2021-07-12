/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.Quiz.QuizOptionDAO;
import DAO.Quiz.QuizQuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetQuizQuestionServlet", urlPatterns = {"/GetQuizQuestionServlet"})
public class GetQuizQuestionServlet extends HttpServlet {
    private final static String TAKE_QUIZ = "quiz.jsp";
    private final static String QUESTION_NOT_FOUND = "error.jsp";

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
        PrintWriter out = response.getWriter();
        
        String url = QUESTION_NOT_FOUND;
        int QuizID = Integer.parseInt(request.getParameter("quizID"));
        
        try {
            QuizQuestionDAO dao1 = new QuizQuestionDAO();
            dao1.importQuizQuestion(QuizID);
            List<QuizQuestionDTO> question = dao1.getQuizQuestionList();
            
            QuizOptionDAO dao2 = new QuizOptionDAO();
            dao2.importOption(question);
            List<QuizOptionDTO> option = dao2.getOption();
            
            if (question != null && option != null) {
                request.setAttribute("QUIZ_QUESTION", question);
                request.setAttribute("QUIZ_QUESTION_OPTION", option);
                url = TAKE_QUIZ;
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(GetQuizQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetQuizQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
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
