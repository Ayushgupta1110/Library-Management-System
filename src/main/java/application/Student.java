package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty name;
    private final StringProperty id;
    private final StringProperty course;
    private final StringProperty session;

    public Student(String name, String id, String course, String session) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.course = new SimpleStringProperty(course);
        this.session = new SimpleStringProperty(session);
    }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public String getId() { return id.get(); }
    public void setId(String value) { id.set(value); }
    public StringProperty idProperty() { return id; }

    public String getCourse() { return course.get(); }
    public void setCourse(String value) { course.set(value); }
    public StringProperty courseProperty() { return course; }

    public String getSession() { return session.get(); }
    public void setSession(String value) { session.set(value); }
    public StringProperty sessionProperty() { return session; }
}
