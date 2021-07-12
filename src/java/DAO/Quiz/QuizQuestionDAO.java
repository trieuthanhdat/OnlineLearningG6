/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import QuizNQuestion.QuestionDTO;
import QuizNQuestion.QuizQuestionDTO;
import group6.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class QuizQuestionDAO implements Serializable {

    private List<QuizQuestionDTO> quizQuestion = null;
    private List<QuizQuestionDTO> newQuizQuestion = null;
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

    public boolean randomQuestion(List<QuestionDTO> questions, String level, int number, int quizID) {
        List<QuestionDTO> hard = new ArrayList();
        List<QuestionDTO> medium = new ArrayList();
        List<QuestionDTO> easy = new ArrayList();

        for (QuestionDTO question : questions) { //distinguish question level
            if (question.getLevel().equals("hard")) {
                hard.add(question);
            }
            if (question.getLevel().equals("medium")) {
                medium.add(question);
            }
            if (question.getLevel().equals("easy")) {
                easy.add(question);
            }
        }

        if (level.equals("hard")) { //quiz level = hard
            int hardNo = (int) (number * 0.20);
            int mediumNo = (int) (number * 0.35);
            int easyNo = number - hardNo - mediumNo;

            copyQuestion(hard, hardNo, quizID);
            copyQuestion(medium, mediumNo, quizID);
            copyQuestion(easy, easyNo, quizID);
            return true;
        }

        if (level.equals("medium")) { //quiz level = medium
            int hardNo = (int) (number * 0.15);
            int mediumNo = (int) (number * 0.4);
            int easyNo = number - hardNo - mediumNo;

            copyQuestion(hard, hardNo, quizID);
            copyQuestion(medium, mediumNo, quizID);
            copyQuestion(easy, easyNo, quizID);
            return true;
        }

        if (level.equals("easy")) { //quiz level = easy
            int hardNo = (int) (number * 0.1);
            int mediumNo = (int) (number * 0.35);
            int easyNo = number - hardNo - mediumNo;

            copyQuestion(hard, hardNo, quizID);
            copyQuestion(medium, mediumNo, quizID);
            copyQuestion(easy, easyNo, quizID);
            return true;
        }

        return false;
    }

    public void copyQuestion(List<QuestionDTO> question, int random, int quizID) { // copy question from list to quiz question
        Random generator = new Random();
        newQuizQuestion = new ArrayList();
        boolean ok;
        for (int i = 0; i < random; i++) {
            ok = true;
            int value = generator.nextInt(question.size());
            for (int j = 0; j < newQuizQuestion.size(); j++) {
                if (newQuizQuestion.get(j).getQuestionID() == question.get(value).getQuestionID()) {
                    i--;
                    ok = false;
                }
            }
            if (ok) {
                QuizQuestionDTO dto = new QuizQuestionDTO(0, quizID, question.get(value).getQuestionID(), question.get(value).getContent(), question.get(value).getExplaination(), question.get(value).getLevel(), question.get(value).getMediaLink(), true);
                newQuizQuestion.add(dto);
            }
        }
    }

    public boolean exportQuizQuestion() throws NamingException, SQLException { //insert into database
        boolean result = false;
        int row = 0;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                for (int i = 0; i < newQuizQuestion.size(); i++) {
                    String sql = "INSERT INTO QuizQuestion(quizID, questionID, content, explanation, level, mediaLink, status) "
                            + "VALUES (?,?,?,?,?,?,?) ";

                    stm = con.prepareStatement(sql);
                    stm.setInt(2, newQuizQuestion.get(i).getQuizID());
                    stm.setInt(3, newQuizQuestion.get(i).getQuestionID());
                    stm.setString(4, newQuizQuestion.get(i).getContent());
                    stm.setString(5, newQuizQuestion.get(i).getExplanation());
                    stm.setString(6, newQuizQuestion.get(i).getLevel());
                    stm.setString(7, newQuizQuestion.get(i).getMediaLink());
                    stm.setBoolean(8, true);

                    row += stm.executeUpdate();

                    if (row == newQuizQuestion.size()) {
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

    public void importQuizQuestion(int quizID) throws SQLException, NamingException { // take data from database
        quizQuestion = new ArrayList();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionNo, questionID, content, explanation, level, mediaLink, status "
                        + " FROM QuizQuestion "
                        + " WHERE quizID = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int questionNo = rs.getInt("questionNo");
                    int questionID = rs.getInt("questionID");
                    String content = rs.getString("content");
                    String explanation = rs.getString("explanation");
                    String level = rs.getString("level");
                    String mediaLink = rs.getString("mediaLink");
                    boolean status = rs.getBoolean("status");
                    QuizQuestionDTO dto = new QuizQuestionDTO(questionNo, quizID, questionID, content, explanation, level, mediaLink, status);
                    quizQuestion.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public boolean editQuestion(QuizQuestionDTO question) throws NamingException, SQLException {
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            
            if (con != null) {
                String sql = "UPDATE QuizQuestion SET content=?, explanation=?, mediaLink=?, status=?"
                        + " WHERE questionNo=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, question.getContent());
                stm.setString(2, question.getExplanation());
                stm.setString(3, question.getMediaLink());
                stm.setBoolean(4, question.isStatus());
                stm.setInt(5, question.getQuestionNo());
                
                if (stm.executeUpdate() > 0) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuizQuestionDTO> getQuizQuestionList() {
        return quizQuestion;
    }

    public QuizQuestionDTO getQuizQuestion(int number) {
        return quizQuestion.get(number);
    }
}
