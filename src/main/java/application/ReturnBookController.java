package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class ReturnBookController implements Initializable {

    @FXML private TextField bookTitleField;
    @FXML private TextField bookIdField;
    @FXML private TextField studentIdField;
    @FXML private ComboBox<Student> studentNameCombo;
    @FXML private TextField dateField;

    private static final DateTimeFormatter INPUT_FMT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter MYSQL_FMT =
            DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Do NOT preload all students
        studentNameCombo.setCellFactory(lv -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });
        studentNameCombo.setButtonCell(new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });
    }

    @FXML
    private void loadStudentName() {
        System.out.println("ReturnBookController.loadStudentName called");

        String studentId = studentIdField.getText().trim();
        System.out.println("Entered ID = '" + studentId + "'");

        studentNameCombo.getItems().clear();
        studentNameCombo.getSelectionModel().clearSelection();

        if (studentId.isEmpty()) {
            System.out.println("ID empty, nothing to do");
            return;
        }

        Student student = StudentData.getInstance().getStudentById(studentId);
        if (student != null) {
            System.out.println("Found student: " + student.getName());
            studentNameCombo.getItems().add(student);
            studentNameCombo.getSelectionModel().selectFirst();
        } else {
            System.out.println("No student found for id = " + studentId);
        }
    }

    @FXML
    private void returnBook() {
        String bookTitle = bookTitleField.getText().trim();
        String bookId = bookIdField.getText().trim();
        Student selectedStudent = studentNameCombo.getSelectionModel().getSelectedItem();
        String dateText = dateField.getText().trim();

        if (bookTitle.isEmpty() || bookId.isEmpty()
                || selectedStudent == null || dateText.isEmpty()) {
            System.out.println("Please fill all fields.");
            return;
        }

        try {
            LocalDate.parse(dateText, INPUT_FMT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use dd-MM-yyyy");
            return;
        }

        String mysqlDate = LocalDate.parse(dateText, INPUT_FMT).format(MYSQL_FMT);

        Transaction t = new Transaction(
                "RETURN",
                bookTitle,
                bookId,
                selectedStudent.getName(),
                selectedStudent.getId(),
                mysqlDate
        );
        TransactionData.getInstance().addTransaction(t);

        Stage stage = (Stage) bookTitleField.getScene().getWindow();
        stage.close();
    }
}
