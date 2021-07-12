/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoalnm.registration;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class UserProfileDTO implements Serializable{
    private String email;
    private String avartar;
    private String gender;
    private String phone;
    private String address;
    private Date date;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String email, String avartar, String gender, String phone, String address, Date date) {
        this.email = email;
        this.avartar = avartar;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.date = date;
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
     * @return the avartar
     */
    public String getAvartar() {
        return avartar;
    }

    /**
     * @param avartar the avartar to set
     */
    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
     
}
