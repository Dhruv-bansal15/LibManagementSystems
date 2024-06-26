package com.library.dao;

import com.library.model.Transaction;
import com.library.util.DatabaseConnection;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
// import java.util.Date ;

// In transaction table, use DATE type for all the date fields

public class TransactionDAO {
    private static StudentDAO studentDAO = new StudentDAO();

    public boolean addTransaction(Transaction transaction) {
        String query1 = "INSERT INTO transaction (transactionId, studentId, bookId, issueDate, returnDate, fine, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String query2 = "Update student SET numIssuedBooks = ? WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement1 = connection.prepareStatement(query2);
            PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setInt(1, transaction.getTransactionId());
            statement.setInt(2, transaction.getStudentId());
            statement.setInt(3, transaction.getBookId());
            statement.setDate(4, new java.sql.Date(transaction.getIssueDate().getTime()));
            statement.setDate(5, null);
            statement.setInt(6, 0);
            statement.setInt(7, -1);
            int rowsInserted = statement.executeUpdate();
            int ans = studentDAO.getNumIssuedBooksById(transaction.getStudentId());
            
            statement1.setInt(1, ans + 1);
            statement1.setInt(2, transaction.getStudentId());

            int rowsInserted1 = statement1.executeUpdate();
            return rowsInserted > 0 && rowsInserted1 > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public boolean returnBook(int studentId, int bookId, java.util.Date returnDate, int rating) {
        String selectQuery = "SELECT issueDate FROM transaction WHERE studentId = ? AND bookId = ?";
        String updateTransactionQuery = "UPDATE transaction SET returnDate = ?, fine = ?, rating = ? WHERE studentId = ? AND bookId = ?";
        String updateStudentQuery = "UPDATE student SET accountBalance = accountBalance + ? WHERE studentId = ?";
        String updateBookRatingQuery = "UPDATE book SET rating = ( rating + ? )/2 WHERE bookId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                PreparedStatement updateTransactionStatement = connection.prepareStatement(updateTransactionQuery);
                PreparedStatement updateBookRatingStatement = connection.prepareStatement(updateBookRatingQuery);
                PreparedStatement updateStudentStatement = connection.prepareStatement(updateStudentQuery)) {

            selectStatement.setInt(1, studentId);
            selectStatement.setInt(2, bookId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("No transaction found for the given studentId and bookId.");
                return false;
            }

            java.sql.Date issueDateTemp = resultSet.getDate("issueDate");
            java.util.Date issueDate = new Date(issueDateTemp.getTime()); 
            long diffInMillies = returnDate.getTime() - issueDate.getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            double fine = 0;
            if (diffInDays > 15) {
                fine = (diffInDays - 15) * 10;
            }

            // Update the transaction table
            updateTransactionStatement.setDate(1, new java.sql.Date(returnDate.getTime()));
            updateTransactionStatement.setDouble(2, fine);
            updateTransactionStatement.setInt(3, rating);
            updateTransactionStatement.setInt(4, studentId);
            updateTransactionStatement.setInt(5, bookId);

            int rowsUpdated = updateTransactionStatement.executeUpdate();
            if (rowsUpdated <= 0) {
                System.out.println("Failed to update transaction table.");
                return false;
            }

            // Update the student table
            updateStudentStatement.setDouble(1, fine);
            updateStudentStatement.setInt(2, studentId);

            int studentRowsUpdated = updateStudentStatement.executeUpdate();
            if (studentRowsUpdated <= 0) {
                System.out.println("Failed to update student table.");
                return false;
            }

            updateBookRatingStatement.setInt(1, rating);
            updateBookRatingStatement.setInt(2, bookId);

            int bookRowsUpdated = updateBookRatingStatement.executeUpdate();
            if (bookRowsUpdated <= 0) {
                System.out.println("Failed to update book table.");
                return false;
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public List<Transaction> getTransactionsByStudentId(int studentId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaction WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("transactionId"),
                        resultSet.getInt("studentId"),
                        resultSet.getInt("bookId"),
                        resultSet.getDate("issueDate"),
                        resultSet.getDate("returnDate"),
                        resultSet.getDouble("fine"),
                        resultSet.getInt("rating"));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
        }
        return transactions;
    }
}