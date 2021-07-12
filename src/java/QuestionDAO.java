/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import utils.DBHelpers;
import QuizNQuestion.QuestionDTO;
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
public class QuestionDAO implements Serializable{
    
    private List<QuestionDTO> questions = null;
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
    
    public void importQuestion (int lessonID) throws NamingException, SQLException {
        questions = new ArrayList();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT questionID, mediaLink, content, status, explanation, level "
                        + "FROM Question "
                        + "WHERE lessonID = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, lessonID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int questionID = rs.getInt("questionID");
                        String mediaLink = rs.getString("mediaLink");
                        String content = rs.getString("content");
                        String explanation = rs.getString("explanation");
                        String level = rs.getString("level");
                        QuestionDTO dto = new QuestionDTO(questionID, mediaLink, content, lessonID, status, explanation, level);
                        questions.add(dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    public List<QuestionDTO> getQuestion() {
        return questions;
    }
    
}
