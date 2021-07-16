/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Quiz;

/**
 *
 * @author Admin
 */
public class QuizErrorDTO {
    private String QuestionShortage;
    private String QuestionLevelShortage;
    private String InvalidQuizName;
    private String UnreasonablePassRate;
    private String UnreasonableTime;
    private String CreateError;

    public QuizErrorDTO() {
    }

    public void setQuestionLevelShortage(String QuestionLevelShortage) {
        this.QuestionLevelShortage = QuestionLevelShortage;
    }

    public void setCreateError(String CreateError) {
        this.CreateError = CreateError;
    }

    public String getCreateError() {
        return CreateError;
    }

    public String getQuestionLevelShortage() {
        return QuestionLevelShortage;
    }

    public String getQuestionShortage() {
        return QuestionShortage;
    }

    public String getInvalidQuizName() {
        return InvalidQuizName;
    }

    public String getUnreasonablePassRate() {
        return UnreasonablePassRate;
    }

    public String getUnreasonableTime() {
        return UnreasonableTime;
    }

    public void setQuestionShortage(String QuestionShortage) {
        this.QuestionShortage = QuestionShortage;
    }

    public void setInvalidQuizName(String InvalidQuizName) {
        this.InvalidQuizName = InvalidQuizName;
    }

    public void setUnreasonablePassRate(String UnreasonablePassRate) {
        this.UnreasonablePassRate = UnreasonablePassRate;
    }

    public void setUnreasonableTime(String UnreasonableTime) {
        this.UnreasonableTime = UnreasonableTime;
    }
    
    
}
