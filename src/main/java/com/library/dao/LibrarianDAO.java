// src/main/java/dao/LibrarianDAO.java
package com.library.dao;

import com.library.model.Librarian;
import com.library.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDAO {
    public void addLibrarian(Librarian librarian) throws SQLException {
        String query = "INSERT INTO Librarian VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, librarian.getLibrarianId());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getSection());
            statement.setBoolean(4, librarian.isApproved());
            statement.executeUpdate();
        }
    }

    public Librarian getLibrarian(String librarianId) throws SQLException {
        String query = "SELECT * FROM Librarian WHERE librarian_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, librarianId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setLibrarianId(resultSet.getString("librarian_id"));
                librarian.setName(resultSet.getString("name"));
                librarian.setSection(resultSet.getString("section"));
                librarian.setApproved(resultSet.getBoolean("approved"));
                return librarian;
            }
        }
        return null;
    }

    public void updateLibrarian(Librarian librarian) throws SQLException {
        String query = "UPDATE Librarian SET name = ?, section = ?, approved = ? WHERE librarian_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, librarian.getName());
            statement.setString(2, librarian.getSection());
            statement.setBoolean(3, librarian.isApproved());
            statement.setString(4, librarian.getLibrarianId());
            statement.executeUpdate();
        }
    }

    public void deleteLibrarian(String librarianId) throws SQLException {
        String query = "DELETE FROM Librarian WHERE librarian_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, librarianId);
            statement.executeUpdate();
        }
    }

    public void approveLibrarian(String librarianId) throws SQLException {
        String query = "UPDATE Librarian SET approved = 1 WHERE librarian_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, librarianId);
            statement.executeUpdate();
        }
    }

    public void assignSection(String librarianId, String section) throws SQLException {
        String query = "UPDATE Librarian SET section = ? WHERE librarian_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, section);
            statement.setString(2, librarianId);
            statement.executeUpdate();
        }
    }
}
