package com.library.service;

import com.library.dao.AdminDAO;
import com.library.model.Admin;

public class AuthenticationService {
    private static Admin loggedInAdmin;
    private AdminDAO adminDAO;

    public AuthenticationService() {
        this.adminDAO = new AdminDAO();
    }

    public boolean login(String username, String password) {
        Admin admin = adminDAO.getAdminByUsernameAndPassword(username, password);
        if (admin != null) {
            loggedInAdmin = admin;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInAdmin = null;
    }

    public static Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public static boolean isAdminLoggedIn() {
        return loggedInAdmin != null;
    }
}
