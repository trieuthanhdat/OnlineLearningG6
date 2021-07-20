package Servlet.SubjectRegistration;

import DAO.SubjectRegistration.PackageDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import DTO.SubjectRegistration.RegistrationDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddNewRegistrationServlet", urlPatterns = {"/AddNewRegistrationServlet"})
public class AddNewRegistrationServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String ERROR_PAGE = "error.html";
    private final String SUCCESS_PAGE = "CourseDetail";

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
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
                if (currUser != null && currUser.getRole().equals("User")) {
                    int subID = Integer.parseInt(request.getParameter("txtSubjectID"));
                    int packageID = Integer.parseInt(request.getParameter("txtPackageID"));

                    PackageDAO packageDAO = new PackageDAO();
                    RegistrationDAO regDAO = new RegistrationDAO();

                    boolean ownCourse = regDAO.checkOwnCourse(currUser.getEmail(), subID);

                    if (!ownCourse) {
                        Date currDate = Date.valueOf(LocalDate.now());
                        // 1 month = 30 days
                        Date validTo = Date.valueOf(currDate.toLocalDate().plusDays(30 * packageDAO.getAccessDuration(packageID)));
                        boolean addResult = regDAO.addNewRegistration(new RegistrationDTO(0, subID, currUser.getEmail(), currDate, packageDAO.getSalePriceID(packageID), currDate, validTo, packageID, true));
                        if (addResult) {
                            url = SUCCESS_PAGE + "?txtSubjectID=" + subID;
                        }
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
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
