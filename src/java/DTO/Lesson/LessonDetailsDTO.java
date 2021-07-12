package DTO.Lesson;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class LessonDetailsDTO implements Serializable{
    private int quizID;
    private String videoLink;
    private String htmlContent;

    public LessonDetailsDTO() {
    }

    public LessonDetailsDTO(int quizID, String videoLink, String htmlContent) {
        this.quizID = quizID;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
    }

    /**
     * @return the quizID
     */
    public int getQuizID() {
        return quizID;
    }

    /**
     * @param quizID the quizID to set
     */
    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    /**
     * @return the videoLink
     */
    public String getVideoLink() {
        return videoLink;
    }

    /**
     * @param videoLink the videoLink to set
     */
    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    /**
     * @return the htmlContent
     */
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * @param htmlContent the htmlContent to set
     */
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }    
}
