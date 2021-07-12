/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.User;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.MyRegistrationDAO;
import DAO.SubjectRegistration.PackageDAO;
import DTO.Subject.SubjectDTO;
import DTO.SubjectRegistration.MyRegistrationDTO;
import DTO.SubjectRegistration.PackageDTO;
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
@WebServlet(name = "ViewMyRegistrationServlet", urlPatterns = {"/ViewMyRegistrationServlet"})
public class ViewMyRegistrationServlet extends HttpServlet {
private final String MY_REGISTRATION = "myregistration.jsp";
private final String LOGIN_PAGE = "login.html";
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
        String url = MY_REGISTRATION;
        
        try {
            
            //Get Registration List
            MyRegistrationDAO dao = new MyRegistrationDAO();
            dao.getRegistration();
            ArrayList<MyRegistrationDTO> regislist = dao.getMyRegistrationArrayList();
            request.setAttribute("regislist", regislist);
            //Get Subject List
            SubjectDAO subdao = new SubjectDAO();
            subdao.getSubject();
            ArrayList<SubjectDTO> subjectlist = subdao.getSubjectList();
            request.setAttribute("subjectlist", subjectlist);
            //Get PAckage List\
            PackageDAO pkgdao = new PackageDAO();
            pkgdao.getPackage();
            ArrayList<PackageDTO> packagelist = pkgdao.getPackageList();
            request.setAttribute("packagelist", packagelist);
            
            
            
            if (regislist == null) {
                log("Registration List - ViewMyRegistration is null");
            }if(subjectlist == null){
                log("Subject List - ViewMyRegistration is null");
            }if (packagelist == null){
                log("Package List - ViewMyRegistration is null");
            }
        }catch(SQLException ex){
            log("Check MyRegistration SQL Exception  - "+ex);
        }catch(NamingException ex){
            log("Check MyRegistration NamingException  - "+ex);
        }finally{
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
