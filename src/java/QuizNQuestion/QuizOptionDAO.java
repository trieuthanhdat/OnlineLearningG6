/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizNQuestion;

import group6.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class QuizOptionDAO implements Serializable {

    private List<QuizOptionDTO> newQuizOption = null;
    private List<QuizOptionDTO> quizOption = null;
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

    public void getQuizOption(List<QuizQuestionDTO> question) throws SQLException, NamingException {
        newQuizOption = new ArrayList();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (QuizQuestionDTO quiz : question) {
                    String sql = "SELECT * FROM QuestionOption "
                            + " WHERE questionID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, quiz.getQuestionID());
                    
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String content = rs.getString("content");
                        String optionLetter = rs.getString("optionLetter");
                        boolean isCorrect = rs.getBoolean("isCorrect");
                        QuizOptionDTO dto = new QuizOptionDTO(quiz.getQuestionNo(), content, optionLetter, isCorrect);
                        newQuizOption.add(dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public boolean exportOption () throws NamingException, SQLException {
        boolean result = false;
        int row = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (QuizOptionDTO option : newQuizOption) {
                    String sql = "INSERT INTO QuizQuestionOption(questionNo, optionLetter, content, isCorrect) "
                            + " VALUES(?,?,?) ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, option.getQuestionNo());
                    stm.setString(2, option.getOptionLetter());
                    stm.setString(3, option.getContent());
                    stm.setBoolean(4, option.isIsCorrect());
                    row += stm.executeUpdate();
                    if (row == newQuizOption.size()) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public void importOption (List<QuizQuestionDTO> questionList) throws NamingException, SQLException {
        quizOption = new ArrayList();
        boolean result = false;
        int row = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (QuizQuestionDTO question : questionList) {
                    String sql = "SELECT * FROM QuizQuestionOption "
                            + " WHERE questionNo = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, question.getQuestionNo());
                    
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int questionNo = rs.getInt("questionNo");
                        String content = rs.getString("content");
                        String optionLetter = rs.getString("optionLetter");
                        boolean isCorrect = rs.getBoolean("isCorrect");
                        QuizOptionDTO dto = new QuizOptionDTO(questionNo, content, optionLetter, isCorrect);
                        quizOption.add(dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<QuizOptionDTO> getOption () {
        return quizOption;
    }
    
    
}
