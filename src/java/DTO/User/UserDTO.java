package DTO.User;

import java.io.Serializable;
import java.sql.Date;

public class UserDTO implements Serializable {
    private String email;
    private String userID;
    private String role;
    private String fullName;
    private String password;
    private boolean status;
    private Date createdate;
    public UserDTO() {
    }

    public UserDTO(String email, String userID, String role, String fullName, String password, boolean status, Date createdate) {
        this.email = email;
        this.userID = userID;
        this.role = role;
        this.fullName = fullName;
        this.password = password;
        this.status = status;
        this.createdate = createdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    
    
    
    
    
}
