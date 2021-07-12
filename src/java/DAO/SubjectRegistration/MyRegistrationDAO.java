/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import group6.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import khoalnm.registration.MyRegistrationDTO;

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
    
}
