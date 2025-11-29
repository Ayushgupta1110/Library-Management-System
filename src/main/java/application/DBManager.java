package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static DBManager instance;
    private Connection conn;
    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());

    private static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/LibraryManagementSystem?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Ayush@05";

    private DBManager() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            initializeDatabase();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize DB", e);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    private void initializeDatabase() {
        try (Statement statement = conn.createStatement()) {

            // students table
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students (" +
                            "id INT PRIMARY KEY, " +
                            "name VARCHAR(100), " +
                            "course VARCHAR(100), " +
                            "session VARCHAR(50)" +
                            ")"
            );

            // books table
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS books (" +
                            "id INT PRIMARY KEY, " +
                            "title VARCHAR(200), " +
                            "author VARCHAR(100), " +
                            "edition VARCHAR(50)" +
                            ")"
            );

            // transactions table
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS transactions (" +
                            "type VARCHAR(50), " +
                            "bookTitle VARCHAR(200), " +
                            "bookId VARCHAR(50), " +
                            "studentName VARCHAR(100), " +
                            "studentId INT, " +
                            "date DATE" +
                            ")"
            );

            // users table
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(50) UNIQUE NOT NULL, " +
                            "password VARCHAR(100) NOT NULL, " +
                            "role VARCHAR(50) DEFAULT 'ADMIN'" +
                            ")"
            );

            // default admin user (only once)
            statement.executeUpdate(
                    "INSERT INTO users (username, password, role) " +
                            "VALUES ('admin', 'admin123', 'ADMIN') " +
                            "ON DUPLICATE KEY UPDATE username = username"
            );

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize the database.");
        }
    }
}
