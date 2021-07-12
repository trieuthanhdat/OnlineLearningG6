/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Sale;

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
@WebServlet(name = "SearchUserIDServlet", urlPatterns = {"/SearchUserIDServlet"})
public class SearchUserIDServlet extends HttpServlet {
private final String SEARCH_PAGE="myregistration.jsp";
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
        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;
        try  {
            MyRegistrationDAO dao = new MyRegistrationDAO();
            if (searchValue.trim().length() > 0) {//neu search co gia tri thi xu li
                dao.searchUserID(searchValue);//lay user theo userid
                ArrayList<MyRegistrationDTO> result = dao.getMyRegistrationArrayList();//search co the tra nhieu ket qua
                request.setAttribute("SEARCHRESULT", result);
                request.setAttribute("SearchField", searchValue);//cai nay de gan search value vao search
                url =SEARCH_PAGE;
            }else if(searchValue.trim().length()==0){//neu search value null thì show all list
               dao.getRegistration();//lay full list user
               ArrayList<MyRegistrationDTO> result = dao.getMyRegistrationArrayList();
               request.setAttribute("SEARCHRESULT", result);
               url =SEARCH_PAGE;
            }
            //Get Subject List to track subject name on jsp
            SubjectDAO subdao = new SubjectDAO();
            subdao.getSubject();
            ArrayList<SubjectDTO> subjectlist = subdao.getSubjectList();
            request.setAttribute("subjectlist", subjectlist);
            //Get PAckage List\ to track package id on jsp
            PackageDAO pkgdao = new PackageDAO();
            pkgdao.getPackage();
            ArrayList<PackageDTO> packagelist = pkgdao.getPackageList();
            request.setAttribute("packagelist", packagelist);
           
        }catch (SQLException ex) {
           log("Check SearchUserIDServlet SQLException - "+ex.getMessage());
        } catch (NamingException ne) {
            log("Check SearchUserIDServlet NamingException - "+ne.getMessage());
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
