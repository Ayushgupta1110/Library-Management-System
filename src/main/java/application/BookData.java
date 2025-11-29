package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookData {
    private static BookData instance;
    private final ObservableList<Book> books;
    private static final Logger LOGGER = Logger.getLogger(BookData.class.getName());

    private BookData() {
        books = FXCollections.observableArrayList();
        loadFromDB();
    }

    public static BookData getInstance() {
        if (instance == null) instance = new BookData();
        return instance;
    }

    public ObservableList<Book> getBooks() { return books; }

    public void addBook(Book b) { addBookToDB(b); }

    private void loadFromDB() {
        books.clear();
        Connection conn = DBManager.getInstance().getConnection();
        try (PreparedStatement ps =
                     conn.prepareStatement("SELECT id, title, author, edition FROM books")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book b = new Book(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("edition")
                    );
                    books.add(b);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to load books from DB", e);
        }
    }

    public void addBookToDB(Book b) {
        Connection conn = DBManager.getInstance().getConnection();
        String sql = "INSERT INTO books (id, title, author, edition) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE " +
                     "title = VALUES(title), " +
                     "author = VALUES(author), " +
                     "edition = VALUES(edition)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(b.getId()));
            ps.setString(2, b.getTitle());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getEdition());
            ps.executeUpdate();
            books.add(b);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add book to DB", e);
        }
    }
}
