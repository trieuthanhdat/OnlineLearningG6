/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Answer;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AnswerDTO implements Serializable{
    private String answer;
    private double point;
    private int questionNo;

    public AnswerDTO(String answer, double point, int questionNo) {
        this.answer = answer;
        this.point = point;
        this.questionNo = questionNo;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getAnswer() {
        return answer;
    }

    public double getPoint() {
        return point;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPoint(double point) {
        this.point = point;
    }
    
    
}
