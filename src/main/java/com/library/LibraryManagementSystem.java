package com.library;

import java.util.List;

import com.library.service.AuthenticationService;
import com.library.model.Admin;
import com.library.model.Book;
import com.library.model.Librarian;
import com.library.model.Student;
import com.library.model.Transaction;
import com.library.model.studentTransaction;
import com.library.util.DatabaseConnection;
import com.library.dao.AdminDAO;
import com.library.dao.LibrarianDAO;
import com.library.dao.StudentDAO;
import com.library.dao.TransactionDAO;
import com.library.dao.BookDAO;
import com.library.util.Helper;

import java.util.Scanner;

import java.sql.*;
import java.text.ParseException;

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthenticationService authService = new AuthenticationService();
    
    private static LibrarianDAO librarianDAO = new LibrarianDAO();
    private static AdminDAO adminDAO = new AdminDAO();
    private static StudentDAO studentDAO = new StudentDAO();
    private static BookDAO bookDAO = new BookDAO();
    private static TransactionDAO transactionDAO = new TransactionDAO();

    public static void main(String[] args) {
        try {
            Connection con = DatabaseConnection.getConnection();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Welcome to the Library Management System");

        while (true) {
            System.out.println("Please select your role (1-Admin, 2-Librarian, 3-Student, 0-Exit): ");
            int role = Helper.integerInput(scanner);
            switch (role) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    librarianLogin();
                    break;
                case 3:
                    studentLogin();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine();

        if (authService.loginAdmin(username, password)) {
            System.out.println("Admin logged in successfully.");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void librarianLogin() {
        System.out.println("Enter librarian username: ");
        String username = scanner.nextLine();
        System.out.println("Enter librarian password: ");
        String password = scanner.nextLine();

        if (authService.loginLibrarian(username, password)) {
            System.out.println("Librarian logged in successfully.");
            librarianMenu();
        } else {
            System.out.println("Invalid librarian credentials.");
        }
    }

    private static void studentLogin() {
        System.out.println("Enter student username: ");
        String username = scanner.nextLine();
        System.out.println("Enter student password: ");
        String password = scanner.nextLine();

        // if (true) {
        if (authService.loginStudent(username, password)) {
            Student loggedInStudent = AuthenticationService.getLoggedInStudent();
            System.out.println("Student login successfully");
            studentMenu(loggedInStudent);
        } else {
            System.out.println("Invalid Student credentials.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu: ");
            System.out.println("1- Add Librarian");
            System.out.println("2- Add New Admin");
            System.out.println("3- Delete Librarian");
            System.out.println("4- View all librarians");
            System.out.println("0- Logout");

            int choice = Helper.integerInput(scanner);

            switch (choice) {
                case 1:
                    addNewLibrarian();
                    break;
                case 2:
                    addNewAdmin();
                    break;
                case 3:
                    deleteLibrarian();
                    break;
                case 4:
                    viewAllLibrarians();
                    break;
                case 0:
                    authService.logout();
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void librarianMenu() {
        while (true) {
            System.out.println("Librarian Menu: ");
            System.out.println("1- Add Student");
            System.out.println("2- Delete Student");
            System.out.println("3- Add Book");
            System.out.println("4- Delete Book");
            System.out.println("5- Update Book");
            System.out.println("6- Issue Books to Students");
            System.out.println("7- Return Books from Students");
            System.out.println("0- Logout");

            int choice = Helper.integerInput(scanner);

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    updateBook();
                    break;
                case 6:
                    issueBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 0:
                    authService.logout();
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void studentMenu(Student student) {
        while (true) {
            System.out.println("Student Menu: ");
            System.out.println("1- View Issued Books");
            System.out.println("2- View Account Balance");
            System.out.println("3- Add Account Balance");
            System.out.println("0- Logout");

            int choice = Helper.integerInput(scanner);

            switch (choice) {
                case 1:
                    viewIssuedBooks(student);
                    break;
                case 2:
                    viewAccountBalance(student);
                    break;
                case 3:
                    addAccountBalance(student);
                    break;
                case 0:
                    authService.logout();
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addNewLibrarian() {
        System.out.println("Enter librarian username: ");
        String username = scanner.nextLine();
        System.out.println("Enter librarian password: ");
        String password = scanner.nextLine();
        System.out.println("Enter librarian name: ");
        String name = scanner.nextLine();
        System.out.println("Enter librarian sectionId: ");
        int sectionId = Helper.integerInput(scanner);
        Librarian librarian = new Librarian(username, password, name, sectionId);

        if (librarianDAO.addLibrarian(librarian)) {
            System.out.println("librarian added successfully");
            return;
        } else {
            System.out.println("Librarin cannot be added, some error occured");
        }

    }

    private static void deleteLibrarian() {
        System.out.println("Enter librarian Id: ");
        int librarianId = Helper.integerInput(scanner);
        boolean ans = librarianDAO.deleteLibrarian(librarianId);
        if (ans) {
            System.out.println("librarian deleted successfully");
            return;
        } else {
            System.out.println("invalid librarian id");
        }

    }

    private static void addNewAdmin() {
        System.out.println("Enter Admin Id: ");
        int librarianId = Helper.integerInput(scanner);
        System.out.println("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine();
        System.out.println("Enter admin name: ");
        String name = scanner.nextLine();
        Admin temp = new Admin(librarianId, username, password, name);

        if (adminDAO.addAdmin(temp)) {
            System.out.println("admin added successfully");
            return;
        } else {
            System.out.println("admin cannot be added, some error occured");
        }
    }

    private static void addStudent() {
        System.out.println("Enter Student Id: ");
        int studentId = Helper.integerInput(scanner);
        System.out.println("Enter student username: ");
        String username = scanner.nextLine();
        System.out.println("Enter student password: ");
        String password = scanner.nextLine();
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();

        Student temp = new Student(studentId, name, username, password);
        if (studentDAO.addStudent(temp)) {
            System.out.println("student added successfully");
            return;
        } else {
            System.out.println("student cannot be added, some error occured");
        }

    }

    private static void deleteStudent() {
        System.out.println("Enter Student Id: ");
        int studentId = Helper.integerInput(scanner);

        if (studentDAO.deleteStudent(studentId)) {
            System.out.println("student deleted successfully");
            return;
        } else {
            System.out.println("error");
        }
    }

    private static void viewAllLibrarians() {
        librarianDAO.viewAllLibrarians();
    }

    // private static void updateStudent() {
    // System.out.println("Enter Student Id: ");
    // int studentId = Helper.integerInput(scanner);
    // System.out.println("Enter student updated name: ");
    // String name = scanner.nextLine();
    // System.out.println("Enter student updated numIssuedBooks: ");
    // String numIssuedBooks = scanner.nextLine();
    // System.out.println("Enter student updated accountBalance: ");
    // String accountBalance = scanner.nextLine();

    // Student temp = new Student(studentId, name, accountBalance, numIssuedBooks);
    // if (studentDAO.updateStudent(studentId)) {
    // System.out.println("student deleted successfully");
    // return;
    // } else {
    // System.out.println("error");
    // }

    // }

    private static void addBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Helper.integerInput(scanner);
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book author: ");
        String author = scanner.nextLine();
        System.out.println("Enter num of copies: ");
        int availableCopies = Helper.integerInput(scanner);
        System.out.println("Enter Section Id: ");
        int sectionId = Helper.integerInput(scanner);

        Book temp = new Book(bookId, title, author, sectionId, availableCopies);
        if (bookDAO.addBook(temp)) {
            System.out.println("book added successfully");
            return;
        } else {
            System.out.println("book already exists or some other credentials are wrong");
        }

        // Implementation here
    }

    private static void deleteBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Helper.integerInput(scanner);

        if (bookDAO.deleteBook(bookId)) {
            System.out.println("book deleted successfully");
            return;
        } else {
            System.out.println("invalid bookId");
        }
        // Implementation here
    }

    private static void updateBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Helper.integerInput(scanner);
        System.out.println("Enter num of copies: ");
        int availableCopies = Helper.integerInput(scanner);
        System.out.println("Enter rating: ");
        int rating = Helper.integerInput(scanner);
        System.out.println("Enter number of issues: ");
        int numIssues = Helper.integerInput(scanner);

        Book temp = new Book(bookId, availableCopies, rating, numIssues);
        if (bookDAO.updateBook(temp)) {
            System.out.println("book updated successfully");
            return;
        } else {
            System.out.println("book credentials are wrong");
        }

    }

    private static void issueBook() {
        System.out.println("Enter StudentId: ");
        int studentId = Helper.integerInput(scanner);
        System.out.println("Enter BookId: ");
        int bookId = Helper.integerInput(scanner);
        System.out.println("Enter IssueDate (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        java.util.Date issueDate = null;
        try {
            issueDate = Helper.inputDateFormat(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in YYYY-MM-DD format.");
            return;
        }

        Transaction issueTransaction = new Transaction(studentId, bookId, issueDate);
        if (transactionDAO.addTransaction(issueTransaction)) {
            System.out.println("Book: " + bookDAO.getTitleById(bookId) + "| issued to Student: " + studentDAO.getNameById(studentId) + "| on Date: " + Helper.outputDateFormat(issueDate));
            return;
        }

    }

    private static void returnBook() {
        System.out.println("Enter StudentId: ");
        int studentId = Helper.integerInput(scanner);
        
        System.out.println("Enter Book Id: ");
        int bookId = Helper.integerInput(scanner);
        
        System.out.println("Enter rating between 1 and 10: ");
        int rating = Helper.integerInput(scanner);
        if (rating < 1 || rating > 10){
            System.out.println("Invalid rating. Please enter an integer value between 1 and 10");
            return;
        }
        
        System.out.println("Enter returnDate (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        java.util.Date returnDate = null;
        try {
            returnDate = Helper.inputDateFormat(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in YYYY-MM-DD format.");
            return;
        }

        transactionDAO.returnBook(studentId, bookId, returnDate, rating);

    }

    private static void viewIssuedBooks(Student student) {
        List<studentTransaction> transactions = transactionDAO.getTransactionsByStudentId(student.getStudentId());

        System.out.println("Viewing issued books for student: " + student.getName());

        if (transactions.isEmpty()) {
            System.out.println("No books issued to this student.");
        } else {
            for (studentTransaction t : transactions) {
                System.out.println("TransactionID - " + t.transactionId + ", Book Title - " + t.title + ", Issue Date - " + t.issueDate + ", Return Date - " + t.returnDate + ", Fine - " + t.fine + ", Rating - " + t.rating);
            }
        }
    }

    private static void viewAccountBalance(Student student) {
        System.out.println("Account balance for student: " + student.getName() + " is " + student.getAccountBalance());
    }

    private static void addAccountBalance(Student student) {
        System.out.println("Add the amount to add in your account: ");
        int amount = Helper.integerInput(scanner);
        if (amount < 0) {
            System.out.println("Negative amount cannot be added");
            return;
        }

        student.setAccountBalance(student.getAccountBalance() + amount);
        studentDAO.updateStudent(student);
        viewAccountBalance(student);
        System.out.println();
    }
}
