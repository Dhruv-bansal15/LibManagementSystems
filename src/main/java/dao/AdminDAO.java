// src/main/java/dao/AdminDAO.java
package dao;

import model.Admin;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public void addAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO Admin VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getAdminId());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getPassword());
            statement.executeUpdate();
        }
    }

    public Admin getAdmin(String adminId) throws SQLException {
        String query = "SELECT * FROM Admin WHERE admin_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getString("admin_id"));
                admin.setName(resultSet.getString("name"));
                admin.setPassword(resultSet.getString("password"));
                return admin;
            }
        }
        return null;
    }

    public void updateAdmin(Admin admin) throws SQLException {
        String query = "UPDATE Admin SET name = ?, password = ? WHERE admin_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getAdminId());
            statement.executeUpdate();
        }
    }

    public void deleteAdmin(String adminId) throws SQLException {
        String query = "DELETE FROM Admin WHERE admin_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminId);
            statement.executeUpdate();
        }
    }
}
