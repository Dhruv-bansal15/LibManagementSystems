compile
javac -d target/classes -cp lib/ojdbc8.jar src/main/java/com/library/\*_/_.java

run
java -cp target/classes:lib/ojdbc8.jar com.library.LibraryManagementSystem

# Library Management System

This project implements a command-line based Library Management System in Java, utilizing Oracle SQL for data storage. The system supports multiple user roles (Admins and Librarians) with various functionalities tailored to each role, along with features for managing students and books.

## Features

- **Admin Features:**
  - CRUD operations for librarians and students.
  - Approval system for librarian registration.
  - Section management for different categories of books.

- **Librarian Features:**
  - CRUD operations for students and books.
  - Issuing and returning books.
  - Reserving books that are currently unavailable.
  - Notifying admin about new book requests.

- **General Features:**
  - Fine calculation for late book returns.
  - Listing available books and recommending similar books.
  - Rating system for books based on student feedback.
  - Department-wise book listings.

## Technologies Used

- Java for application logic.
- Oracle SQL for database management.
- JDBC for database connectivity.