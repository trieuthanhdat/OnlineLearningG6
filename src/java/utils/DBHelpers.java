package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBHelpers implements Serializable {

    public static Connection makeConnection()
            throws NamingException, SQLException {
        Connection con = null;
        try {
            Context context = new InitialContext(); //get current OS
            Context tomcatContext = (Context) context.lookup("java:comp/env"); //get Tomcat OS
            DataSource ds = (DataSource) tomcatContext.lookup("OLS");
            con = ds.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }


}
