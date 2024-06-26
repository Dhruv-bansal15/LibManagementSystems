package com.library.dao;

import com.library.model.Transaction;
import com.library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// In transaction table, use DATE type for all the date fields

public class TransactionDAO {

    public boolean addTransaction(Transaction transaction) {
        String query = "INSERT INTO transaction (transactionId, studentId, bookId, issueDate, returnDate, fine, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, transaction.getTransactionId());
            statement.setInt(2, transaction.getStudentId());
            statement.setInt(3, transaction.getBookId());
            statement.setDate(4, new java.sql.Date(transaction.getIssueDate().getTime()));
            statement.setDate(5, null);
            statement.setDate(6, null);
            statement.setDate(7, null);
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public boolean returnBook(int transactionId, Date returnDate, double fine, int rating) {
        String query = "UPDATE transaction SET return_date = ?, fine = ?, rating = ? WHERE transaction_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(returnDate.getTime()));
            statement.setDouble(2, fine);
            statement.setInt(3, rating);
            statement.setInt(4, transactionId);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public List<Transaction> getTransactionsByStudentId(int studentId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaction WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getDate("issue_date"),
                        resultSet.getDate("return_date"),
                        resultSet.getDouble("fine"),
                        resultSet.getInt("rating")
                );
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
        }
        return transactions;
    }
}