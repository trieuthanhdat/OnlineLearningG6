/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Answer;

import DTO.Answer.AnswerDTO;
import DTO.Quiz.QuizOptionDTO;
import DTO.Answer.UserAnswerDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

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
                String sql = "SELECT UserAnswer, Score FROM UserAnswer "
                        + " WHERE userID = ? AND quizID = ? ";
                stm = con.prepareStatement(sql);

                stm.setString(1, userID);
                stm.setInt(2, quizID);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String user_answer = rs.getString("UserAnswer");
                    ArrayList<AnswerDTO> list = new ArrayList<>();
                    String[] answer = user_answer.split(",");
                    for (String tmpAnswer : answer) {
                        String[] tmp = tmpAnswer.split(":");
                        AnswerDTO dto = new AnswerDTO(tmp[1], 0, Integer.parseInt(tmp[0]));
                        list.add(dto);
                    }
                    double score = rs.getDouble("Score");
                    userDidQuiz = new UserAnswerDTO(userID, quizID, list, score);
                }
            }
        } finally {
            closeConnection();
        }
        return userDidQuiz;
    }

    public List<AnswerDTO> copyAnswer(List<QuizOptionDTO> options, double point) {
        List<AnswerDTO> list = new ArrayList();
        for (QuizOptionDTO option : options) {
            if (option.isIsCorrect()) {
                AnswerDTO dto = new AnswerDTO(option.getOptionLetter(), point, option.getQuestionNo());
                list.add(dto);
            }
        }
        return list;
    }

    public double compareAnswer(List<AnswerDTO> answers, List<AnswerDTO> rightAnswers) {
        double total = 0;
        for (AnswerDTO answer : answers) {
            for (AnswerDTO right : rightAnswers) {
                if (answer.getQuestionNo() == right.getQuestionNo()) {
                    if (answer.getAnswer().equals(right.getAnswer())) {
                        total += right.getPoint();
                    }
                }
            }
        }
        return total;
    }

    public String createString(List<AnswerDTO> answers) {
        String result =  null;
        for (AnswerDTO answer : answers) {
            result += "," + answer.getQuestionNo() + ":" + answer.getAnswer();
        }
        return result;
    }
    
    public boolean saveToServer (UserAnswerDTO dto, String answer) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO UserAnswer (quizID, userID, UserAnswer, Score) "
                        + " VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getQuizID());
                stm.setString(2, dto.getUserID());
                stm.setString(3, answer);
                stm.setDouble(4, dto.getScore());
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
