/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.SubjectRegistration;

import DTO.Marketing.StatisDTO;
import DTO.SubjectRegistration.PackageDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class PackageDAO implements  Serializable{
    private List<PackageDTO> packages = null;
    public List<PackageDTO> getPackageList(){
       return packages;
    }

    public PackageDAO() {
    }        
    
    public void getPackage() throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "SELECT PackageID, SubjectID, Name, AccessDuration, "
                        + "Status, ListPrice, SalePrice, "
                        + "Description "
                        +" From PricePackage ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int packageID = rs.getInt("PackageID");                    
                    int subjectID = rs.getInt("SubjectID");
                    String name = rs.getString("Name");
                    int accessDuration= rs.getInt("AccessDuration");                    
                    BigDecimal listPrice= rs.getBigDecimal("ListPrice");
                    BigDecimal salePrice = rs.getBigDecimal("SalePrice");
                    String description =rs.getString("Description");  
                    boolean status = rs.getBoolean("Status"); ;                        
                    PackageDTO pkg = new PackageDTO(packageID, subjectID, name, accessDuration, status, listPrice.floatValue(), salePrice.floatValue(), description);
                    
                    if (this.packages == null){
                        this.packages = new ArrayList<>();
                    }
                    this.packages.add(pkg);
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
    
    public void getPackagesBySubjectID(int subjectID) 
            throws NamingException, SQLException {
        packages = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT PackageID, Name, AccessDuration, "
                           + "ListPrice, SalePrice, Description, Status "
                           + "FROM PricePackage "
                           + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectID);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int packageID = rs.getInt("PackageID");                    
                    String name = rs.getString("Name");
                    int accessDuration= rs.getInt("AccessDuration");                    
                    BigDecimal listPrice= rs.getBigDecimal("ListPrice");
                    BigDecimal salePrice = rs.getBigDecimal("SalePrice");
                    String description =rs.getString("Description");  
                    boolean status = rs.getBoolean("Status");   
                    
                    packages.add(new PackageDTO(packageID, subjectID, name, accessDuration, status, listPrice.floatValue(), salePrice.floatValue(), description));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }   
    public int getAccessDuration(int packageID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int  accessDuration = 0 ;
        try{
                //1.Connect DB
            con = DBHelpers.makeConnection();
            if(con!=null){
                //2.Create SQL String
                String sql = "Select AccessDuration " 
                            + "From PricePackage " 
                            + "Where PackageID = ?";
                //3.Create Statement Object and assign Parameter value if any
               stm = con.prepareStatement(sql);
               stm.setInt(1, packageID);
               rs = stm.executeQuery();
               if (rs.next()){
                  accessDuration= rs.getInt("AccessDuration");
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
        return accessDuration; 
    }
     public float getSalePriceID(int packageID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
                //1.Connect DB
            con = DBHelpers.makeConnection();
            if(con!=null){
                //2.Create SQL String
                String sql = "Select SalePrice " 
                            + "From PricePackage " 
                            + "Where PackageID = ?";
                //3.Create Statement Object and assign Parameter value if any
               stm = con.prepareStatement(sql);
               stm.setInt(1, packageID);
               rs = stm.executeQuery();
               if (rs.next()){
                  packageID= rs.getInt("SalePrice");
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
        return packageID; 
    }
    public ArrayList<StatisDTO> getTop5PopularSubject(Date from, Date to) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<StatisDTO> list = null;
        
        try{
                //1.Connect DB
            con = DBHelpers.makeConnection();
            if(con!=null){
                //2.Create SQL String
                String sql = "Select TOP 5 SubjectID,  COUNT(RegistrationID) AS 'RegistrationTime' "
                + "From Registration "
                + "Where RegistrationTime Between ? and ? "
                + "Group By SubjectID "
                + "ORDER BY  'RegistrationTime'  DESC   ,SubjectID ";    
                //3.Create Statement Object and assign Parameter value if any
               stm = con.prepareStatement(sql);
               stm.setDate(1, from );
               stm.setDate(2, to);
               rs = stm.executeQuery();
               list = new ArrayList<>();
               while (rs.next()){
                int registrationID = rs.getInt("SubjectID");
                int registrationTime = rs.getInt("RegistrationTime");
                StatisDTO dto = new StatisDTO(0, 0, 0, registrationID, registrationTime);
                list.add(dto);
            }//end if it is existed
            }//end if connection is opened
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
        return list; 
    }
}
