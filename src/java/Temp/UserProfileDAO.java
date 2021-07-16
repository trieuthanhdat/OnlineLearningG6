package Temp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DTO.User.UserProfileDTO;
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
public class UserProfileDAO implements Serializable {

    private ArrayList<UserProfileDTO> userprofile = null;

    public ArrayList<UserProfileDTO> getUserProfileList() {
        return userprofile;
    }

    public UserProfileDAO() {
    }

    public void getUserProfile() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, Avatar, Gender, Mobile, Address ,CreateDate "
                        + "FROM UserProfile ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String avatar = rs.getString("Avatar");
                    String gender = rs.getString("Gender");
                    String phone = rs.getString("Mobile");
                    String address = rs.getString("Address");

                    UserProfileDTO user = new UserProfileDTO(email, avatar, gender, phone, address);
                    if (this.userprofile == null) {
                        this.userprofile = new ArrayList<>();
                    }
                    this.userprofile.add(user);
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

    public boolean updateUserProfile(
            String email,
            String avartar,
            String gender,
            String phone,
            String address
    ) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            String sql = "UPDATE UserProfile "
                    + "SET Avatar = ? , Gender = ? , Mobile = ? , Address = ? "
                    + "Where Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, avartar);
            stm.setString(2, gender);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, email);

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

    public UserProfileDTO getUserEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserProfileDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, Avatar, Gender, Mobile, Address ,CreateDate "
                        + "FROM UserProfile "
                        + "WHERE Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String avatar = rs.getString("Avatar");
                    String gender = rs.getString("Gender");
                    String phone = rs.getString("Mobile");
                    String address = rs.getString("Address");
                    dto = new UserProfileDTO(email, avatar, gender, phone, address);
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
        return dto;
    }

}
