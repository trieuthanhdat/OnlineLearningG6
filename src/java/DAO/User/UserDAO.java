package DAO.User;

import DTO.User.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBHelpers;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (this.rs != null) {
            rs.close();
        }
        if (this.stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
//    public UserDAO(){
//        
//    }

//    public UserDAO()throws NamingException, SQLException {
//        getAllUsers();
//    }
    private List<UserDTO> userList = new ArrayList<>();

    private void getAllUsers()
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, UserID, Role, Fullname, Password, Status "
                        + "FROM Users";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String userID = rs.getString("UserID");
                    String role = rs.getString("Role");
                    String fullname = rs.getString("Fullname");
                    String password = rs.getString("Password");
                    boolean status = rs.getBoolean("Status");

                    userList.add(new UserDTO(email, userID, role, fullname, password, status));
                }
            }
        } finally {
            closeConnection();
        }
    }

    public boolean checkLogin(String email, String password)
            throws NamingException, SQLException {

        for (UserDTO user : userList) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public UserDTO getCurrUserByEmail(String email) {
        for (UserDTO user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public UserDTO getCurrUserByID(String userID) {
        for (UserDTO user : userList) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public String getUserFullName(String userID) {
        for (UserDTO user : userList) {
            if (user.getUserID().equals(userID)) {
                return user.getFullName();
            }
        }
        return "";
    }

    public List<UserDTO> getAllExperts() {
        List<UserDTO> expertList = new ArrayList<>();
        for (UserDTO user : userList) {
            if (user.getRole().equals("expert")) {
                expertList.add(user);
            }
        }
        return expertList;
    }

    public UserDTO checkLogin2(String email, String password) throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            String sql = "SELECT UserID, Role, Fullname, Status "
                    + "FROM Users "
                    + "WHERE Email = ? AND Password = ?";

            if (con != null) {
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                
                rs = stm.executeQuery();                
                if (rs.next()) {
                    String userID = rs.getString("UserID");
                    String role = rs.getString("Role");
                    String fullname = rs.getString("Fullname");
                    boolean status = rs.getBoolean("Status");

                    UserDTO dto = new UserDTO(email, userID, role, fullname, password, status);
                    return dto;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

}
