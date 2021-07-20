package Temp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DTO.User.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author ADMIN
 */
public class UsersDAO implements Serializable {

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

    public UsersDAO() throws NamingException, SQLException {
        getAllUsers();
    }
    private List<UserDTO> userList = new ArrayList<>();

    public List<UserDTO> getUserList() {
        return userList;
    }

    public String getUserID(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select UserID "
                        + "From Users "
                        + "Where Email = ?";
                //3.Create Statement Object and assign Parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    email = rs.getString("userid");
                }

            }//end if it is existed
            //end if connection is opened

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
        return email;
    }

    public boolean updateUser(
            String userID,
            String role,
            boolean status
    ) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            String sql = "UPDATE Users "
                    + "SET Role = ? , Status = ? "
                    + "Where UserID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, role);
            stm.setBoolean(2, status);
            stm.setString(3, userID);

            int rowAffect = stm.executeUpdate();
            if (rowAffect > 0) {
                return true;
            }
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

    public boolean checkExistUserEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Email "
                        + "From Users "
                        + "Where Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
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

    public List<UserDTO> getAllExperts()
            throws NamingException, SQLException {
        List<UserDTO> resultList = new ArrayList<>();
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, UserID, Fullname, Password, CreateDate, Status "
                        + "FROM Users "
                        + "WHERE Role = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "expert");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String userID = rs.getString("UserID");
                    String fullName = rs.getString("Fullname");
                    String password = rs.getString("Password");
                    String role = "expert";
                    Date createDate = rs.getDate("CreateDate");
                    boolean status = rs.getBoolean("Status");

                    resultList.add(new UserDTO(email, userID, role, fullName, password, status, createDate));
                }
            }
        } finally {
            closeConnection();
        }
        return resultList;
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
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO "
                        + "UserProfile(Email, Avatar, Gender, Mobile, Address) "
                        + "VALUES(?, ?, ?, ?, ?) ";
                //3. Create statement and assign parameter value if any
                stm = con.prepareStatement(sql);
                stm.setString(1, Email);
                if (Avatar == null) {
                    stm.setNull(2, Types.VARCHAR);
                } else {
                    stm.setString(2, Avatar);
                }
                if (Gender == null) {
                    stm.setNull(3, Types.VARCHAR);
                } else {
                    stm.setString(3, Gender);
                }
                if (Mobile == null) {
                    stm.setNull(4, Types.VARCHAR);
                } else {
                    stm.setString(4, Mobile);
                }
                if (Address == null) {
                    stm.setNull(5, Types.VARCHAR);
                } else {
                    stm.setString(5, Address);
                }
                //4. Execute query
                int rowAffect = stm.executeUpdate();
                //5. Process result
                if (rowAffect > 0) {
                    return true;
                }
            } //end if con is opened
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean createNewUser(String Email, String UserID, String Role,
            String Fullname, String Password, boolean Status, Date CreateDate) throws NamingException, SQLException {
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
            closeConnection();
        }
        return false;

    }

    public String getNextUserID() {
        String nextUserIDPrefix = "ID";
        String numberPart = String.valueOf(userList.size());

        while (numberPart.length() < 8) {
            numberPart = "0" + numberPart;
        }

        return nextUserIDPrefix + numberPart;
    }

    public void getAllUsers()
            throws NamingException, SQLException {
        userList = new ArrayList<>();
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
        userList = new ArrayList<>();
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
