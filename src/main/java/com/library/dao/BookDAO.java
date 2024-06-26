package com.library.dao;

import com.library.model.Book;
import com.library.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    // Method to add a new book to the database
    public boolean addBook(Book book) {
        String query = "INSERT INTO book (bookId, title, author, sectionId, availableCopies, rating, numIssues) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getSectionId());
            stmt.setInt(5, book.getAvailableCopies());
            stmt.setDouble(6, 5);
            stmt.setInt(7, 0);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to fetch a book by bookId from the database
    public Book getBookById(int bookId) {
        String query = "SELECT * FROM book WHERE bookId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    int sectionId = rs.getInt("sectionId");
                    int availableCopies = rs.getInt("availableCopies");
                    double rating = rs.getDouble("rating");
                    int numIssues = rs.getInt("numIssues");

                    return new Book(bookId, title, author, sectionId, availableCopies, rating, numIssues);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Method to fetch all books from the database
    // public List<Book> getAllBooks() {
    //     List<Book> books = new ArrayList<>();
    //     String query = "SELECT * FROM books";
    //     try (Connection conn = DatabaseConnection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(query);
    //          ResultSet rs = stmt.executeQuery()) {
    //         while (rs.next()) {
    //             int bookId = rs.getInt("book_id");
    //             String title = rs.getString("title");
    //             String author = rs.getString("author");
    //             String section = rs.getString("section");
    //             int availableCopies = rs.getInt("available_copies");
    //             double rating = rs.getDouble("rating");
    //             int numIssues = rs.getInt("num_issues");

    //             Book book = new Book(bookId, title, author, section, availableCopies, rating, numIssues);
    //             books.add(book);
    //         }
    //     } catch (SQLException ex) {
    //         System.out.println("Error occured: " + ex.getMessage());
    //     }
    //     return books;
    // }

    // Method to update an existing book in the database
    public boolean updateBook(Book book) {
        String query = "UPDATE book SET availableCopies = ?, rating = ?, numIssues = ? WHERE bookId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, book.getAvailableCopies());
            stmt.setDouble(2, book.getRating());
            stmt.setInt(3, book.getNumIssues());
            stmt.setInt(4, book.getBookId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    // Method to delete a book from the database
    public boolean deleteBook(int bookId) {
        String query = "DELETE FROM book WHERE bookId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public String getTitleById(int bookId) {
        String query = "SELECT title FROM book WHERE bookId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    return title;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
        }
        return "";
    }
}