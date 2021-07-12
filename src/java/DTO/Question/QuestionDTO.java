/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Question;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class QuestionDTO implements Serializable{
    private int questionID;
    private String mediaLink;
    private String content;
    private int lessionID;
    private boolean status;
    private String explaination;
    private String level;

    public QuestionDTO() {
    }

    public QuestionDTO(int questionID, String mediaLink, String content, int lessionID, boolean status, String explaination, String level) {
        this.questionID = questionID;
        this.mediaLink = mediaLink;
        this.content = content;
        this.lessionID = lessionID;
        this.status = status;
        this.explaination = explaination;
        this.level = level;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public String getContent() {
        return content;
    }

    public int getLessionID() {
        return lessionID;
    }

    public boolean isStatus() {
        return status;
    }

    public String getExplaination() {
        return explaination;
    }

    public String getLevel() {
        return level;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLessionID(int lessionID) {
        this.lessionID = lessionID;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
}
