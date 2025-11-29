package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML private TextField nameField;
    @FXML private TextField idField;
    @FXML private TextField courseField;
    @FXML private TextField sessionField;

    public void addStudent() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String course = courseField.getText().trim();
        String session = sessionField.getText().trim();

        if (name.isEmpty() || id.isEmpty() || course.isEmpty()) {
            return;
        }

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return;
        }

        Student s = new Student(name, id, course, session);
        StudentData.getInstance().addStudent(s);

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
