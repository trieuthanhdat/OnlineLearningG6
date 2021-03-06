/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Quiz;

import DAO.Question.QuestionDAO;
import DTO.Question.QuestionDTO;
import DAO.Quiz.QuizDAO;
import DTO.Quiz.QuizDTO;
import DTO.Quiz.QuizErrorDTO;
import DAO.Option.QuizOptionDAO;
import DAO.Question.QuizQuestionDAO;
import DTO.Question.QuizQuestionDTO;
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
        QuestionDAO questionDAO = new QuestionDAO();
        QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
        QuizOptionDAO quizOptionDAO = new QuizOptionDAO();
        QuizErrorDTO error = new QuizErrorDTO();

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String name = request.getParameter("name");
        int numOfQuestions = Integer.parseInt(request.getParameter("numOfQuestions"));
        Time duration = Time.valueOf(request.getParameter("duration"));
        double passRate = Double.parseDouble(request.getParameter("passRate"));
        String level = request.getParameter("level");
        int lessonID = Integer.parseInt(request.getParameter("lessonID"));

        try {
            boolean isError = true;
            questionDAO.importQuestion(lessonID);//l???y question thu???c lesson ???? ra
            List<QuestionDTO> question = questionDAO.getQuestion();

            if (name.length() < 5 || name.length() > 50) { // name q??a ng???n hay qu?? d??i
                error.setInvalidQuizName("Name must be 5-50 characters");
                isError = false;
            }

            if (question.size() < numOfQuestions) { // N???u s??? l?????ng c??u h???i kh??ng kh???p v???i l???i s??? l?????ng y??u c???u
                error.setQuestionShortage("Not enough question. Please add more!");
                isError = false;
            }

            if (quizQuestionDAO.randomQuestion(question, level, numOfQuestions, 0) == false) { // N???u s??? l?????ng c??u theo level ???????c ?????m b???o
                error.setQuestionLevelShortage("Not enough question for that level");
                isError = false;
            }
            
            if (passRate > 80 || passRate < 50) {// pass rate v?? l??
                error.setUnreasonablePassRate("Pass rate must not lower than 50% and higher than 80%");
                isError = false;
            }
            
            if (isError) {
                QuizDTO quiz = new QuizDTO(0, subjectID, name, numOfQuestions, duration, passRate, level, false);

                QuizDAO quizDAO = new QuizDAO();
                boolean result1 = quizDAO.createQuiz(quiz);// t???o quiz
                int quizID = quizDAO.getQuizListSize(); // query l???i ????? l???y quizID

                if (result1) {//t???o th??nh c??ng copy question
                    //truy???n v??o quiz ????? ph??n lo???i v?? copy
                    boolean result2 = quizQuestionDAO.randomQuestion(question, level, numOfQuestions, quizID);

                    if (result2) {// random ko thi???u c??u n??o
                        boolean result3 = quizQuestionDAO.exportQuizQuestion();
                        quizQuestionDAO.importQuizQuestion(quizID); //query l???i ????? l???y questionNo
                        List<QuizQuestionDTO> quizQuestion = quizQuestionDAO.getQuizQuestionList();

                        if (result3) {// n???u c??u h???i ???????c th??m v??o th??nh c??ng, l???y option theo t???ng c??u h???i
                            quizOptionDAO.getQuizOption(quizQuestion);
                            boolean result4 = quizOptionDAO.exportOption();

                            if (result4) {
                                url = QUIZ_LIST_PAGE;
                            } else {
                                quizDAO.removeQuiz(quizID);
                                quizQuestionDAO.removeQuestionByQuiz(quizID);
                                quizOptionDAO.removeOption(quizQuestion);
                                error.setCreateError("Some issue happen and prevent quiz from being create");
                                request.setAttribute("Create_Quiz_Error", error);
                            }
                        } else {
                            quizDAO.removeQuiz(quizID);
                            quizQuestionDAO.removeQuestionByQuiz(quizID);
                            error.setCreateError("Some issue happen and prevent quiz from being create");
                            request.setAttribute("Create_Quiz_Error", error);
                        }
                    } else {
                        quizDAO.removeQuiz(quizID);
                        error.setCreateError("Some issue happen and prevent quiz from being create");
                        request.setAttribute("Create_Quiz_Error", error);
                    }
                }
            } else {
                request.setAttribute("Create_Quiz_Error", error);
            }

        } catch (NamingException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MakeQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
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
