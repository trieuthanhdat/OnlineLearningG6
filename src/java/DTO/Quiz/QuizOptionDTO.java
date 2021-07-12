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
public class QuizOptionDTO implements Serializable{
    private int questionNo;
    private String content;
    private String optionLetter;
    private boolean isCorrect;

    public QuizOptionDTO() {
    }

    public QuizOptionDTO(int questionNo, String content, String optionLetter, boolean isCorrect) {
        this.questionNo = questionNo;
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

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public String getContent() {
        return content;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }
    
    
}
