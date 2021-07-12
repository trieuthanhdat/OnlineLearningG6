/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.Quiz.QuizDAO;
import DAO.Quiz.QuizOptionDAO;
import DAO.Quiz.QuizQuestionDAO;
import DTO.Question.QuestionDTO;
import DTO.Quiz.QuizDTO;
import DTO.Quiz.QuizQuestionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
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
@WebServlet(name = "MakeQuizServlet", urlPatterns = {"/MakeQuizServlet"})
public class MakeQuizServlet extends HttpServlet {

    private final static String QUIZ_LIST_PAGE = "QuizList.jsp";
    private final static String ERROR = "error.jsp";

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

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String name = request.getParameter("name");
        int numOfQuestions = Integer.parseInt(request.getParameter("numOfQuestions"));
        Time duration = Time.valueOf(request.getParameter("duration"));
        double passRate = Double.parseDouble(request.getParameter("passRate"));
        String level = request.getParameter("level");
        int lessonID = Integer.parseInt(request.getParameter("lessonID"));

        try {
            QuizDTO quiz = new QuizDTO(0, subjectID, name, numOfQuestions, duration, passRate, level, false);

            QuizDAO quizDAO = new QuizDAO();
            boolean result1 = quizDAO.createQuiz(quiz);// tạo quiz
            int quizID = quizDAO.getQuizListSize(); // query lại để lấy quizID

          if (result1) {//tạo thành công copy question
                QuestionDAO questionDAO = new QuestionDAO(); //lấy question thuộc lesson đó ra
                questionDAO.importQuestion(lessonID);
                List<QuestionDTO> question = questionDAO.getQuestion();

                if (question.size() >= numOfQuestions) { // Nếu số lượng câu hỏi khớp với lại số lượng yêu cầu
                    QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO(); //truyền vào quiz để phân loại và copy
                    boolean result2 = quizQuestionDAO.randomQuestion(question, level, numOfQuestions, quizID);
                    
                    if (result2) {// random ko thiếu câu nào
                        boolean result3 = quizQuestionDAO.exportQuizQuestion();
                        quizQuestionDAO.importQuizQuestion(quizID); //query lại để lấy questionNo
                        List<QuizQuestionDTO> quizQuestion = quizQuestionDAO.getQuizQuestionList();

                        if (result3) {// nếu câu hỏi được thêm vào thành công, lấy option theo từng câu hỏi
                            QuizOptionDAO quizOptionDAO = new QuizOptionDAO();
                            quizOptionDAO.getQuizOption(quizQuestion);
                            boolean result4 = quizOptionDAO.exportOption();
                            
                            if (result4) {
                                url = QUIZ_LIST_PAGE;
                            }
                        }
                    } else {// báo thiếu question ở level đó
                        
                    }

                } else { // thông báo ko đủ quiz trong bank 

                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);
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
