// src/main/java/dao/BookDAO.java
package dao;

import model.Book;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO Book VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getBookId());
            statement.setString(2, book.getBookName());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getGenre());
            statement.setString(5, book.getSection());
            statement.setInt(6, book.getNumCopiesAvailable());
            statement.setDouble(7, book.getRating());
            statement.setInt(8, book.getNumIssues());
            statement.executeUpdate();
        }
    }

    public Book getBook(String bookId) throws SQLException {
        String query = "SELECT * FROM Book WHERE book_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getString("book_id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setSection(resultSet.getString("section"));
                book.setNumCopiesAvailable(resultSet.getInt("num_copies_available"));
                book.setRating(resultSet.getDouble("rating"));
                book.setNumIssues(resultSet.getInt("num_issues"));
                return book;
            }
        }
        return null;
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE Book SET book_name = ?, author = ?, genre = ?, section = ?, num_copies_available = ?, rating = ?, num_issues = ? WHERE book_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setString(4, book.getSection());
            statement.setInt(5, book.getNumCopiesAvailable());
            statement.setDouble(6, book.getRating());
            statement.setInt(7, book.getNumIssues());
            statement.setString(8, book.getBookId());
            statement.executeUpdate();
        }
    }

    public void deleteBook(String bookId) throws SQLException {
        String query = "DELETE FROM Book WHERE book_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookId);
            statement.executeUpdate();
        }
    }
}
