package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField bookTitleField;
    @FXML private TextField authorField;
    @FXML private TextField editionField;

    public void addBook() {
        String id = bookIdField.getText().trim();
        String title = bookTitleField.getText().trim();
        String author = authorField.getText().trim();
        String edition = editionField.getText().trim();

        if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
            return;
        }

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return;
        }

        Book b = new Book(id, title, author, edition);
        BookData.getInstance().addBook(b);

        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }
}
