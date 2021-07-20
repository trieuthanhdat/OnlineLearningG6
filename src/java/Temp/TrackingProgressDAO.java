package Temp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author DELL
 */
public class TrackingProgressDAO implements Serializable{

    public TrackingProgressDAO() {
    }
    
    
    public boolean addnewTrackingProgress(int registrationID, int QuizID, Date Deadline, boolean Status) throws NamingException, SQLException{
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
           con = DBHelpers.makeConnection();
           if(con!=null){
               String sql = "Insert Into "
                       + "TrackingProgress(RegistrationID,QuizID,DeadLine,Status) "
                       + "Values(?,?,?,?) ";
               stm= con.prepareStatement(sql);
               stm.setInt(1, registrationID);
               stm.setInt(2, QuizID);
               stm.setDate(3, Deadline);
               stm.setBoolean(4, Status);
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
}
