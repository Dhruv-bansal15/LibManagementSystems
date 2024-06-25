package com.library.service;

import com.library.dao.AdminDAO;
import com.library.model.Admin;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    public void addAdmin(Admin admin) {
        if (!AuthenticationService.isAdminLoggedIn()) {
            throw new SecurityException("Only authenticated admins can add new admins.");
        }
        adminDAO.addAdmin(admin);
    }

    public Admin getAdminById(int adminId) {
        if (!AuthenticationService.isAdminLoggedIn()) {
            throw new SecurityException("Only authenticated admins can view admin details.");
        }
        return adminDAO.getAdminById(adminId);
    }

    // Other admin-related business logic as necessary
}
