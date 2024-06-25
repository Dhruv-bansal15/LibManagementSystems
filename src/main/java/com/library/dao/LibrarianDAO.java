package com.library.dao;

import com.library.model.Librarian;
import com.library.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDAO {

    // Method to add a new librarian to the database
    public boolean addLibrarian(Librarian librarian) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO librarian (librarianId, username, password, name, sectionId) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, librarian.getLibrarianId());
            stmt.setString(2, librarian.getUsername());
            stmt.setString(3, librarian.getPassword());
            stmt.setString(4, librarian.getName());
            stmt.setInt(5, librarian.getSectionId());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(conn, stmt);
        }

        return success;
    }

    // Method to fetch a librarian by username and password (for authentication)
    public Librarian getLibrarianByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Librarian librarian = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM librarian WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int librarianId = rs.getInt("librarianId");
                String name = rs.getString("name");
                int sectionId = rs.getInt("sectionId");

                librarian = new Librarian(librarianId, username, password, name, sectionId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }

        return librarian;
    }

    // Method to fetch a librarian by librarianId from the database
    public Librarian getLibrarianById(int librarianId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Librarian librarian = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM librarian WHERE librarianId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, librarianId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                int sectionId = rs.getInt("sectionId");

                librarian = new Librarian(librarianId, username, password, name, sectionId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }

        return librarian;
    }

    // Method to update an existing librarian in the database
    // public boolean updateLibrarian(Librarian librarian) {
    //     Connection conn = null;
    //     PreparedStatement stmt = null;
    //     boolean success = false;

    //     try {
    //         conn = DatabaseConnection.getConnection();
    //         String query = "UPDATE librarians SET username = ?, password = ?, name = ?, is_verified = ?, section_id = ? WHERE librarian_id = ?";
    //         stmt = conn.prepareStatement(query);
    //         stmt.setString(1, librarian.getUsername());
    //         stmt.setString(2, librarian.getPassword());
    //         stmt.setString(3, librarian.getName());
    //         stmt.setBoolean(4, librarian.isVerified());
    //         stmt.setInt(5, librarian.getSectionId());
    //         stmt.setInt(6, librarian.getLibrarianId());

    //         int rowsUpdated = stmt.executeUpdate();
    //         if (rowsUpdated > 0) {
    //             success = true;
    //         }
    //     } catch (SQLException ex) {
    //         ex.printStackTrace();
    //     } finally {
    //         DatabaseConnection.closeResources(conn, stmt);
    //     }

    //     return success;
    // }

    

    // Method to delete a librarian from the database
    public boolean deleteLibrarian(int librarianId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM librarian WHERE librarianId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, librarianId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(conn, stmt);
        }

        return success;
    }
}
