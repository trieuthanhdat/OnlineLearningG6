package group6.entity.subject;

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
public class SubjectDAO implements Serializable {
    
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeConnection() 
            throws SQLException {
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
    
    public SubjectDAO() 
            throws NamingException, SQLException {
        getAllSubjects();
    }
    
    private List<SubjectDTO> subjectsList = new ArrayList<>();

    public List<SubjectDTO> getSubjectsList() {
        return subjectsList;
    }
    
    private void getAllSubjects() 
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT SubjectID, SubjectCategoryID, Title, NumOfLessons, "
                                  + "Thumbnail, Owner, BriefInfo, Status "
                           + "FROM Subjects";
                
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while(rs.next()) {
                    int subjectID = rs.getInt("SubjectID");
                    int categoryID = rs.getInt("SubjectCategoryID");                    
                    String subjectTitle = rs.getString("Title");
                    int numOfLessons = rs.getInt("NumOfLessons");
                    String thumbnail = rs.getString("Thumbnail");
                    String ownerID = rs.getString("Owner");
                    String briefInfo = rs.getString("BriefInfo");
                    boolean status = rs.getBoolean("Status");
                    
                    subjectsList.add(new SubjectDTO(subjectID, categoryID, subjectTitle, numOfLessons, thumbnail, ownerID, briefInfo, null, status));
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<SubjectDTO> getAvailableSubjectList(String userID, String userRole) {
        
        List<SubjectDTO> resultList = new ArrayList<>();
        
        if (userRole.equals("admin")) resultList.addAll(subjectsList);
        else {
            for (SubjectDTO dto : subjectsList) {
                if (dto.getOwnerID().equals(userID)) {
                    resultList.add(dto);
                }
            }
        }
        return resultList;
        
    }
    
    public boolean updateSubjectStatus(int subjectID) 
            throws NamingException, SQLException {
        
        try {                        
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Subjects "
                           + "SET Status = ? "
                           + "WHERE SubjectID = ?";
                
                stm = con.prepareStatement(sql);
                SubjectDTO dto = subjectsList.get(subjectID - 1); // subjectID - 1 = index of subject 
                if (dto == null) {
                    return false;
                } else {
                    boolean currStatus = dto.isStatus();
                    stm.setInt(1, currStatus ? 0 : 1);
                    stm.setInt(2, subjectID);
                    
                    int updateResult = stm.executeUpdate();
                    if (updateResult == 1) {
                        return true;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public SubjectDTO getSubjectByID(int subjectID) {        
        SubjectDTO resultSubject = null;
        for (SubjectDTO subItem : subjectsList) {
            if (subItem.getSubjectID() == subjectID) { 
                resultSubject = subItem;
                break;
            }
        }
        return resultSubject;           
    }      
    
    public SubjectDetailsDTO getSubjectDetailsByID(int subjectID) 
            throws NamingException, SQLException {
        SubjectDetailsDTO details = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT TagLine, Description "
                           + "FROM SubjectDetails "
                           + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectID);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    String tagLine = rs.getString("TagLine");
                    String description = rs.getString("Description");
                    
                    details = new SubjectDetailsDTO(tagLine, description);
                }
            }
        } finally {
            closeConnection();
        }
        return details;
    }
    
    public boolean addNewSubject(SubjectDTO newSubject) 
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Subjects (SubjectID, SubjectCategoryID, Title, "
                           + "NumOfLessons, Thumbnail, Owner, BriefInfo, Status) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectsList.size() + 1);
                stm.setInt(2, newSubject.getSubjectCategoryID());
                stm.setString(3, newSubject.getTitle());
                stm.setInt(4, newSubject.getNumOfLessons());
                stm.setString(5, newSubject.getThumbnail());
                stm.setString(6, newSubject.getOwnerID());
                stm.setString(7, newSubject.getBriefInfo());
                stm.setBoolean(8, newSubject.isStatus());
                
                int result = stm.executeUpdate();
                if (result != 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean addNewSubjectDetails(SubjectDTO newSubject) 
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO SubjectDetails (SubjectID, TagLine, Description) "
                           + "VALUES (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, subjectsList.size() + 1);
                stm.setString(2, newSubject.getDetails().getTagLine());
                stm.setString(3, newSubject.getDetails().getDescription());
                
                int result = stm.executeUpdate();
                if (result != 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean updateSubject(SubjectDTO sub) 
            throws NamingException, SQLException {                
        try {
            con = DBHelpers.makeConnection();
            con.setAutoCommit(false);
            
            if (con != null) {
                String sql = "UPDATE Subjects "                                                      
                           + "SET SubjectCategoryID = ?, Title = ?, Thumbnail = ?, BriefInfo = ? "
                           + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, sub.getSubjectCategoryID());
                stm.setString(2, sub.getTitle());
                stm.setString(3, sub.getThumbnail());
                stm.setString(4, sub.getBriefInfo());
                stm.setInt(5, sub.getSubjectID());
                
                int result1 = stm.executeUpdate();
                
                sql = "UPDATE SubjectDetails "
                    + "SET TagLine = ?, Description = ? "
                    + "WHERE SubjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, sub.getDetails().getTagLine());
                stm.setString(2, sub.getDetails().getDescription());
                stm.setInt(3, sub.getSubjectID());
                
                int result2 = stm.executeUpdate();
                
                con.commit();                                
                
                if (result1 != 0 && result2 != 0) {
                    return true;
                }                                
            }
        } catch (SQLException ex) {
            con.rollback();
            throw new SQLException(ex.toString());
        } finally {
            closeConnection();
        }
        return false;
    }
}
