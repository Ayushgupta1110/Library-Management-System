package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(DashboardController.class.getName());

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colId;
    @FXML private TableColumn<Student, String> colCourse;
    @FXML private TableColumn<Student, String> colSession;

    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> colBookId;
    @FXML private TableColumn<Book, String> colBookTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, String> colEdition;

    @FXML private TableView<Transaction> transactionTable;
    @FXML private TableColumn<Transaction, String> colType;
    @FXML private TableColumn<Transaction, String> colTBookTitle;
    @FXML private TableColumn<Transaction, String> colTBookId;
    @FXML private TableColumn<Transaction, String> colStudentName;
    @FXML private TableColumn<Transaction, String> colStudentId;
    @FXML private TableColumn<Transaction, String> colDate;

    public void openAddStudent() {
        openWindow("/application/add_student.fxml", "Add Student");
    }

    public void openAddBook() {
        openWindow("/application/add_book.fxml", "Add Book");
    }

    public void openIssueBook() {
        openWindow("/application/issue_book.fxml", "Issue Book");
    }

    public void openReturnBook() {
        openWindow("/application/return_book.fxml", "Return Book");
    }

    private void openWindow(String fxml, String title) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource(fxml)), 500, 400));
            stage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to open window " + fxml, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Students
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colSession.setCellValueFactory(new PropertyValueFactory<>("session"));
        studentTable.setItems(StudentData.getInstance().getStudents());

        // Books
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colEdition.setCellValueFactory(new PropertyValueFactory<>("edition"));
        bookTable.setItems(BookData.getInstance().getBooks());

        // Transactions
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colTBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        transactionTable.setItems(TransactionData.getInstance().getTransactions());
    }
}
