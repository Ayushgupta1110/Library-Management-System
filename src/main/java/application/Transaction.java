package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private final StringProperty type;
    private final StringProperty bookTitle;
    private final StringProperty bookId;
    private final StringProperty studentName;
    private final StringProperty studentId;
    private final StringProperty date;

    public Transaction(String type, String bookTitle, String bookId,
                       String studentName, String studentId, String date) {
        this.type = new SimpleStringProperty(type);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.bookId = new SimpleStringProperty(bookId);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentId = new SimpleStringProperty(studentId);
        this.date = new SimpleStringProperty(date);
    }

    public String getType() { return type.get(); }
    public StringProperty typeProperty() { return type; }

    public String getBookTitle() { return bookTitle.get(); }
    public StringProperty bookTitleProperty() { return bookTitle; }

    public String getBookId() { return bookId.get(); }
    public StringProperty bookIdProperty() { return bookId; }

    public String getStudentName() { return studentName.get(); }
    public StringProperty studentNameProperty() { return studentName; }

    public String getStudentId() { return studentId.get(); }
    public StringProperty studentIdProperty() { return studentId; }

    public String getDate() { return date.get(); }
    public StringProperty dateProperty() { return date; }
}
