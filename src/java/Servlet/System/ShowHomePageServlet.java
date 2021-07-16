/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.System;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.RegistrationDAO;
import Temp.UsersDAO;
import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.RegistrationDTO;
import DTO.User.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ShowHomePageServlet", urlPatterns = {"/ShowHomePageServlet"})
public class ShowHomePageServlet extends HttpServlet {

    private final String WELCOME_PAGE = "WelcomePage";
    private final String HOME_PAGE = "HomePage.jsp";
    private final String DASHBOARD_PAGE = "";
    private final String ERROR_PAGE = "ErrorPage";

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
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String url = WELCOME_PAGE;

        HttpSession session = request.getSession(false);
        if (session != null) {
            UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
            if (currUser != null) {
                if (currUser.getRole().equals("User")) {
                    // slides, posts, courses
                    url = HOME_PAGE;
                } else {
                    // to Dashboard
                }
            }

        }

        if (url.equals(HOME_PAGE)) {
            try {
                SubjectDAO subdao = new SubjectDAO();
                UserDTO currUser = (UserDTO) session.getAttribute("CURRENT_USER");
                String userID = currUser.getUserID();
                RegistrationDAO regDAO = new RegistrationDAO();
                List<RegistrationDTO> regList = regDAO.getUserRegistrations(userID);
                List<Integer> subIDList = new ArrayList<>();

                for (RegistrationDTO regItem : regList) {
                    // find all registrations which status is valid
                    if (regItem.isStatus()) {
                        subIDList.add(regItem.getSubjectID());
                    }
                }

                List<SubjectDTO> myCourses = new ArrayList<>();
                SubjectDAO subDAO = new SubjectDAO();

                for (Integer subID : subIDList) {
                    SubjectDTO sub = subDAO.getSubjectByID(subID);
                    // find all enabled subjects and add to myCourse
                    if (sub.isStatus()) {
                        myCourses.add(sub);
                        log(sub.getTitle());
                    }
                
                }
                request.setAttribute("MYSUBJECT_LIST", myCourses);
                
            } catch (Exception ex) {
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

        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(ShowHomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowHomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(ShowHomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShowHomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
