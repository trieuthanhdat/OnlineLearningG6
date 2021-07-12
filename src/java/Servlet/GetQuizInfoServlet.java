/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import QuizNQuestion.QuizDAO;
import QuizNQuestion.QuizDTO;
import QuizNQuestion.UserAnswerDAO;
import QuizNQuestion.UserAnswerDTO;
import group6.entity.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class GetQuizInfoServlet extends HttpServlet {
    private static final String QUIZ_PAGE = "quiz.jsp";
    private static final String ERROR = "error.jsp";

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
        String url = ERROR;
        
        try {
            HttpSession session = request.getSession(); // Lấy thông tin người dùng và quizID
            UserDTO user = (UserDTO) session.getAttribute("USER");
            int quizID = Integer.parseInt(request.getParameter("quizID"));
            
            QuizDAO quizDAO = new QuizDAO();
            QuizDTO quiz = quizDAO.getQuiz(quizID);
            
            if (quiz != null){ // check xem quiz có tồn tại hay ko
                request.setAttribute("QUIZ_INFO", quiz);
                url = QUIZ_PAGE;
                UserAnswerDAO userAns = new UserAnswerDAO();
                UserAnswerDTO Ans = userAns.checkUserAnswerExist(quizID, user.getUserID());
                
                if (Ans != null) { // check xem người dùng đã làm quiz hay chưa
                    request.setAttribute("USER_SCORE", Ans);
                }
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(GetQuizInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetQuizInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
