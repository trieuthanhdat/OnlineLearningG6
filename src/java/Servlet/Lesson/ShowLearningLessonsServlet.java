package Servlet.Lesson;

import DAO.Lesson.LessonDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.Lesson.LessonDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ShowLearningLessonsServlet", urlPatterns = {"/ShowLearningLessonsServlet"})
public class ShowLearningLessonsServlet extends HttpServlet {

    private final String ERROR_PAGE = "ErrorPage";
    private final String SHOW_CURRENT_LESSON_DETAILS = "ShowCurrentLessonServlet";
    
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
                int subID = Integer.parseInt(request.getParameter("txtSubjectID"));
                log("SubjectID: " + subID);
                
                if (currUser != null) {                    
                    RegistrationDAO regDAO = new RegistrationDAO();
                    
                    //check if the subject is still enabled
                    boolean result = regDAO.checkRegistration(currUser.getEmail(), subID);
                    log("Check registration result: " + result);
                    if (result) {
                        LessonDAO lessonDAO = new LessonDAO();
                        List<LessonDTO> lessonsByOrder = lessonDAO.getCurrLessons(subID);
                        
                        List<LessonDTO> topics = new ArrayList<>();
                        for (LessonDTO ls : lessonsByOrder) {
                            if (ls.getTopicID() == 0) {
                                topics.add(ls);
                            }
                        }
                        
                        List<LessonDTO> lessons = new ArrayList<>();
                        for (LessonDTO ls : lessonsByOrder) {
                            if (ls.getTopicID() != 0) {
                                lessons.add(ls);
                            }
                        }
                        
                        request.setAttribute("TOPICS_LIST", topics);
                        request.setAttribute("LESSONS_LIST", lessons);
                        url = SHOW_CURRENT_LESSON_DETAILS;
                    }
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
