package com.library.dao;

import com.library.model.Admin;
import com.library.util.DatabaseConnection;

import java.sql.*;

public class AdminDAO {

    public void addAdmin(Admin admin) {
        String query = "INSERT INTO Admin (username, password, name) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getName());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                admin.setAdminId(keys.getInt(1));   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin getAdminById(int adminId) {
        String query = "SELECT * FROM Admin WHERE admin_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Admin(
                    resultSet.getInt("admin_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Other CRUD methods as necessary
}
