/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.SubjectRegistration;

import DTO.SubjectRegistration.PackageDTO;
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
public class PackageDAO implements  Serializable{
    private ArrayList<PackageDTO> packages = null;
    public ArrayList<PackageDTO> getPackageList(){
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
                String sql = "SELECT PackageID, Name, AccessDuration, "
                        + "Status, ListPrice, SalePrice, "
                        + "Description "
                        +" From PricePackage ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int PackageID = rs.getInt("PackageID");
                    String Name = rs.getString("Name");
                    Date AccessDuration= rs.getDate("AccessDuration");
                    boolean status = rs.getBoolean("Status");   
                    float ListPrice= rs.getFloat("ListPrice");
                    float SalePrice = rs.getFloat("SalePrice");
                    String description =rs.getString("Description");                        
                    PackageDTO  pkg = new PackageDTO(PackageID, Name, AccessDuration,
                            status, ListPrice, SalePrice, description);
                    if(this.packages ==null){
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
    
    
}
