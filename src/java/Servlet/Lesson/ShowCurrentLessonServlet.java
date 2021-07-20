package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DAO.Quiz.QuizDAO;
import DTO.Lesson.LessonDTO;
import DTO.Quiz.QuizDTO;
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

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ShowCurrentLessonServlet", urlPatterns = {"/ShowCurrentLessonServlet"})
public class ShowCurrentLessonServlet extends HttpServlet {

    private final String ERROR_PAGE = "ErrorPage";
    private final String SHOW_LEARNING_PAGE = "Lectures.jsp";

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
            int lessonID = 0;
            String lessonIDasString = request.getParameter("txtLesssonID");
            if (lessonIDasString != null) {
                lessonID = Integer.parseInt(lessonIDasString);
            }
            log("LessonID: " + lessonID);

            List<LessonDTO> lessons = (List<LessonDTO>) request.getAttribute("LESSONS_LIST");
            boolean validLesson = false;
            for (LessonDTO ls : lessons) {
                if (lessonID == 0) {
                    // get first lesson
                    validLesson = true;
                    lessonID = ls.getLessonID();
                    break;
                } else if (ls.getLessonID() == lessonID) {
                    validLesson = true;
                    break;
                }
            }

            log(validLesson + " " + lessonID);
            if (validLesson) {

                LessonDAO lessonDAO = new LessonDAO();
                LessonDTO currLesson = lessonDAO.getCurrLessonDetails(lessonID);
                log(currLesson.getName());
                if (currLesson != null) {
                    log("Lesson details exist.");
                    if (currLesson.getDetails().getQuizID() != 0) {
                        QuizDAO quizDAO = new QuizDAO();
                        QuizDTO currQuiz = quizDAO.getQuiz(currLesson.getDetails().getQuizID());
                        request.setAttribute("CURRENT_QUIZ", currQuiz);
                    }
                    request.setAttribute("CURRENT_LESSON", currLesson);
                    url = SHOW_LEARNING_PAGE;
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        } finally {
            if (url.equals(ERROR_PAGE)) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
