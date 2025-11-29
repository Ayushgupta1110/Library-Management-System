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
