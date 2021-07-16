package DAO.SubjectRegistration;

import DTO.SubjectRegistration.RegistrationDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class RegistrationDAO implements Serializable{
    
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
    
    public RegistrationDAO()
            throws NamingException, SQLException {
        getAllRegistrations();
        validateAllRegistrations();
    }
    
    private List<RegistrationDTO> registrationsList = new ArrayList<>();

    public List<RegistrationDTO> getRegistrationsList() {
        return registrationsList;
    }
    
    private void getAllRegistrations() 
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT RegistrationID, SubjectID, UserID, RegistrationTime, "
                                  + "TotalCost, ValidFrom, ValidTo, PackageID, Status "
                           + "FROM Registration";
                
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while(rs.next()) {
                    int registrationID = rs.getInt("RegistrationID");
                    int subjectID = rs.getInt("SubjectID");
                    String userID = rs.getString("UserID");
                    Date registrationTime = rs.getDate("RegistrationTime");
                    int totalCost = rs.getInt("TotalCost");
                    Date validFrom = rs.getDate("ValidFrom");
                    Date validTo = rs.getDate("ValidTo");
                    int packageID = rs.getInt("PackageID");
                    boolean status = rs.getBoolean("Status");
                    
                    registrationsList.add(new RegistrationDTO(registrationID, subjectID, userID, registrationTime, totalCost, validFrom, validTo, packageID, status));
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    private void validateAllRegistrations() 
            throws NamingException, SQLException {
        try {
            Date currDate = new Date(Instant.now().toEpochMilli());

            for (RegistrationDTO reg : registrationsList) {
                if (currDate.compareTo(reg.getValidFrom()) >= 0 &&
                    currDate.compareTo(reg.getValidTo()) <= 0) {
                    // status is supposed to be true but is false in database
                    if (!reg.isStatus()) { 
                        changeRegistrationStatus(reg.getRegistrationID(), reg.isStatus());
                        reg.setStatus(!reg.isStatus());
                    }
                } else {
                    // status is supposed to be false but is true in database
                    if (reg.isStatus()) {
                        changeRegistrationStatus(reg.getRegistrationID(), reg.isStatus());
                        reg.setStatus(!reg.isStatus());
                    }
                }
            }
        } finally { }
    }
    
    public List<RegistrationDTO> getUserRegistrations(String currUserID) {
        
        List<RegistrationDTO> resultList = new ArrayList<>();
        for (RegistrationDTO regis : registrationsList) {
            if (regis.getUserID().equals(currUserID)) {
                resultList.add(regis);
            }
        }
        return resultList;
        
    }
    
    public boolean checkRegistration(String currUserID, int subjectID) {        
        boolean checkResult = false;
        for (RegistrationDTO regItem : registrationsList) {
            if (regItem.getSubjectID() == subjectID && regItem.getUserID().equals(currUserID) && regItem.isStatus()) {
                checkResult = true;
            }
        }
        return checkResult;        
    }
    
    public void changeRegistrationStatus(int registrationID, boolean currStatus) 
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                           + "SET Status = ? "
                           + "WHERE RegistrationID = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, !currStatus);
                stm.setInt(2, registrationID);     
                
                stm.executeUpdate();
            }
        } finally {
            closeConnection();
        }
    }
    
}
