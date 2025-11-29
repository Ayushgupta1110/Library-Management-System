package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {

    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty edition;

    public Book(String id, String title, String author, String edition) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.edition = new SimpleStringProperty(edition);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String value) {
        author.set(value);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getEdition() {
        return edition.get();
    }

    public void setEdition(String value) {
        edition.set(value);
    }

    public StringProperty editionProperty() {
        return edition;
    }
}
