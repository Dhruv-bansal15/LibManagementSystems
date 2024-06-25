package com.library.service;

import com.library.dao.AdminDAO;
import com.library.dao.LibrarianDAO;
import com.library.dao.StudentDAO;
import com.library.model.Admin;
import com.library.model.Librarian;
import com.library.model.Student;

public class AuthenticationService {
    private static Admin loggedInAdmin;
    private static Librarian loggedInLibrarian;
    private static Student loggedInStudent;
    private AdminDAO adminDAO;
    private LibrarianDAO librarianDAO;
    private StudentDAO studentDAO;

    public AuthenticationService() {
        this.adminDAO = new AdminDAO();
        this.librarianDAO = new LibrarianDAO();
        this.studentDAO = new StudentDAO();
    }

    // Admin login
    public boolean loginAdmin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsernameAndPassword(username, password);
        if (admin != null) {
            loggedInAdmin = admin;
            return true;
        }
        return false;
    }

    // Librarian login
    public boolean loginLibrarian(String username, String password) {
        Librarian librarian = librarianDAO.getLibrarianByUsernameAndPassword(username, password);
        if (librarian != null) {
            loggedInLibrarian = librarian;
            return true;
        }
        return false;
    }

    // Student login (assuming login by student name)
    // public boolean loginStudent(String name) {
    //     Student student = studentDAO.getStudentByName(name);
    //     if (student != null && student.isVerified()) {
    //         loggedInStudent = student;
    //         return true;
    //     }
    //     return false;
    // }

    // Logout
    public void logout() {
        loggedInAdmin = null;
        loggedInLibrarian = null;
        loggedInStudent = null;
    }

    // Get logged-in admin
    public static Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    // Get logged-in librarian
    public static Librarian getLoggedInLibrarian() {
        return loggedInLibrarian;
    }

    // Get logged-in student
    public static Student getLoggedInStudent() {
        return loggedInStudent;
    }

    // Check if admin is logged in
    public static boolean isAdminLoggedIn() {
        return loggedInAdmin != null;
    }

    // Check if librarian is logged in
    public static boolean isLibrarianLoggedIn() {
        return loggedInLibrarian != null;
    }

    // Check if student is logged in
    public static boolean isStudentLoggedIn() {
        return loggedInStudent != null;
    }
}
