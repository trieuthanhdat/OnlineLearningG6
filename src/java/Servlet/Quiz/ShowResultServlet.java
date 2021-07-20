/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Quiz;

import DTO.User.UserDTO;
import DTO.Answer.AnswerDTO;
import DTO.Quiz.QuizDTO;
import DTO.Quiz.QuizOptionDTO;
import DTO.Question.QuizQuestionDTO;
import DAO.Answer.UserAnswerDAO;
import DTO.Answer.UserAnswerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class ShowResultServlet extends HttpServlet {
    private final static String QUIZ_INFO_PAGE = "QuizHandle";

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
        
        String url = QUIZ_INFO_PAGE;
        
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");
        QuizDTO quiz = (QuizDTO) session.getAttribute("QUIZ_INFO");
        List<QuizOptionDTO> option = (List<QuizOptionDTO>) session.getAttribute("QUIZ_QUESTION_OPTION");
        List<AnswerDTO> answer = (List<AnswerDTO>) session.getAttribute("AnswerList");
        
        try {
            double point = 10/quiz.getNumOfQuestions();
            UserAnswerDAO dao = new UserAnswerDAO();
            List<AnswerDTO> rightAnswer = dao.copyAnswer(option, point);
            double score = dao.compareAnswer(answer, rightAnswer);
            String answerString = dao.createString(answer);
            UserAnswerDTO userAnswer = new UserAnswerDTO(user.getUserID(), quiz.getQuizID(), (ArrayList<AnswerDTO>) answer, score);
            boolean result = dao.saveToServer(userAnswer, answerString);
            if (result) {
                session.setAttribute("USER_SCORE", userAnswer);
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(ShowResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowResultServlet.class.getName()).log(Level.SEVERE, null, ex);
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
