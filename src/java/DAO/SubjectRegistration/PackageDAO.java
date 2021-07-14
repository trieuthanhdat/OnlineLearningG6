/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.SubjectRegistration;

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
                    Date accessDuration= rs.getDate("AccessDuration");                    
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
                           + "FROM PricePackage"
                           + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int packageID = rs.getInt("PackageID");                    
                    String name = rs.getString("Name");
                    Date accessDuration= rs.getDate("AccessDuration");                    
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
}
