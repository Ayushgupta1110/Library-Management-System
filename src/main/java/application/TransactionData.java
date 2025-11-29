package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionData {
    private static TransactionData instance;
    private final ObservableList<Transaction> transactions;
    private static final Logger LOGGER = Logger.getLogger(TransactionData.class.getName());

    private TransactionData() {
        transactions = FXCollections.observableArrayList();
        loadFromDB();
    }

    public static TransactionData getInstance() {
        if (instance == null) instance = new TransactionData();
        return instance;
    }

    public ObservableList<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction t) { addTransactionToDB(t); }

    private void loadFromDB() {
        transactions.clear();
        Connection conn = DBManager.getInstance().getConnection();
        try (PreparedStatement ps =
                     conn.prepareStatement("SELECT type, bookTitle, bookId, studentName, studentId, date FROM transactions")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction t = new Transaction(
                            rs.getString("type"),
                            rs.getString("bookTitle"),
                            rs.getString("bookId"),
                            rs.getString("studentName"),
                            rs.getString("studentId"),
                            rs.getString("date")
                    );
                    transactions.add(t);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to load transactions from DB", e);
        }
    }

    public void addTransactionToDB(Transaction t) {
        Connection conn = DBManager.getInstance().getConnection();
        String sql = "INSERT INTO transactions " +
                     "(type, bookTitle, bookId, studentName, studentId, date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getType());
            ps.setString(2, t.getBookTitle());
            ps.setString(3, t.getBookId());
            ps.setString(4, t.getStudentName());
            ps.setString(5, t.getStudentId());
            ps.setString(6, t.getDate());
            ps.executeUpdate();
            transactions.add(t);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add transaction to DB", e);
        }
    }
}
