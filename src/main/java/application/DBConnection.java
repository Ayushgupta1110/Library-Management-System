package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/LibraryManagementSystem?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "library_user";
    private static final String PASSWORD = "StrongPassword123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database Connected Successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database Connection Failed!");
        }
        return conn;
    }
}
