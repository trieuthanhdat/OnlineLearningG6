/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.SubjectRegistration;

import DTO.Marketing.StatisDTO;
import DTO.SubjectRegistration.MyRegistrationDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class MyRegistrationDAO implements Serializable{

    public MyRegistrationDAO() {
    }
   
    private ArrayList<MyRegistrationDTO> registration = null;
    public ArrayList<MyRegistrationDTO> getMyRegistrationArrayList(){
       return registration;
    }
    
//    
//    
    public void getRegistration() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "SELECT RegistrationID, SubjectID, UserID, "
                        + "RegistrationTime, TotalCost, ValidTo, "
                        + "ValidFrom, Status, PakageID "
                        +" From Registration";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int registrationID = rs.getInt("RegistrationID");
                    int subjectID = rs.getInt("SubjectID");
                    String userID =rs.getString("UserID");
                    Date registrationTime = rs.getDate("RegistrationTime");
                    float totalCost = rs.getFloat("TotalCost");
                    Date validTo = rs.getDate("ValidTo");
                    Date validFrom = rs.getDate("ValidFrom");
                    boolean status= rs.getBoolean("Status");
                    int packageID = rs.getInt("PakageID");          
                    MyRegistrationDTO regis = new MyRegistrationDTO(registrationID, subjectID, userID, 
                            registrationTime, totalCost, validTo, validFrom, status, packageID);
                    if(this.registration ==null){
                        this.registration = new ArrayList<>();
                    }
                    this.registration.add(regis);
                }
            }  
        }finally{
            if(rs!=null){
                rs.close();
            }if(stm !=null){
                stm.close();
            }if(con!=null){
                con.close();
            }
        }
    }
    public boolean createNewRegistration(String userID, int subjectID, int packageID,
            Date validFrom, Date validTo, float TotalCost, Date RegistrationTime, String LastUpdatedBy, boolean Status) throws NamingException, SQLException{
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
           con = DBHelpers.makeConnection();
           if(con!=null){
               String sql = "Insert Into "
                       + "Registration(UserID,SubjectID,PakageID,ValidFrom,ValidTo,"
                       + "TotalCost,RegistrationTime,LastUpdatedBy,Status) "
                       + "Values(?,?,?,?,?,?,?,?,?) ";
               stm= con.prepareStatement(sql);
               stm.setString(1, userID);
               stm.setInt(2, subjectID);
               stm.setInt(3, packageID);
               stm.setDate(4, validFrom);
               stm.setDate(5, validTo);
               stm.setFloat(6, TotalCost);
               stm.setDate(7, RegistrationTime);
               stm.setString(8, LastUpdatedBy);
               stm.setBoolean(9, Status);
               int rowEffect = stm.executeUpdate();
               if(rowEffect>0){
                   return true;
               }
           }
        }finally{
            if(rs!=null){
                rs.close();
            }if(stm!=null){
                stm.close();
            }if(con!=null){
                con.close();
            }
        }
        return false;
    }
    public void searchUserID(String searchValue) throws NamingException, SQLException{
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                //2.Create SQL String
                String sql = "SELECT RegistrationID, SubjectID, UserID, "
                        + "RegistrationTime, TotalCost, ValidTo, "
                        + "ValidFrom, Status, PakageID "
                        + "From Registration "
                        + "Where UserID LIKE ? ";
                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"  + searchValue + "%");
                //4.Execute Query
                rs = stm.executeQuery();
                //5.process result
                while (rs.next()){
                    int registrationID = rs.getInt("RegistrationID");
                    int subjectID = rs.getInt("SubjectID");
                    String userID =rs.getString("UserID");
                    Date registrationTime = rs.getDate("RegistrationTime");
                    float totalCost = rs.getFloat("TotalCost");
                    Date validTo = rs.getDate("ValidTo");
                    Date validFrom = rs.getDate("ValidFrom");
                    boolean status= rs.getBoolean("Status");
                    int packageID = rs.getInt("PakageID");  
                    MyRegistrationDTO dto = new MyRegistrationDTO(registrationID, subjectID, userID, registrationTime, totalCost, validTo, validFrom, status, packageID);
                    if(this.registration == null){
                        this.registration = new ArrayList<>() ;
                    }
                    this.registration.add(dto);
                }
            }
        }finally{
            if(rs!=null){
                rs.close();
            }if(stm!=null){
                stm.close();
            }if(con!=null){
                con.close();
            }
        }
    }
    public int getNumberOfRegistration() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int num = 0;
        try{
                //1.Connect DB
            con = DBHelpers.makeConnection();
            if(con!=null){
                //2.Create SQL String
                String sql = "Select COUNT (RegistrationID) " 
                            + "From Registration " ;
                            
                //3.Create Statement Object and assign Parameter value if any
               stm = con.prepareStatement(sql);
               
               rs = stm.executeQuery();
               if (rs.next()){
                num = rs.getInt(1);
                }
            }//end if it is existed
            //end if connection is opened
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }   
            if(con!=null){
                con.close();
            }
        }
        return num;
    }
    public int getSumProfit() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total=0;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "SELECT TotalCost "
                        +" From Registration";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int totalCost = rs.getInt("TotalCost");
                    total += totalCost;
                    }
                }
            }  
        finally{
            if(rs!=null){
                rs.close();
            }if(stm !=null){
                stm.close();
            }if(con!=null){
                con.close();
            }
        }
        return total;
    }
    
    public StatisDTO getProfitByDate(Date from, Date to) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total=0;
        StatisDTO dto = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "SELECT TotalCost "
                        +" From Registration "
                        + "Where RegistrationTime Between ? and ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, from);
                stm.setDate(2,to);
                rs = stm.executeQuery();
                while(rs.next()){
                    int totalCost = rs.getInt("TotalCost");
                    total += totalCost;
                    
                }
                dto= new StatisDTO(0, 0, total, 0, 0);
                }
            }  
        finally{
            if(rs!=null){
                rs.close();
            }if(stm !=null){
                stm.close();
            }if(con!=null){
                con.close();
            }
        }
        return dto;
    }
    
    
}
