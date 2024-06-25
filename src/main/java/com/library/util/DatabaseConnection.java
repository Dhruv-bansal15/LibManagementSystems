// src/main/java/util/DatabaseConnection.java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:ORCLCDB";
        String user = "SYS";
        String password = "mypassword1";
        return DriverManager.getConnection(url, user, password);
    }
}
