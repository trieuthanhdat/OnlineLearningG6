/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Admin;
import Temp.UsersDAO;
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

/**
 *
 * @author DELL
 */
@WebServlet(name = "SearchUserEmailServlet", urlPatterns = {"/SearchUserEmailServlet"})
public class SearchUserEmailServlet extends HttpServlet {

    private final String SEARCH_PAGE = "user.jsp";

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
        String searchValue = request.getParameter("txtSearchEmail");
        String url = SEARCH_PAGE;
        try {

            UsersDAO dao = new UsersDAO();
            if (searchValue.trim().length() > 0) {//neu search co gia tri thi xu li
                dao.searchUserEmail(searchValue);//lay user theo user email
                List<UserDTO> result = dao.getUserList();//search co the tra nhieu ket qua
                request.setAttribute("SEARCHRESULT", result);
                request.setAttribute("SearchField", searchValue);//cai nay de gan search value vao search
                url = SEARCH_PAGE;

            } else if (searchValue.trim().length() == 0) {//neu search value null thì show all list
                dao.searchUserEmail(searchValue);//lay full list user theo email
                List<UserDTO> result = dao.getUserList();
                request.setAttribute("SEARCHRESULT", result);
                url = SEARCH_PAGE;

            }
        } catch (SQLException ex) {
            log("Check SearchUserEmailServlet SQLException - " + ex.getMessage());
        } catch (NamingException ne) {
            log("Check SearchUserEmailServlet NamingException - " + ne.getMessage());
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
