package DTO.Subject;

import DTO.SubjectRegistration.PackageDTO;
import java.io.Serializable;
import java.util.List;

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
    private List<PackageDTO> packages;
    private boolean status;
    private boolean featureFlag;

    public SubjectDTO() {
    }

    public SubjectDTO(int subjectID, int subjectCategoryID, String title, int numOfLessons, String thumbnail, String ownerID, String briefInfo, SubjectDetailsDTO details, List<PackageDTO> packages, boolean status, boolean featureFlag) {
        this.subjectID = subjectID;
        this.subjectCategoryID = subjectCategoryID;
        this.title = title;
        this.numOfLessons = numOfLessons;
        this.thumbnail = thumbnail;
        this.ownerID = ownerID;
        this.briefInfo = briefInfo;
        this.details = details;
        this.packages = packages;
        this.status = status;
        this.featureFlag = featureFlag;
    }   
    
    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getSubjectCategoryID() {
        return subjectCategoryID;
    }

    public void setSubjectCategoryID(int subjectCategoryID) {
        this.subjectCategoryID = subjectCategoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfLessons() {
        return numOfLessons;
    }

    public void setNumOfLessons(int numOfLessons) {
        this.numOfLessons = numOfLessons;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public SubjectDetailsDTO getDetails() {
        return details;
    }

    public void setDetails(SubjectDetailsDTO details) {
        this.details = details;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFeatureFlag() {
        return featureFlag;
    }

    public void setFeatureFlag(boolean featureFlag) {
        this.featureFlag = featureFlag;
    }    

    public List<PackageDTO> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageDTO> packages) {
        this.packages = packages;
    }        
}

