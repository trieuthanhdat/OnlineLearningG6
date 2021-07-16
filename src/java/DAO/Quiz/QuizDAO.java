/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Quiz;

import DTO.Quiz.QuizDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class QuizDAO implements Serializable{
    
    private List<QuizDTO> quiz = null;
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeConnection() throws SQLException {
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
    
    public boolean createQuiz (QuizDTO quiz) throws NamingException, SQLException {
        boolean result = false;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Quiz(subjectID, name, numOfQuestions, duration, passRate, level, status) "
                        + " VALUES(?,?,?,?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quiz.getSubjectID());
                stm.setString(2, quiz.getName());
                stm.setInt(3, quiz.getNumOfQuestions());
                stm.setTime(4, quiz.getDuration());
                stm.setDouble(5, quiz.getPassRate());
                stm.setString(6, quiz.getLevel());
                stm.setBoolean(7, quiz.isStatus());
                
                int row = stm.executeUpdate();
                
                if (row > 0) {
                    result = true;
                }
            }
        } finally {
            
        }
        return result;
    }
    
    public QuizDTO getQuiz (int quizID) throws NamingException, SQLException {
        QuizDTO dto = null;
        
         try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT subjectID, name, numOfQuestions, duration, passRate, level, status " 
                            +"FROM Quiz "
                        + " WHERE quizID = ?";  
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int subjectID = rs.getInt("subjectID");
                    String name = rs.getString("name");
                    int numOfQuestion = rs.getInt("numOfQuestion");
                    Time duration = rs.getTime("duration");
                    double passRate = rs.getDouble("passRate");
                    String level = rs.getString("level");
                    boolean status = rs.getBoolean("status");
                    dto = new QuizDTO(quizID, subjectID, name, numOfQuestion, duration, passRate, level, status);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public int getQuizListSize () throws NamingException, SQLException {
        quiz = new ArrayList();
        QuizDTO dto = null;
        
         try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT * " 
                            +"FROM Quiz ";  
                
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int quizID = rs.getInt("quizID");
                    int subjectID = rs.getInt("subjectID");
                    String name = rs.getString("name");
                    int numOfQuestion = rs.getInt("numOfQuestion");
                    Time duration = rs.getTime("duration");
                    double passRate = rs.getDouble("passRate");
                    String level = rs.getString("level");
                    boolean status = rs.getBoolean("status");
                    dto = new QuizDTO(quizID, subjectID, name, numOfQuestion, duration, passRate, level, status);
                    quiz.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
         return quiz.size();
    }
    
    public boolean removeQuiz (int quizID) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            
            if (con != null) {
                String sql = "DELETE FROM Quiz "
                        + " WHERE quizID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                
                int row = stm.executeUpdate();
                
                if (row > 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean editQuiz (QuizDTO quiz) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            
            if (con != null) {
                String sql = "UPDATE Quiz SET name=?, duration=?, passRate=? "
                        + " WHERE quizID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, quiz.getName());
                stm.setTime(2, quiz.getDuration());
                stm.setDouble(3, quiz.getPassRate());
                stm.setInt(4, quiz.getQuizID());
                
                int row = stm.executeUpdate();
                
                if (row > 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
}
