package Servlet.System;

import DAO.Subject.SubjectCategoryDAO;
import DAO.Subject.SubjectDAO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ShowWelcomePageServlet", urlPatterns = {"/ShowWelcomePageServlet"})
public class ShowWelcomePageServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage.jsp";
    private final String HOME_PAGE = "HomePage";
    private final String DASHBOARD_PAGE = "Dashboard";

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

        String url = WELCOME_PAGE;

        HttpSession session = request.getSession(false);
        if (session != null) {
            UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
            if (currUser != null) {
                if (currUser.getRole().equals("User")) {
                    url = HOME_PAGE;
                } else {
                    url = DASHBOARD_PAGE;
                }
            }

        }
        if (url.equals(WELCOME_PAGE)) {
            try{
            // slides, posts, courses
            SubjectDAO subdao = new SubjectDAO();
            SubjectCategoryDAO catedao = new SubjectCategoryDAO();
            
            
            session = request.getSession(true);
            session.setAttribute("SUBJECT_LIST", subdao.getSubjectsList());
            session.setAttribute("CATEGORY_LIST", catedao.getSubjectsCategoryList());
            log(subdao.getSubjectsList().toString());
            }catch(Exception ex){
                ex.printStackTrace();
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
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
