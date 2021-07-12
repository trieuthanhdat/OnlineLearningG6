/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserAnswerDTO implements Serializable{
    private String userID;
    private int quizID;
    private String[] answer;
    private double score;

    public UserAnswerDTO() {
    }

    public UserAnswerDTO(String userID, int quizID, String[] answer, double score) {
        this.userID = userID;
        this.quizID = quizID;
        this.answer = answer;
        this.score = score;
    }

    public String getUserID() {
        return userID;
    }

    public int getQuizID() {
        return quizID;
    }

    public String[] getAnswer() {
        return answer;
    }

    public double getScore() {
        return score;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
