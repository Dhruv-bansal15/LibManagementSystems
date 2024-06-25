package com.library;

// import java.util.Properties;

// import com.library.service.AuthenticationService;
// import com.library.service.AdminService;
// import com.library.service.LibrarianService;
// import com.library.service.StudentService;
// import com.library.model.Admin;
// import com.library.model.Librarian;
// import com.library.model.Student;
import com.library.util.DatabaseConnection;

// import java.sql.PreparedStatement;
// import java.util.Scanner;
import java.sql.*;

public class LibraryManagementSystem {
    // private static Scanner sc = new Scanner(System.in);
    // private static AuthenticationService authService = new
    // AuthenticationService();
    // private static StudentService studentService = new StudentService();
    // private static AdminService adminService = new AdminService();
    // private static LibrarianService librarianService = new LibrarianService();

    public static void main(String[] args) {
        try {
            
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("Welcome to the Library Management System");

        // while (true) {
        // System.out.println("Please select your role (1-Admin, 2-Librarian, 3-Student,
        // 0-Exit): ");
        // int role = Integer.parseInt(sc.nextLine());

        // switch (role) {
        // case 1:
        // adminLogin();
        // System.out.println("admin login");
        // break;
        // case 2:
        // librarianLogin();
        // break;
        // case 3:
        // studentLogin();
        // break;
        // case 0:
        // System.out.println("Exiting...");
        // return;
        // default:
        // System.out.println("Invalid option, please try again.");
        // }
        // }

        // cl
    }

    // private static void adminLogin() {
    // System.out.println("Enter admin username: ");
    // String username = scanner.nextLine();
    // System.out.println("Enter admin password: ");
    // String password = scanner.nextLine();

    // if (authService.loginAdmin(username, password)) {
    // Admin loggedInAdmin = AuthenticationService.getLoggedInAdmin();
    // if (loggedInAdmin != null) {
    // System.out.println("Admin logged in successfully.");
    // adminMenu();
    // } else {
    // System.out.println("Invalid admin credentials.");
    // }
    // }
    // }

    // private static void librarianLogin() {
    // System.out.println("Enter librarian username: ");
    // String username = scanner.nextLine();
    // System.out.println("Enter librarian password: ");
    // String password = scanner.nextLine();

    // if (authService.loginLibrarian(username, password)) {
    // Librarian loggedInLibrarian = AuthenticationService.getLoggedInLibrarian();
    // if (loggedInLibrarian != null) {
    // System.out.println("Librarian logged in successfully.");
    // librarianMenu();
    // } else {
    // System.out.println("Invalid librarian credentials.");
    // }
    // }
    // }

    // private static void studentLogin() {
    // // Assuming student login mechanism (e.g., name as username)
    // System.out.println("Enter student name: ");
    // String studentName = scanner.nextLine();

    // Student student = studentService.getStudentByName(studentName);
    // if (student != null) {
    // System.out.println("Student logged in successfully.");
    // studentMenu(student);
    // } else {
    // System.out.println("Student not found.");
    // }
    // }

    // private static void adminMenu() {
    // while (true) {
    // System.out.println("Admin Menu: ");
    // System.out.println("1- Verify Librarians");
    // System.out.println("2- Assign Librarian Section");
    // System.out.println("3- Add New Admin");
    // System.out.println("0- Logout");

    // int choice = Integer.parseInt(scanner.nextLine());

    // switch (choice) {
    // case 1:
    // verifyLibrarians();
    // break;
    // case 2:
    // assignLibrarianSection();
    // break;
    // case 3:
    // addNewAdmin();
    // break;
    // case 0:
    // authService.logout();
    // System.out.println("Logged out.");
    // return;
    // default:
    // System.out.println("Invalid choice, please try again.");
    // }
    // }
    // }

    // private static void librarianMenu() {
    // while (true) {
    // System.out.println("Librarian Menu: ");
    // System.out.println("1- CRUD Student Database");
    // System.out.println("2- CRUD Book Database");
    // System.out.println("3- Issue Books to Students");
    // System.out.println("4- Return Books from Students");
    // System.out.println("5- Reserve Books for Students");
    // System.out.println("6- Verify Students");
    // System.out.println("0- Logout");

    // int choice = Integer.parseInt(scanner.nextLine());

    // switch (choice) {
    // case 1:
    // manageStudents();
    // break;
    // case 2:
    // manageBooks();
    // break;
    // case 3:
    // issueBooks();
    // break;
    // case 4:
    // returnBooks();
    // break;
    // case 5:
    // reserveBooks();
    // break;
    // case 6:
    // verifyStudents();
    // break;
    // case 0:
    // authService.logout();
    // System.out.println("Logged out.");
    // return;
    // default:
    // System.out.println("Invalid choice, please try again.");
    // }
    // }
    // }

    // private static void studentMenu(Student student) {
    // while (true) {
    // System.out.println("Student Menu: ");
    // System.out.println("1- View Issued Books");
    // System.out.println("2- View Reserved Books");
    // System.out.println("3- View Account Balance");
    // System.out.println("0- Logout");

    // int choice = Integer.parseInt(scanner.nextLine());

    // switch (choice) {
    // case 1:
    // viewIssuedBooks(student);
    // break;
    // case 2:
    // viewReservedBooks(student);
    // break;
    // case 3:
    // viewAccountBalance(student);
    // break;
    // case 0:
    // System.out.println("Logged out.");
    // return;
    // default:
    // System.out.println("Invalid choice, please try again.");
    // }
    // }
    // }

    // private static void verifyLibrarians() {
    // System.out.println("Verifying librarians...");
    // // Implementation here
    // }

    // private static void assignLibrarianSection() {
    // System.out.println("Assigning section to librarian...");
    // // Implementation here
    // }

    // private static void addNewAdmin() {
    // System.out.println("Adding a new admin...");
    // // Implementation here
    // }

    // private static void manageStudents() {
    // System.out.println("Managing students...");
    // // Implementation here
    // }

    // private static void manageBooks() {
    // System.out.println("Managing books...");
    // // Implementation here
    // }

    // private static void issueBooks() {
    // System.out.println("Issuing books...");
    // // Implementation here
    // }

    // private static void returnBooks() {
    // System.out.println("Returning books...");
    // // Implementation here
    // }

    // private static void reserveBooks() {
    // System.out.println("Reserving books...");
    // // Implementation here
    // }

    // private static void verifyStudents() {
    // System.out.println("Verifying students...");
    // // Implementation here
    // }

    // private static void viewIssuedBooks(Student student) {
    // System.out.println("Viewing issued books for student: " + student.getName());
    // // Implementation here
    // }

    // private static void viewReservedBooks(Student student) {
    // System.out.println("Viewing reserved books for student: " +
    // student.getName());
    // // Implementation here
    // }

    // private static void viewAccountBalance(Student student) {
    // System.out.println("Account balance for student: " + student.getName() + " is
    // " + student.getAccountBalance());
    // // Implementation here
    // }
}
