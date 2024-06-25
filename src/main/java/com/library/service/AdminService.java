package com.library.service;

import com.library.dao.AdminDAO;
import com.library.model.Admin;
import com.library.model.Librarian;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
        // this.authService = new AuthenticationService();
    }

    // Method to add a new admin (only accessible by logged-in admin)
    public boolean addAdmin(Admin newAdmin) {
        Admin loggedInAdmin = AuthenticationService.getLoggedInAdmin();
        if (loggedInAdmin == null) {
            return false; // Only logged-in admin can add new admins
        }

        return adminDAO.addAdmin(newAdmin);
    }

    // Method to fetch an admin by username
    public Admin getAdminByUsername(String username) {
        return adminDAO.getAdminByUsername(username);
    }

    // Method to fetch an admin by adminId
    public Admin getAdminById(int adminId) {
        return adminDAO.getAdminById(adminId);
    }

    // Method to update an existing admin
    // public boolean updateAdmin(Admin admin) {
    //     Admin loggedInAdmin = AuthenticationService.getLoggedInAdmin();
    //     if (loggedInAdmin == null) {
    //         return false; // Only logged-in admin can update admins
    //     }

    //     return adminDAO.updateAdmin(admin);
    // }

    // // Method to delete an admin by adminId
    // public boolean deleteAdmin(int adminId) {
    //     Admin loggedInAdmin = AuthenticationService.getLoggedInAdmin();
    //     if (loggedInAdmin == null) {
    //         return false; // Only logged-in admin can delete admins
    //     }

    //     return adminDAO.deleteAdmin(adminId);
    // }

    // Method to verify a librarian by assigning them to a section
    // public boolean verifyLibrarian(Librarian librarian, String sectionName) {
    //     Admin loggedInAdmin = AuthenticationService.getLoggedInAdmin();
    //     if (loggedInAdmin == null) {
    //         return false; // Only logged-in admin can verify librarians
    //     }

    //     return adminDAO.assignLibrarianSection(librarian, sectionName);
    // }
}
