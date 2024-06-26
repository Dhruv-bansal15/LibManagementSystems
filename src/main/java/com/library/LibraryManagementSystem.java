package com.library;

import java.util.List;

import com.library.service.AuthenticationService;
import com.library.model.Admin;
import com.library.model.Book;
import com.library.model.Librarian;
import com.library.model.Student;
import com.library.model.Transaction;
import com.library.util.DatabaseConnection;
import com.library.dao.AdminDAO;
import com.library.dao.LibrarianDAO;
import com.library.dao.StudentDAO;
import com.library.dao.TransactionDAO;
import com.library.dao.BookDAO;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.*;

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthenticationService authService = new AuthenticationService();
    // private static StudentService studentService = new StudentService();
    // private static AdminService adminService = new AdminService();
    // private static LibrarianService librarianService = new LibrarianService();
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
            int role = Integer.parseInt(scanner.nextLine());
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

        // cl
    }

    private static void adminLogin() {
        System.out.println("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine();

        if (authService.loginAdmin(username, password)) {
            Admin loggedInAdmin = AuthenticationService.getLoggedInAdmin();
            if (loggedInAdmin != null) {
                System.out.println("Admin logged in successfully.");
                adminMenu();
            } else {
                System.out.println("Invalid admin credentials.");
            }
        }
    }

    private static void librarianLogin() {
        System.out.println("Enter librarian username: ");
        String username = scanner.nextLine();
        System.out.println("Enter librarian password: ");
        String password = scanner.nextLine();

        if (authService.loginLibrarian(username, password)) {
            Librarian loggedInLibrarian = AuthenticationService.getLoggedInLibrarian();
            if (loggedInLibrarian != null) {
                System.out.println("Librarian logged in successfully.");
                librarianMenu();
            } else {
                System.out.println("Invalid librarian credentials.");
            }
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
            System.out.println("0- Logout");

            int choice = Integer.parseInt(scanner.nextLine());

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
                case 0:
                    authService.logout();
                    System.out.println("Logged out.");
                    LibraryManagementSystem.main(null);
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
            // System.out.println("3- Update Student");
            System.out.println("4- Add Book");
            System.out.println("5- Delete Book");
            System.out.println("6- Update Book");
            System.out.println("7- Issue Books to Students");
            System.out.println("8- Return Books from Students");
            System.out.println("0- Logout");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 4:
                    addBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    issueBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 0:
                    authService.logout();
                    System.out.println("Logged out.");
                    LibraryManagementSystem.main(null);
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

            int choice = Integer.parseInt(scanner.nextLine());

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
                    LibraryManagementSystem.main(null);
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addNewLibrarian() {
        System.out.println("Enter librarian Id: ");
        int librarianId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter librarian username: ");
        String username = scanner.nextLine();
        System.out.println("Enter librarian password: ");
        String password = scanner.nextLine();
        System.out.println("Enter librarian name: ");
        String name = scanner.nextLine();
        System.out.println("Enter librarian sectionId: ");
        int sectionId = Integer.parseInt(scanner.nextLine());
        Librarian temp = new Librarian(librarianId, username, password, name, sectionId);

        if (librarianDAO.addLibrarian(temp)) {
            System.out.println("librarian added successfully");
            adminMenu();
        } else {
            System.out.println("Librarin cannot be added, some error occured");
        }

    }

    private static void deleteLibrarian() {
        System.out.println("Enter librarian Id: ");
        int librarianId = Integer.parseInt(scanner.nextLine());
        boolean ans = librarianDAO.deleteLibrarian(librarianId);
        if (ans) {
            System.out.println("librarian deleted successfully");
            adminMenu();
        } else {
            System.out.println("invalid librarian id");
        }

    }

    private static void addNewAdmin() {
        System.out.println("Enter Admin Id: ");
        int librarianId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine();
        System.out.println("Enter admin name: ");
        String name = scanner.nextLine();
        Admin temp = new Admin(librarianId, username, password, name);

        if (adminDAO.addAdmin(temp)) {
            System.out.println("admin added successfully");
            adminMenu();
        } else {
            System.out.println("admin cannot be added, some error occured");
        }
    }

    private static void addStudent() {
        System.out.println("Enter Student Id: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter student username: ");
        String username = scanner.nextLine();
        System.out.println("Enter student password: ");
        String password = scanner.nextLine();
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();

        Student temp = new Student(studentId, name, username, password);
        if (studentDAO.addStudent(temp)) {
            System.out.println("student added successfully");
            librarianMenu();
        } else {
            System.out.println("student cannot be added, some error occured");
        }

    }

    private static void deleteStudent() {
        System.out.println("Enter Student Id: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        if (studentDAO.deleteStudent(studentId)) {
            System.out.println("student deleted successfully");
            librarianMenu();
        } else {
            System.out.println("error");
        }
    }

    // private static void updateStudent() {
    //     System.out.println("Enter Student Id: ");
    //     int studentId = Integer.parseInt(scanner.nextLine());
    //     System.out.println("Enter student updated name: ");
    //     String name = scanner.nextLine();
    //     System.out.println("Enter student updated numIssuedBooks: ");
    //     String numIssuedBooks = scanner.nextLine();
    //     System.out.println("Enter student updated accountBalance: ");
    //     String accountBalance = scanner.nextLine();

    //     Student temp = new Student(studentId, name, accountBalance, numIssuedBooks);
    //     if (studentDAO.updateStudent(studentId)) {
    //         System.out.println("student deleted successfully");
    //         librarianMenu();
    //     } else {
    //         System.out.println("error");
    //     }

    // }

    private static void addBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book author: ");
        String author = scanner.nextLine();
        System.out.println("Enter num of copies: ");
        int availableCopies = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Section Id: ");
        int sectionId = Integer.parseInt(scanner.nextLine());

        Book temp = new Book(bookId, title, author ,sectionId, availableCopies);
        if (bookDAO.addBook(temp)) {
            System.out.println("book added successfully");
            librarianMenu();
        } else {
            System.out.println("book already exists or some other credentials are wrong");
        }

        // Implementation here
    }

    private static void deleteBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        if (bookDAO.deleteBook(bookId)) {
            System.out.println("book deleted successfully");
            librarianMenu();
        } else {
            System.out.println("invalid bookId");
        }
        // Implementation here
    }

    private static void updateBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter num of copies: ");
        int availableCopies = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter rating: ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter number of issues: ");
        int numIssues = Integer.parseInt(scanner.nextLine());

        Book temp = new Book(bookId, availableCopies, rating, numIssues);
        if (bookDAO.updateBook(temp)) {
            System.out.println("book updated successfully");
            librarianMenu();
        } else {
            System.out.println("book credentials are wrong");
        }
        
    }

    private static void issueBook() {
        System.out.println("Enter Transaction Id: ");
        int transactionId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter StudentId: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter BookId: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter IssueDate (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date issueDate = null;
        try {
            issueDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in YYYY-MM-DD format.");
        }

        Transaction temp = new Transaction(transactionId, studentId, bookId, issueDate);
        if (transactionDAO.addTransaction(temp)) {
            System.out.println("transaction added successfully");
            librarianMenu();
        } else {
            System.out.println("transaction cannot be added, some error occured");
        }
        
    }

    private static void returnBook() {
        System.out.println("Enter Book Id: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter StudentId: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter rating: ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter returnDate (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date returnDate = null;
        try {
            returnDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter a date in YYYY-MM-DD format.");
        }

        if (transactionDAO.returnBook(studentId, bookId, returnDate, rating)) {
            System.out.println("transaction updated successfully");
            librarianMenu();
        } else {
            System.out.println("book is not yet issued");
        }
        
    }

    private static void viewIssuedBooks(Student student) {
        List<Transaction> transactions = transactionDAO.getTransactionsByStudentId(student.getStudentId());

        System.out.println("Viewing issued books for student: " + student.getName());

        if (transactions.isEmpty()) {
            System.out.println("No books issued to this student.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    private static void viewAccountBalance(Student student) {
        System.out.println("Account balance for student: " + student.getName() + " is " + student.getAccountBalance());
        System.out.println();
    }

    private static void addAccountBalance(Student student) {
        System.out.println("Add the amount to add in your account: ");
        int amount = scanner.nextInt();

        student.setAccountBalance(student.getAccountBalance() + amount);
        viewAccountBalance(student);
    }
}
