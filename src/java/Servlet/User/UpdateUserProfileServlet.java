/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoalnm.controller;


import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoalnm.registration.UserProfileDAO;


/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateUserProfileServlet", urlPatterns = {"/UpdateUserProfileServlet"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UpdateUserProfileServlet extends HttpServlet {
private final String UPDATE_USER_PROFILE="updateUserProfile.jsp";
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
        String url = UPDATE_USER_PROFILE;
        
        
        String avatar = request.getParameter("txtAvatar");
        String gender = request.getParameter("txtGender");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        String email = request.getParameter("txtEmail"); 
        try  {     
            
            
//            // Create a factory for disk-based file items
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // Configure a repository (to ensure a secure temp location is used)
//            ServletContext servletContext = this.getServletConfig().getServletContext();
//            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//            factory.setRepository(repository);
//            // Create a new file upload handler
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            // Parse the request
//            List<FileItem> items = upload.parseRequest(request);
//            // Process the uploaded items
//            Iterator<FileItem> iter = items.iterator();
//            while (iter.hasNext()) {
//            FileItem item = iter.next();
//                if (item.isFormField()) {
//                    String name = item.getFieldName();
//                    String value = item.getString();
//                    System.out.println("Name:"+name);
//                    System.out.println("VAlue: "+value);
//                } else {
//                    
//                }
//            } 
            UserProfileDAO dao = new UserProfileDAO();
            boolean result = dao.updateUserProfile(email,avatar, gender, phone, address);
            if(result){
               url = UPDATE_USER_PROFILE;
           }
        }catch(SQLException ex){
            log("Check UpdateUserProfileServlet SQL Exception - "+ex);
        }catch(NamingException ex){
            log("Check UpdateUserProfileServlet NamingException - "+ex);
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
