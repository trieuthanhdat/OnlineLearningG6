/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group6.controller;

import group6.entity.subject.SubjectDAO;
import group6.entity.subject.SubjectDTO;
import group6.entity.subject.SubjectDetailsDTO;
import group6.entity.subjectcategory.SubjectCategoryDAO;
import group6.entity.subjectcategory.SubjectCategoryDTO;
import group6.entity.user.UserDAO;
import group6.entity.user.UserDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ShowUpdateSubjectFormServlet", urlPatterns = {"/ShowUpdateSubjectFormServlet"})
public class ShowUpdateSubjectFormServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String UPDATE_SUBJECT_PAGE = "UpdateSubjectDetails.jsp";
    
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
                if (currUser != null) {
                    int currSubID = Integer.parseInt(request.getParameter("txtSubjectID"));
                    SubjectDAO subDAO = new SubjectDAO();
                    SubjectDTO currSub = subDAO.getSubjectByID(currSubID);
                    SubjectDetailsDTO details = subDAO.getSubjectDetailsByID(currSubID);

                    if (currSub != null && details != null) {
                        currSub.setDetails(details);
                        request.setAttribute("CURRENT_SUBJECT", currSub);

                        SubjectCategoryDAO categoryDAO = new SubjectCategoryDAO();
                        List<SubjectCategoryDTO> categoryList = categoryDAO.getSubjectsCategoryList();

                        UserDAO userDAO = new UserDAO();
                        UserDTO currOwner = userDAO.getCurrUserByID(currSub.getOwnerID());
                        request.setAttribute("OWNER", currOwner);

                        if (!categoryList.isEmpty() && currOwner != null) {
                            request.setAttribute("CATEGORY_LIST", categoryList);
                            request.setAttribute("OWNER", currOwner);
                            url = UPDATE_SUBJECT_PAGE;
                        }
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
