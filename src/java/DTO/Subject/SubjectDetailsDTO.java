package DTO.Subject;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class SubjectDetailsDTO implements Serializable {
    private String tagLine;
    private String description;

    public SubjectDetailsDTO() {
    }

    public SubjectDetailsDTO(String tagLine, String description) {
        this.tagLine = tagLine;
        this.description = description;
    }

    /**
     * @return the tagLine
     */
    public String getTagLine() {
        return tagLine;
    }

    /**
     * @param tagLine the tagLine to set
     */
    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }       
}
