package DTO.Marketing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class StatisDTO {

    private int numberofsubject;
    private int numberofregistion;
    private int totalCost;
    private int subjecttop5;
    private int registrationtop5;

    public StatisDTO() {
    }

    public StatisDTO(int numberofsubject, int numberofregistion, int totalCost, int subjecttop5, int registrationtop5) {
        this.numberofsubject = numberofsubject;
        this.numberofregistion = numberofregistion;
        this.totalCost = totalCost;
        this.subjecttop5 = subjecttop5;
        this.registrationtop5 = registrationtop5;
    }

    /**
     * @return the numberofsubject
     */
    public int getNumberofsubject() {
        return numberofsubject;
    }

    /**
     * @param numberofsubject the numberofsubject to set
     */
    public void setNumberofsubject(int numberofsubject) {
        this.numberofsubject = numberofsubject;
    }

    /**
     * @return the numberofregistion
     */
    public int getNumberofregistion() {
        return numberofregistion;
    }

    /**
     * @param numberofregistion the numberofregistion to set
     */
    public void setNumberofregistion(int numberofregistion) {
        this.numberofregistion = numberofregistion;
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
     * @return the subjecttop5
     */
    public int getSubjecttop5() {
        return subjecttop5;
    }

    /**
     * @param subjecttop5 the subjecttop5 to set
     */
    public void setSubjecttop5(int subjecttop5) {
        this.subjecttop5 = subjecttop5;
    }

    /**
     * @return the registrationtop5
     */
    public int getRegistrationtop5() {
        return registrationtop5;
    }

    /**
     * @param registrationtop5 the registrationtop5 to set
     */
    public void setRegistrationtop5(int registrationtop5) {
        this.registrationtop5 = registrationtop5;
    }

}
