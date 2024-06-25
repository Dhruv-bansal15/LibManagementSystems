package com.library;

import java.util.Properties;

import com.library.service.AuthenticationService;
import com.library.service.AdminService;
import com.library.service.LibrarianService;
import com.library.service.StudentService;
import com.library.model.Admin;
import com.library.model.Librarian;
import com.library.model.Student;
import com.library.util.DatabaseConnection;
import com.library.dao.AdminDAO;
import com.library.dao.LibrarianDAO;

import java.util.Scanner;
import java.sql.*;

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthenticationService authService = new AuthenticationService();
    private static StudentService studentService = new StudentService();
    private static AdminService adminService = new AdminService();
    private static LibrarianService librarianService = new LibrarianService();
    private LibrarianDAO librarianDAO;
    private AdminDAO adminDAO;

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

        while (true) {
            System.out.println("Please select your role (1-Admin, 2-Librarian, 3-Student, 0-Exit): ");
            int role = Integer.parseInt(scanner.nextLine());
            switch (role) {
                case 1:
                    adminLogin();
                    System.out.println("admin login");
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
        // Assuming student login mechanism (e.g., name as username)
        System.out.println("Enter student name: ");
        String studentName = scanner.nextLine();

        Student student = studentService.getStudentByName(studentName);
        if (student != null) {
            System.out.println("Student logged in successfully.");
            studentMenu(student);
        } else {
            System.out.println("Student not found.");
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
            System.out.println("3- Update Student");
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
                case 3:
                    updateStudent();
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
            System.out.println("2- View Reserved Books");
            System.out.println("3- View Account Balance");
            System.out.println("0- Logout");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewIssuedBooks(student);
                    break;
                case 2:
                    viewReservedBooks(student);
                    break;
                case 3:
                    viewAccountBalance(student);
                    break;
                case 0:
                    System.out.println("Logged out.");
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
        } else{
                System.out.println("error");
        }
        
    }

    private static void deleteLibrarian() {
        System.out.println("Enter librarian Id: ");
        int librarianId = Integer.parseInt(scanner.nextLine());
        boolean ans = librarianDAO.deleteLibrarian(librarianId);
        if (ans) {
                System.out.println("librarian deleted successfully");
                adminMenu();
        } else{
                System.out.println("error");
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

        if(adminDAO.addAdmin(temp)){
                System.out.println("admin added successfully");
                adminMenu();
        }else{
            System.out.println("error");
        }
    }

    private static void addStudent() {
        System.out.println("Managing students...");
        // Implementation here
    }

    private static void deleteStudent() {
        System.out.println("Managing students...");
        // Implementation here
    }

    private static void updateStudent() {
        System.out.println("Managing students...");
        // Implementation here
    }

    private static void addBook() {
        System.out.println("Managing books...");
        // Implementation here
    }

    private static void deleteBook() {
        System.out.println("Managing books...");
        // Implementation here
    }

    private static void updateBook() {
        System.out.println("Managing books...");
        // Implementation here
    }

    private static void issueBook() {
        System.out.println("Issuing books...");
        // Implementation here
    }

    private static void returnBook() {
        System.out.println("Returning books...");
        // Implementation here
    }

    private static void viewIssuedBooks(Student student) {
        System.out.println("Viewing issued books for student: " + student.getName());
        // Implementation here
    }

    private static void viewReservedBooks(Student student) {
        System.out.println("Viewing reserved books for student: " +
                student.getName());
        // Implementation here
    }

    private static void viewAccountBalance(Student student) {
    System.out.println("Account balance for student: " + student.getName() + " is
    " + student.getAccountBalance());
    // Implementation here
    }
}
