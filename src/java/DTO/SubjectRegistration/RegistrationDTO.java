package DTO.SubjectRegistration;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class RegistrationDTO implements Serializable{
    private int registrationID;
    private int subjectID;
    private String userID;
    private Date registrationTime;
    private int totalCost;
    private Date validFrom;
    private Date validTo;
    private int packageID;
    private boolean status;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int registrationID, int subjectID, String userID, Date registrationTime, int totalCost, Date validFrom, Date validTo, int packageID, boolean status) {
        this.registrationID = registrationID;
        this.subjectID = subjectID;
        this.userID = userID;
        this.registrationTime = registrationTime;
        this.totalCost = totalCost;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.packageID = packageID;
        this.status = status;
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
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the validFrom
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * @return the validTo
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * @param validTo the validTo to set
     */
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
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
}
