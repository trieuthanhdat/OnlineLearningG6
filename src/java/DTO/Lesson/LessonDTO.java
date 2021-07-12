package DTO.Lesson;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class LessonDTO implements Serializable{
    private int lessonID;
    private int subjectID;
    private String name;
    private int order;
    private String type;
    private int topicID;
    private boolean status;
    private LessonDetailsDTO details;

    public LessonDTO() {
    }

    public LessonDTO(int lessonID, int subjectID, String name, int order, String type, int topicID, boolean status, LessonDetailsDTO details) {
        this.lessonID = lessonID;
        this.subjectID = subjectID;
        this.name = name;
        this.order = order;
        this.type = type;
        this.topicID = topicID;
        this.status = status;
        this.details = details;
    }   

    /**
     * @return the lessonID
     */
    public int getLessonID() {
        return lessonID;
    }

    /**
     * @param lessonID the lessonID to set
     */
    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    /**
     * @return the subjectID
     */
    public int getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the details
     */
    public LessonDetailsDTO getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(LessonDetailsDTO details) {
        this.details = details;
    }   

    /**
     * @return the topicID
     */
    public int getTopicID() {
        return topicID;
    }

    /**
     * @param topicID the topicID to set
     */
    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }
}
