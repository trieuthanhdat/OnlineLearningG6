package group6.entity.subject;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class SubjectDTO implements Serializable {
    private int subjectID;
    private int subjectCategoryID;
    private String title;
    private int numOfLessons;
    private String thumbnail;
    private String ownerID;
    private String briefInfo;
    private SubjectDetailsDTO details;
    private boolean status;

    public SubjectDTO() {
    }

    public SubjectDTO(int subjectID, int subjectCategoryID, String title, int numOfLessons, String thumbnail, String ownerID, String briefInfo, SubjectDetailsDTO details, boolean status) {
        this.subjectID = subjectID;
        this.subjectCategoryID = subjectCategoryID;
        this.title = title;
        this.numOfLessons = numOfLessons;
        this.thumbnail = thumbnail;
        this.ownerID = ownerID;
        this.briefInfo = briefInfo;
        this.details = details;
        this.status = status;
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
     * @return the subjectCategoryID
     */
    public int getSubjectCategoryID() {
        return subjectCategoryID;
    }

    /**
     * @param subjectCategoryID the subjectCategoryID to set
     */
    public void setSubjectCategoryID(int subjectCategoryID) {
        this.subjectCategoryID = subjectCategoryID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the numOfLessons
     */
    public int getNumOfLessons() {
        return numOfLessons;
    }

    /**
     * @param numOfLessons the numOfLessons to set
     */
    public void setNumOfLessons(int numOfLessons) {
        this.numOfLessons = numOfLessons;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the ownerID
     */
    public String getOwnerID() {
        return ownerID;
    }

    /**
     * @param ownerID the ownerID to set
     */
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * @return the briefInfo
     */
    public String getBriefInfo() {
        return briefInfo;
    }

    /**
     * @param briefInfo the briefInfo to set
     */
    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    /**
     * @return the details
     */
    public SubjectDetailsDTO getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(SubjectDetailsDTO details) {
        this.details = details;
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
