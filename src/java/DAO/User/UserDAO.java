package DAO.User;

import DTO.User.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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

    public List<UserDTO> getUserList() {
        return userList;
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
        return null;

    }

    public UserDTO checkLogin(String email, String password) throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            String sql = "SELECT UserID, Role, Fullname, Status, CreateDate "
                    + "FROM Users "
                    + "WHERE Email = '" + email + "' AND Password = '" + password + "'";

            if (con != null) {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("UserID");
                    String role = rs.getString("Role");
                    String fullname = rs.getString("Fullname");
                    boolean status = rs.getBoolean("Status");
                    Date date = rs.getDate("CreateDate");

                    UserDTO dto = new UserDTO(email, userID, role, fullname, password, status, date);
                    return dto;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean createUserProfileDTO(String Email, String Avatar, String Gender,
            String Mobile, String Address) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO "
                        + "UserProfile(Email, Avatar, Gender, Mobile, Address) "
                        + "VALUES(?, ?, ?, ?, ?, ?) ";
                //3. Create statement and assign parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, Email);
                stm.setString(2, Avatar);
                stm.setString(3, Gender);
                stm.setString(4, Mobile);
                stm.setString(5, Address);

                //4. Execute query
                int rowAffect = stm.executeUpdate();
                //5. Process result
                if (rowAffect > 0) {
                    return true;
                }
            } //end if con is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewUser(String Email, String UserID, String Role,
            String Fullname, String Password, boolean Status, Date CreateDate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO "
                        + "Users(Email, UserID, Role, Fullname, Password, Status, CreateDate) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                //3. Create statement and assign parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, Email);
                stm.setString(2, UserID);
                stm.setString(3, Role);
                stm.setString(4, Fullname);
                stm.setString(5, Password);
                stm.setBoolean(6, Status);
                stm.setDate(7, CreateDate);
                //4. Execute query
                int rowAffect = stm.executeUpdate();
                //5. Process result
                if (rowAffect > 0) {
                    return true;
                }
            } //end if con is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    public void getAllUsers()
            throws NamingException, SQLException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, UserID, Role, Fullname, Password, Status, CreateDate "
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
                    Date date = rs.getDate("CreateDate");
                    userList.add(new UserDTO(email, userID, role, fullname, password, status, date));
                }
            }
        } finally {
            closeConnection();
        }
    }

    public void searchUserEmail(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, UserID, Role, Fullname, Password, Status, CreateDate "
                        + "FROM Users "
                        + "WHERE Email LIKE ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String userid = rs.getString("UserID");
                    String role = rs.getString("Role");
                    String fullname = rs.getString("Fullname");
                    String password = rs.getString("Password");
                    boolean status = rs.getBoolean("Status");
                    Date createDate = rs.getDate("CreateDate");
                    UserDTO dto = new UserDTO(email, userid, role, fullname, password, status, createDate);
                    if (this.userList == null) {
                        this.userList = new ArrayList<>();
                    }
                    this.userList.add(dto);
                }
            }
        } finally {
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
    }
}
