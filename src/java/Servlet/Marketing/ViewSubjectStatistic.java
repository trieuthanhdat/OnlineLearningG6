/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Marketing;

import DAO.Subject.SubjectDAO;
import DAO.SubjectRegistration.MyRegistrationDAO;
import DTO.Marketing.StatisDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
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
@WebServlet(name = "ViewSubjectStatistic", urlPatterns = {"/ViewSubjectStatistic"})
public class ViewSubjectStatistic extends HttpServlet {
private final String VIEW_SUBJECT_STATISTIC="viewsubjectstatistic.jsp";
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
        String url = VIEW_SUBJECT_STATISTIC;
        try  {
            
            MyRegistrationDAO regdao = new MyRegistrationDAO();
            SubjectDAO dao = new SubjectDAO();
            int numofsubject = dao.getNumberOfSubject();
            int numberofregistration =  regdao.getNumberOfRegistration();
            int totalcost = regdao.getSumProfit();
//            log("Number of subject: "+numofsubject);
//            log("Num of Registration: "+numberofregistration);
//            log("Total cost: "+totalcost);
            long millis = System.currentTimeMillis();
            java.sql.Date to = new java.sql.Date(millis);
            long milis2 = System.currentTimeMillis() - 7 ;
            java.sql.Date from = new java.sql.Date(milis2);
            //get total cost moi 10 ngay
            ArrayList<StatisDTO> slist = new ArrayList<>();
            //---------------------------------------
            long millis3 = System.currentTimeMillis() -10;
            java.sql.Date end = new java.sql.Date(millis3);
            //-----------------------------------------
            long millis4 = System.currentTimeMillis()-20;
            java.sql.Date end1 = new java.sql.Date(millis4);
            //------------------------------------------
            long millis5 = System.currentTimeMillis()-30;
            java.sql.Date end2 = new java.sql.Date(millis5);
            //------------------------------------------
            slist.add(regdao.getProfitByDate(end1, end2 ));
            slist.add(regdao.getProfitByDate(end, end1 ));
            slist.add(regdao.getProfitByDate(to, end ));
            
            request.setAttribute("profitby10day", slist);
            log("from :" +milis2);
            log("to : "+millis);       
            ArrayList<StatisDTO> list =dao.getTop5PopularSubject(from,to);
            StatisDTO dto = new StatisDTO(numofsubject, numberofregistration, totalcost, 0, 0);
            
            request.setAttribute("top5subject", list);
            request.setAttribute("otherstatis", dto);
            
            log("Number of subject: "+numofsubject);
        }catch(SQLException ex){
            log("Check ViewMSubjectStatistic SQL Exception  - "+ex);
        }catch(NamingException ex){
            log("Check ViewMSubjectStatistic NamingException  - "+ex);
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
