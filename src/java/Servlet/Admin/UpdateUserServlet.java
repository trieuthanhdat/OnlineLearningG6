/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Admin;

import DAO.User.UserDAO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {
private final String USER_LIST="user.jsp";
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
        String role = request.getParameter("selectRole");
        String selectStatus = request.getParameter("selectStatus");
        String email = request.getParameter("email");
        String url = USER_LIST;
        boolean status = false;
        if(selectStatus.equals("true")){
            status= true;
        }
        try { 
            UserDAO dao = new UserDAO();
            boolean result = dao.updateUser(email, role, status);
            if(result){
                //update xong thì load lại page vua cap nhat, thieu cai nay thì empty list null => no result match
                UserDAO userdao = new UserDAO();
                userdao.getUser();
                ArrayList<UserDTO> userList = userdao.getUserList();
                request.setAttribute("userlist", userList);
                url = USER_LIST;
            }
        }catch(SQLException ex){
            log("Check UpdateUserServlet SQL Exception - "+ex);
        }catch(NamingException ex){
            log("Check UpdateUserServlet NamingException - "+ex);
        }
        finally{
            RequestDispatcher rd=request.getRequestDispatcher(url);
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
