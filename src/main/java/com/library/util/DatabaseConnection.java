package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:ORCLCDB";
        String user = "SYS";
        String password = "mypassword1";
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("internal_logon", "SYSDBA");
        // Connection con = DriverManager.getConnection(url, props);
        // Statement stmt = con.createStatement();
        // ResultSet rs = stmt.executeQuery("select * from emp");
        // while (rs.next())
        //     System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

        // con.close();
        return DriverManager.getConnection(url, props);
    }

    public static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void closeResources(Connection conn, PreparedStatement stmt) {
        closeResources(conn, stmt, null);
    }
}
