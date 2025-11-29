package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserData {
    private static final Logger LOGGER = Logger.getLogger(UserData.class.getName());
    private static UserData instance;

    private UserData() { }

    public static synchronized UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public User authenticate(String username, String password) {
        Connection conn = DBManager.getInstance().getConnection();
        String sql = "SELECT username, role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("username"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to authenticate user", e);
        }
        return null;
    }
}
