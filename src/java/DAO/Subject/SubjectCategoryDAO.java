package group6.entity.subjectcategory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import group6.utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class SubjectCategoryDAO implements Serializable {
    
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeConnection() 
            throws SQLException, NamingException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    public SubjectCategoryDAO() 
            throws NamingException, SQLException {
        getAllSubjectsCategory();
    }
    
    private List<SubjectCategoryDTO> subjectsCategoryList = new ArrayList<>();

    public List<SubjectCategoryDTO> getSubjectsCategoryList() {
        return subjectsCategoryList;
    }
    
    private void getAllSubjectsCategory() 
            throws NamingException, SQLException {
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT SubjectCategoryID, Name, Description "
                           + "FROM SubjectCategory";
                        
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("SubjectCategoryID");
                    String subjectName = rs.getString("Name");
                    String description = rs.getString("Description");
                    
                    subjectsCategoryList.add(new SubjectCategoryDTO(ID, subjectName, description));
                }
            }
        } finally {
            closeConnection();
        }
    }
}
