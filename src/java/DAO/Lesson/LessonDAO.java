package DAO.Lesson;

import DTO.Lesson.LessonDTO;
import DTO.Lesson.LessonDetailsDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class LessonDAO implements Serializable{
    
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeConnection() 
            throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }    
    
    public LessonDAO() 
            throws NamingException, SQLException {
        getAllLessons();
    }
    
    private List<LessonDTO> lessonsList = new ArrayList<>();
    
    private void getAllLessons() 
            throws SQLException, NamingException {
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT LessonID, SubjectID, Name, "
                           + "LessonOrder, Type, TopicID, Status "
                           + "FROM Lesson";
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while(rs.next()) {
                    int lessonID = rs.getInt("LessonID");
                    int subjectID = rs.getInt("SubjectID");
                    String name = rs.getString("Name");
                    int order = rs.getInt("LessonOrder");
                    String type = rs.getString("Type");
                    int topicID = rs.getInt("TopicID");
                    boolean status = rs.getBoolean("Status");
                    
                    lessonsList.add(new LessonDTO(lessonID, subjectID, name, order, type, topicID, status, null));
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<LessonDTO> getCurrLessons(int subjectID) {        
        List<LessonDTO> currLessonList = getLessonsBySubjectID(subjectID);
        List<LessonDTO> resultList = new ArrayList<>();
        
        for (LessonDTO lesson : currLessonList) {
            if (lesson.isStatus()) {
                resultList.add(lesson);
            }
        }
        
        return currLessonList;        
    }
    
    public LessonDTO getCurrLessonDetails(int subjectID, int topicOrder, int lessonOrder) 
            throws NamingException, SQLException {
        
        LessonDTO currLesson = null;
        try {
            List<LessonDTO> lessons = getLessonsBySubjectID(subjectID);
            int currTopicID = 0;
            // remove disabled lesson and get topicID of current lesson.
            for (LessonDTO lesson : lessons) {
                if (!lesson.isStatus()) {
                    lessons.remove(lesson);
                } else if (lesson.getTopicID() == 0 && lesson.getOrder() == topicOrder) {
                    currTopicID = lesson.getLessonID();
                    break;
                }
            }
            // after traversing through the list and don't find topic return null
            if (currTopicID == 0) return currLesson;
            // check for the right topicID and order.
            for (LessonDTO lesson : lessons) {
                if (lesson.getTopicID() == currTopicID && lesson.getOrder() == lessonOrder) {
                    currLesson = lesson;
                    break;
                }
            }
            
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT QuizID, VideoLink, HtmlContent "
                           + "FROM LessonDetails "
                           + "WHERE LessonID = ?";
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, currLesson.getLessonID());
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quizID = rs.getInt("QuizID");
                    String videoLink = rs.getString("VideoLink");
                    String htmlContent = rs.getString("HtmlContent");
                    
                    LessonDetailsDTO details = new LessonDetailsDTO(quizID, videoLink, htmlContent);
                    currLesson.setDetails(details);
                }
            }
        } finally {
            closeConnection();
        }
        return currLesson;
    }
    
    public LessonDTO getLessonByID(int lessonID) {
        LessonDTO searchLesson = null;
        for (LessonDTO lesson : lessonsList) {
            if (lesson.getLessonID() == lessonID) {
                searchLesson = lesson;
            }
        }
        return searchLesson;
    }
    
    public LessonDetailsDTO getLessonDetailsByID(int lessonID) 
            throws NamingException, SQLException {
        LessonDetailsDTO details = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT QuizID, VideoLink, HtmlContent "
                           + "FROM LessonDetails "
                           + "WHERE LessonID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, lessonID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quizID = rs.getInt("QuizID");
                    String videoLink = rs.getString("VideoLink");
                    String htmlContent = rs.getString("HtmlContent");
                    
                    details = new LessonDetailsDTO(quizID, videoLink, htmlContent);                    
                }
            }
        } finally {
            closeConnection();
        }
        return details;
    }
    
    public boolean checkSubjectHasValidLessons(int subjectID) {        
        List<LessonDTO> currLessons = getCurrLessons(subjectID); // get valid lessons with subjectID
        
        if (currLessons.isEmpty()) {
            return false;
        }
        
        for (LessonDTO lesson : currLessons) {
            if (lesson.getSubjectID() == subjectID && lesson.getType().equals("Subject Topic")) {
                if (lesson.getOrder() == currLessons.size()) {
                    return false;
                }
                // order = index + 1 and this checks if the lesson after the topic lesson is a normal lesson
                else if (!currLessons.get(lesson.getOrder()).getType().equals("Lesson")) { 
                    return false;
                }                
            }
        }
        return true;
    }
        
    public List<LessonDTO> getLessonsBySubjectID(int subjectID) {
        List<LessonDTO> validLessons = new ArrayList<>();
        List<LessonDTO> invalidLessons = new ArrayList<>();
        
        for (LessonDTO lesson : lessonsList) {
            if (lesson.getSubjectID() == subjectID) {
                if (lesson.isStatus()) 
                    validLessons.add(lesson);
                else invalidLessons.add(lesson);
            }
        }

        List<LessonDTO> topicList = new ArrayList<>();
        
        // after this topicList only contains topic following order and lessons only contains lessons.
        int minOrderForTopic = 1;
        for (LessonDTO lesson : validLessons) {
            for (LessonDTO lessonItem : validLessons) {
                if (lessonItem.getType().equals("Subject Topic") && lessonItem.getOrder() == minOrderForTopic) {
                    minOrderForTopic++;
                    topicList.add(lessonItem);                    
                }
            }
        }
        
        List<LessonDTO> resultList = new ArrayList<>();
        
        int minOrderForLesson = 1;
        for (LessonDTO topic : topicList) {
            resultList.add(topic);
            
            for (LessonDTO lesson : validLessons) {
                for (LessonDTO lessonItem : validLessons) {
                    if (lessonItem.getTopicID() == topic.getLessonID() && lessonItem.getOrder() == minOrderForLesson) {
                        minOrderForLesson++;
                        resultList.add(lessonItem);                        
                    }
                } 
            }
            
            for (LessonDTO lesson : invalidLessons) {
                if (lesson.getTopicID() == topic.getLessonID()) {
                    resultList.add(lesson);
                }
            }
            
            minOrderForLesson = 1;
        }
        
        
        
        return resultList;
    }
    
    public List<LessonDTO> getTopicsBySubjectID(int subjectID) {
        List<LessonDTO> topics = new ArrayList<>();
                
        for (LessonDTO lesson : lessonsList) {            
            if (lesson.getSubjectID() == subjectID && lesson.isStatus() && lesson.getType().equals("Subject Topic")) {
                topics.add(lesson);
            }
        }
        
        List<LessonDTO> resultList = new ArrayList<>();
        int minOrder = 1;
        for (LessonDTO lesson : topics) {
            for (LessonDTO lessonItem : topics) {
                if (lessonItem.getOrder() == minOrder) {
                    minOrder++;
                    resultList.add(lessonItem);                    
                }
            }
        }
        
        return resultList;
    }
    
    public List<LessonDTO> getLessonsByTopicID(int topicID) {
        List<LessonDTO> resultList = new ArrayList<>();
        
        for (LessonDTO lesson : lessonsList) {
            if (lesson.getTopicID() == topicID && lesson.isStatus()) {
                resultList.add(lesson);
            }
        }
        
        return resultList;
    }
    
    public boolean checkValidOrderForAdding(int subjectID, int order, int topicID) {
        boolean result = false;
        
        if (topicID == 0) { // new lesson is a subject topic
            List<LessonDTO> lessons = getTopicsBySubjectID(subjectID);
            if (order <= lessons.size() + 1) {
                result = true;
            }
        } else {
            List<LessonDTO> lessons = getLessonsByTopicID(topicID);
            if (order <= lessons.size() + 1) {
                result = true;
            }
        }        
        
        return result;
    }
    
    public boolean checkValidOrderForUpdating(int subjectID, int order, int oldTopicID, int newTopicID) {
        boolean result = false;
        
        if (newTopicID == 0) { // new lesson is a subject topic
            List<LessonDTO> lessons = getTopicsBySubjectID(subjectID);
            if (order <= lessons.size()) {
                result = true;
            }
        } else {
            if (oldTopicID == newTopicID) {
                List<LessonDTO> lessons = getLessonsByTopicID(newTopicID);
                if (order <= lessons.size()) {
                    result = true;
                }
            } else {
                List<LessonDTO> lessons = getLessonsByTopicID(newTopicID);
                if (order <= lessons.size() + 1) {
                    result = true;
                }
            }
        }        
        
        return result;
    }
    
    private boolean checkDuplicateOrder(int subjectID, int order, int topicID) {
        boolean result = false;
        
        if (topicID == 0) {
            List<LessonDTO> topicsInSubject = getTopicsBySubjectID(subjectID);
            for (LessonDTO topic : topicsInSubject) {
                if (topic.getOrder() == order) {
                    return true;
                }
            }
        } else {
            List<LessonDTO> lessonsInTopic = getLessonsByTopicID(topicID);
            for (LessonDTO lesson : lessonsInTopic) {
                if (lesson.getOrder() == order) {
                    return true;
                }
            }                   
        }
        
        return result;
    }
    
    public boolean addNewLesson(LessonDTO newLesson) 
            throws NamingException, SQLException {
        boolean addResult = false;
        int subID = newLesson.getSubjectID();
        int order = newLesson.getOrder();
        int topicID = newLesson.getTopicID();
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Lesson (LessonID, SubjectID, Name, "
                           + "LessonOrder, Type, TopicID, Status) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, lessonsList.size() + 1);
                stm.setInt(2, subID);
                stm.setString(3, newLesson.getName());
                stm.setInt(4, order);
                stm.setString(5, newLesson.getType());
                stm.setInt(6, topicID);
                stm.setBoolean(7, newLesson.isStatus());
                
                int result1 = stm.executeUpdate();
                
                sql = "INSERT INTO LessonDetails (LessonID, QuizID, VideoLink, HtmlContent) "
                    + "VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, lessonsList.size() + 1);
                
                if (newLesson.getDetails().getQuizID() == 0) {
                    stm.setNull(2, Types.INTEGER);
                } else stm.setInt(2, newLesson.getDetails().getQuizID());              
                
                if (newLesson.getDetails().getVideoLink().isEmpty()) {
                    stm.setNull(3, Types.VARCHAR);
                } else stm.setString(3, newLesson.getDetails().getVideoLink());
                
                if (newLesson.getDetails().getHtmlContent().isEmpty()) {
                    stm.setNull(4, Types.VARCHAR);
                } else stm.setString(4, newLesson.getDetails().getHtmlContent());
                
                int result2 = stm.executeUpdate();
                
                if (result1 != 0 && result2 != 0) {
                    if (checkDuplicateOrder(subID, order, topicID)) {
                        List<LessonDTO> lessons;
                        // get an ordered list of topics or lessons based on topicID
                        if (newLesson.getTopicID() == 0) {
                            lessons = getTopicsBySubjectID(subID);                            
                        } else {
                            lessons = getLessonsByTopicID(topicID);
                        }
                        // remove any lesson with order < new lesson's order from list
                        List<LessonDTO> lessonsAfterRemove = new ArrayList<>();
                        for (LessonDTO lesson : lessons) {
                            if (lesson.getOrder() >= order) {
                                lessonsAfterRemove.add(lesson);
                            }
                        }
                        // update the order of every lesson after the newly added lesson
                        for (LessonDTO lesson : lessonsAfterRemove) {                                                    
                            sql = "UPDATE Lesson "
                                + "SET LessonOrder = ? "
                                + "WHERE LessonID = ?";
                            stm = con.prepareStatement(sql);
                            stm.setInt(1, lesson.getOrder() + 1);
                            stm.setInt(2, lesson.getLessonID());                            
                            
                            int updateOrderResult = stm.executeUpdate();
                            if (updateOrderResult == 0) {
                                
                            }
                        }                                                
                    }
                    addResult = true;
                }
            }
        } finally {
            closeConnection();
        }
        return addResult;
    }
    
    public boolean updateLesson(LessonDTO lesson, int oldOrder, int oldTopicID) 
            throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                // change order but doesn't change topic it belongs to
                if (oldTopicID == lesson.getTopicID()) {
                    List<LessonDTO> lessonsFromOldTopic = getLessonsByTopicID(oldTopicID);
                    for (LessonDTO l : lessonsFromOldTopic) {
                        // switch order between 2 lessons
                        if (l.getOrder() == lesson.getOrder()) {
                            String sql = "UPDATE Lesson "
                                       + "SET LessonOrder = ? "
                                       + "WHERE LessonID = ?";
                            
                            stm = con.prepareStatement(sql);
                            stm.setInt(1, oldOrder);
                            stm.setInt(2, l.getLessonID());
                            stm.executeUpdate();
                        }
                    }
                }
                // change topic makes order changes as well
                else if (oldTopicID != lesson.getTopicID()) {
                    List<LessonDTO> lessonsFromOldTopic = getLessonsByTopicID(oldTopicID);
                    for (LessonDTO l : lessonsFromOldTopic) {
                        // push up the order of lessons behind the updated lesson by 1
                        if (l.getOrder() > oldOrder) {
                            String sql = "UPDATE Lesson "
                                       + "SET LessonOrder = ? "
                                       + "WHERE LessonID = ?";
                            stm = con.prepareStatement(sql);
                            stm.setInt(1, l.getOrder() - 1);
                            stm.setInt(2, l.getLessonID());
                            stm.executeUpdate();
                        }
                    }
                    
                    List<LessonDTO> lessonsFromNewTopic = getLessonsByTopicID(lesson.getTopicID());                                             
                    for (LessonDTO l : lessonsFromNewTopic) {
                        // push down the order of lessons behind the updated lesson by 1
                        if (l.getOrder() >= lesson.getOrder()) {
                            String sql = "UPDATE Lesson "
                                       + "SET LessonOrder = ? "
                                       + "WHERE LessonID = ?";
                            stm = con.prepareStatement(sql);
                            stm.setInt(1, l.getOrder() + 1);
                            stm.setInt(2, l.getLessonID());
                            stm.executeUpdate();
                        }
                    }
                }
                
                String sql = "UPDATE Lesson "
                           + "SET Name = ?, TopicID = ?, LessonOrder = ? "
                           + "WHERE LessonID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, lesson.getName());
                stm.setInt(2, lesson.getTopicID());
                stm.setInt(3, lesson.getOrder());
                stm.setInt(4, lesson.getLessonID());
                
                int updateResult1 = stm.executeUpdate();
                
                sql = "UPDATE LessonDetails "
                    + "SET QuizID = ?, VideoLink = ?, HtmlContent = ? "
                    + "WHERE LessonID = ?";
                stm = con.prepareStatement(sql);
                
                if (lesson.getDetails().getQuizID() == 0) {
                    stm.setNull(1, Types.INTEGER);
                } else stm.setInt(1, lesson.getDetails().getQuizID());
                
                if (lesson.getDetails().getVideoLink().isEmpty()) {
                    stm.setNull(2, Types.VARCHAR);
                } else stm.setString(2, lesson.getDetails().getVideoLink());
                
                stm.setString(3, lesson.getDetails().getHtmlContent());
                stm.setInt(4, lesson.getLessonID());
                
                int updateResult2 = stm.executeUpdate();
                
                if (updateResult1 != 0 && updateResult2 != 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
