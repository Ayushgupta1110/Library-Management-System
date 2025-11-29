package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentData {
    private static StudentData instance;
    private final ObservableList<Student> students;
    private static final Logger LOGGER = Logger.getLogger(StudentData.class.getName());

    private StudentData() {
        students = FXCollections.observableArrayList();
        loadFromDB();
    }

    public static StudentData getInstance() {
        if (instance == null) instance = new StudentData();
        return instance;
    }

    public ObservableList<Student> getStudents() { return students; }

    public void addStudent(Student s) { addStudentToDB(s); }

    public Student getStudentById(String id) {
        Connection conn = DBManager.getInstance().getConnection();
        String sql = "SELECT id, name, course, session FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("name"),
                            rs.getString("id"),
                            rs.getString("course"),
                            rs.getString("session")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to load student by ID", e);
        }
        return null;
    }

    public ObservableList<Student> getAllStudents() {
        ObservableList<Student> list = FXCollections.observableArrayList();
        Connection conn = DBManager.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id, name, course, session FROM students")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("name"),
                            rs.getString("id"),
                            rs.getString("course"),
                            rs.getString("session")
                    ));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to load all students", e);
        }
        return list;
    }

    private void loadFromDB() {
        students.clear();
        Connection conn = DBManager.getInstance().getConnection();
        try (PreparedStatement ps =
                     conn.prepareStatement("SELECT id, name, course, session FROM students")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student s = new Student(
                            rs.getString("name"),
                            rs.getString("id"),
                            rs.getString("course"),
                            rs.getString("session")
                    );
                    students.add(s);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to load students from DB", e);
        }
    }

    public void addStudentToDB(Student s) {
        Connection conn = DBManager.getInstance().getConnection();
        String sql = "INSERT INTO students (id, name, course, session) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE " +
                     "name = VALUES(name), " +
                     "course = VALUES(course), " +
                     "session = VALUES(session)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(s.getId()));
            ps.setString(2, s.getName());
            ps.setString(3, s.getCourse());
            ps.setString(4, s.getSession());
            ps.executeUpdate();
            students.add(s);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add student to DB", e);
        }
    }
}
