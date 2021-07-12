/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.User;

import DTO.User.UserAnswerDTO;
import utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class UserAnswerDAO implements Serializable {

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

    public UserAnswerDTO checkUserAnswerExist(int quizID, String userID) throws NamingException, SQLException {
        UserAnswerDTO userDidQuiz = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT user_answer, score FROM UserAnswer "
                        + " WHERE userID = ? AND quizID = ? ";
                stm = con.prepareStatement(sql);
                
                stm.setString(1, userID);
                stm.setInt(2, quizID);
                rs = stm.executeQuery();
                
                if (rs.next()){
                    String user_answer = rs.getString("user_answer");
                    String[] answer = user_answer.split(","); 
                    double score = rs.getDouble("score");
                    userDidQuiz = new UserAnswerDTO(userID, quizID, answer, score);
                }
            }
        } finally {
            closeConnection();
        }
        return userDidQuiz;
    }
    
    public UserAnswerDTO saveAnswer (String userID, int quizID, String answer) {
        return null;
        
    }
    
}
