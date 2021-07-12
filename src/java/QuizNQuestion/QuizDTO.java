/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizNQuestion;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author Admin
 */
public class QuizDTO implements Serializable{
    private int quizID;
    private int subjectID;
    private String name;
    private int numOfQuestions;
    private Time duration;
    private double passRate;
    private String level;
    private boolean status;

    public QuizDTO() {
    }

    public QuizDTO(int quizID, int subjectID, String name, int numOfQuestions, Time duration, double passRate, String level, boolean status) {
        this.quizID = quizID;
        this.subjectID = subjectID;
        this.name = name;
        this.numOfQuestions = numOfQuestions;
        this.duration = duration;
        this.passRate = passRate;
        this.level = level;
        this.status = status;
    }

    public int getQuizID() {
        return quizID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public String getName() {
        return name;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public Time getDuration() {
        return duration;
    }

    public double getPassRate() {
        return passRate;
    }

    public String getLevel() {
        return level;
    }

    public boolean isStatus() {
        return status;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setPassRate(double passRate) {
        this.passRate = passRate;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
