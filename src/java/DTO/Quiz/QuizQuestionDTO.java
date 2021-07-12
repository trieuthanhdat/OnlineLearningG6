/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Quiz;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class QuizQuestionDTO implements Serializable{
    private int questionNo;
    private int quizID;
    private int questionID;
    private String content;
    private String explanation;
    private String level;
    private String mediaLink;
    private boolean status;
    
    public QuizQuestionDTO() {
    }

    public QuizQuestionDTO(int questionNo, int quizID, int questionID, String content, String explanation, String level, String mediaLink, boolean status) {
        this.questionNo = questionNo;
        this.quizID = quizID;
        this.questionID = questionID;
        this.content = content;
        this.explanation = explanation;
        this.level = level;
        this.mediaLink = mediaLink;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public int getQuizID() {
        return quizID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getContent() {
        return content;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getLevel() {
        return level;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExplanation(String explaination) {
        this.explanation = explaination;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }
    
    
}
