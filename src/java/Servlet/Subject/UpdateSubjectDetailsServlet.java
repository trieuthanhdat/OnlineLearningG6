/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Subject;

import DAO.Subject.SubjectDAO;
import DTO.Subject.SubjectDTO;
import DTO.Subject.SubjectDetailsDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "UpdateSubjectDetailsServlet", urlPatterns = {"/UpdateSubjectDetailsServlet"})
public class UpdateSubjectDetailsServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String RESULT_PAGE = "ShowUpdateSubjectFormServlet";

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
                if (currUser != null) {
                    int subID = Integer.parseInt(request.getParameter("txtSubjectID"));
                    String title = request.getParameter("txtSubjectTitle");
                    int categoryID = Integer.parseInt(request.getParameter("txtCategoryID"));
                    String thumbnail = request.getParameter("txtThumbnailLink");
                    String tagLine = request.getParameter("txtTagLine");
                    String briefInfo = request.getParameter("txtBriefInfo");
                    String description = request.getParameter("txtDescription");

                    SubjectDetailsDTO subDetails = new SubjectDetailsDTO(tagLine, description);
                    SubjectDTO sub = new SubjectDTO(subID, categoryID, title, 0, thumbnail, "", briefInfo, subDetails, null, true, false);

                    SubjectDAO dao = new SubjectDAO();
                    if (dao.updateSubject(sub)) {
                        url = RESULT_PAGE + "?txtSubjectID=" + subID;
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
