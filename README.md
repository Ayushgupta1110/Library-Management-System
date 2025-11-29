Library Management System (JavaFX + MySQL)

A desktop Library Management System built with JavaFX, MySQL and Maven.  
It supports managing students and books, issuing and returning books, and tracking all transactions in a MySQL database.

---

Features

- Login screen with username/password (default: `admin` / `admin123`)
- Manage students (add basic details: ID, name, course, session)
- Manage books (add ID, title, author, edition)
- Issue books to students
- Return books from students
- Transaction history (type, book, student, date)
- Student name auto-selects from roll number (ID) using a dropdown
- MySQL persistence

---

Tech Stack

- Java 17
- JavaFX 21
- MySQL 8
- Maven
- JDBC

---

Project Structure

.
├── pom.xml
└── src
└── main
├── java
│ └── application
│ ├── AddBookController.java
│ ├── AddStudentController.java
│ ├── Book.java
│ ├── BookData.java
│ ├── DashboardController.java
│ ├── DBConnection.java # (optional helper, if used)
│ ├── DBManager.java
│ ├── IssueBookController.java
│ ├── LoginController.java
│ ├── Main.java
│ ├── ReturnBookController.java
│ ├── Student.java
│ ├── StudentData.java
│ ├── Transaction.java
│ ├── TransactionData.java
│ ├── User.java
│ ├── UserData.java
│ └── module-info.java
└── resources
└── application
├── add_book.fxml
├── add_student.fxml
├── dashboard.fxml
├── issue_book.fxml
├── login.fxml
└── return_book.fxml

Database Setup (MySQL)

1. Create the database and tables:

CREATE DATABASE IF NOT EXISTS LibraryManagementSystem;
USE LibraryManagementSystem;

DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS users;

CREATE TABLE students (
id INT PRIMARY KEY,
name VARCHAR(100),
course VARCHAR(100),
session VARCHAR(50)
);

CREATE TABLE books (
id INT PRIMARY KEY,
title VARCHAR(200),
author VARCHAR(100),
edition VARCHAR(50)
);

CREATE TABLE transactions (
type VARCHAR(50),
bookTitle VARCHAR(200),
bookId VARCHAR(50),
studentName VARCHAR(100),
studentId INT,
date DATE
);

CREATE TABLE users (
id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(100) NOT NULL,
role VARCHAR(50) DEFAULT 'ADMIN'
);

INSERT INTO users (username, password, role)
VALUES ('admin', 'admin123', 'ADMIN');

text

2. Make sure your MySQL server is running on `127.0.0.1:3306`.

---

Configure Database in Code

In `src/main/java/application/DBManager.java`, set your MySQL credentials:

private static final String JDBC_URL =
"jdbc:mysql://127.0.0.1:3306/LibraryManagementSystem?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "Ayush@05";

text

---

How to Run

From the project root:

mvn clean compile javafx:run

text

Then:

- Login with:
  - **Username:** `admin`
  - **Password:** `admin123`
- Use **Add Student** and **Add Book** to populate data.
- Use **Issue Book**:
  - Enter Book Title and Book ID.
  - Enter Student ID (roll number) and press Enter.
  - Student Name dropdown will auto-fill with that student.
- Use **Return Book** similarly.

---

Notes
- This project is intended as a learning/demo project for JavaFX + MySQL.
- You can modify the UI (FXML files) with Scene Builder.
- Feel free to extend it with validations, search, and reporting features.
- This project is intended as a learning/demo project for JavaFX + MySQL.
- You can modify the UI (FXML files) with Scene Builder.
- Feel free to extend it with validations, search, and reporting features.
