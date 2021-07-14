/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDateTime;

public class NewAccount  {
    private String email;
    private String password;
    private String fullName;
    private String validationNums;
    private LocalDateTime createTime;

    public NewAccount() {
    }

    public NewAccount(String email, String password, String fullName, String validationNums, LocalDateTime createTime) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.validationNums = validationNums;
        this.createTime = createTime;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the validationNums
     */
    public String getValidationNums() {
        return validationNums;
    }

    /**
     * @param validationNums the validationNums to set
     */
    public void setValidationNums(String validationNums) {
        this.validationNums = validationNums;
    }

    /**
     * @return the createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }   
}