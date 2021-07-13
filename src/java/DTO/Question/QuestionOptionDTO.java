/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class QuestionOptionDTO implements Serializable{
    private int questionID;
    private String content;
    private String optionLetter;
    private boolean isCorrect;

    public QuestionOptionDTO() {
    }

    public QuestionOptionDTO(int questionID, String content, String optionLetter, boolean isCorrect) {
        this.questionID = questionID;
        this.content = content;
        this.optionLetter = optionLetter;
        this.isCorrect = isCorrect;
    }

    public String getOptionLetter() {
        return optionLetter;
    }

    public void setOptionLetter(String optionLetter) {
        this.optionLetter = optionLetter;
    }
    
    public int getQuestionID() {
        return questionID;
    }

    public String getContent() {
        return content;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
}
