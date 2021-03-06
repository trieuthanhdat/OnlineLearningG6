/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.SubjectRegistration;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class PackageDTO implements Serializable{
    private int packageID;
    private int subjectID;
    private String name;
    private int accessduration;
    private boolean status;
    private float  listPrice;
    private float salePrice;
    private String description;

    public PackageDTO() {
    }

    public PackageDTO(int packageID, int subjectID, String name, int accessduration, boolean status, float listPrice, float salePrice, String description) {
        this.packageID = packageID;
        this.subjectID = subjectID;
        this.name = name;
        this.accessduration = accessduration;
        this.status = status;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.description = description;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the accessduration
     */
    public int getAccessduration() {
        return accessduration;
    }

    /**
     * @param accessduration the accessduration to set
     */
    public void setAccessduration(int accessduration) {
        this.accessduration = accessduration;
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
     * @return the listPrice
     */
    public float getListPrice() {
        return listPrice;
    }

    /**
     * @param listPrice the listPrice to set
     */
    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * @return the salePrice
     */
    public float getSalePrice() {
        return salePrice;
    }

    /**
     * @param salePrice the salePrice to set
     */
    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
}
