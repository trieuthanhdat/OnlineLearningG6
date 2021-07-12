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
public class MyRegistrationDTO implements Serializable{
    private int registrationID;
    private int subjectID;
    private String userID;
    private Date registrationTime;
    private float totalCost;
    private Date vaalidTo;
    private Date vaalidFrom;
    private boolean  status;
    private int packageID;

    public MyRegistrationDTO() {
    }

    public MyRegistrationDTO(int registrationID, int subjectID, String userID, Date registrationTime, float totalCost, Date vaalidTo, Date vaalidFrom, boolean status, int packageID) {
        this.registrationID = registrationID;
        this.subjectID = subjectID;
        this.userID = userID;
        this.registrationTime = registrationTime;
        this.totalCost = totalCost;
        this.vaalidTo = vaalidTo;
        this.vaalidFrom = vaalidFrom;
        this.status = status;
        this.packageID = packageID;
    }

    
    /**
     * @return the registrationID
     */
    public int getRegistrationID() {
        return registrationID;
    }

    /**
     * @param registrationID the registrationID to set
     */
    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    /**
     * @return the subjectID
     */
    public int getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the registrationTime
     */
    public Date getRegistrationTime() {
        return registrationTime;
    }

    /**
     * @param registrationTime the registrationTime to set
     */
    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    /**
     * @return the totalCost
     */
    public float getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the vaalidTo
     */
    public Date getVaalidTo() {
        return vaalidTo;
    }

    /**
     * @param vaalidTo the vaalidTo to set
     */
    public void setVaalidTo(Date vaalidTo) {
        this.vaalidTo = vaalidTo;
    }

    /**
     * @return the vaalidFrom
     */
    public Date getVaalidFrom() {
        return vaalidFrom;
    }

    /**
     * @param vaalidFrom the vaalidFrom to set
     */
    public void setVaalidFrom(Date vaalidFrom) {
        this.vaalidFrom = vaalidFrom;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the packageID
     */
    public int getPackageID() {
        return packageID;
    }

    /**
     * @param packageID the packageID to set
     */
    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    
}
