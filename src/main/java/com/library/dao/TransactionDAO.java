package com.library.dao;

import com.library.model.Transaction;
import com.library.model.studentTransaction;
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
        String insertTransactionQuery = "INSERT INTO transaction (studentId, bookId, issueDate, returnDate, fine, rating) VALUES (?, ?, ?, ?, ?, ?)";
        String updateStudentQuery = "Update student SET numIssuedBooks = ? WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement transactionStatement = connection.prepareStatement(insertTransactionQuery)) {
            PreparedStatement studentStatement = connection.prepareStatement(updateStudentQuery);
            transactionStatement.setInt(1, transaction.getStudentId());
            transactionStatement.setInt(2, transaction.getBookId());
            transactionStatement.setDate(3, new java.sql.Date(transaction.getIssueDate().getTime()));
            transactionStatement.setDate(4, null);
            transactionStatement.setInt(5, 0);
            transactionStatement.setInt(6, -1);

            int numIssuedBooks = studentDAO.getNumIssuedBooksById(transaction.getStudentId());
            int accountBalance = studentDAO.getAccountBalanceById(transaction.getStudentId());

            if (numIssuedBooks >= 5){
                System.out.println("Student cannot issue any more books");
                return false;
            }
            if (accountBalance < 0) {
                System.out.println("Student must pay their remaining fine first");
                return false;
            }
            
            int transactionRowsInserted = transactionStatement.executeUpdate();
            
            studentStatement.setInt(1, numIssuedBooks + 1);
            studentStatement.setInt(2, transaction.getStudentId());

            int studentRowsInserted = studentStatement.executeUpdate();
            return studentRowsInserted > 0 && transactionRowsInserted  > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public boolean returnBook(int studentId, int bookId, java.util.Date returnDate, int rating) {
        String selectQuery = "SELECT issueDate FROM transaction WHERE studentId = ? AND bookId = ?";
        String updateTransactionQuery = "UPDATE transaction SET returnDate = ?, fine = ?, rating = ? WHERE studentId = ? AND bookId = ?";
        String updateStudentQuery = "UPDATE student SET accountBalance = accountBalance - ? WHERE studentId = ?";
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

            int fine = 0;
            if (diffInDays > 15) {
                fine = (int) (diffInDays - 15) * 10;
            }

            // Update the transaction table
            updateTransactionStatement.setDate(1, new java.sql.Date(returnDate.getTime()));
            updateTransactionStatement.setInt(2, fine);
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

    public List<studentTransaction> getTransactionsByStudentId(int studentId) {
        List<studentTransaction> studentTransactions = new ArrayList<>();
        String query = "SELECT t.transactionId, b.title, t.issueDate, t.returnDate, t.fine, t.rating FROM transaction t INNER JOIN book b on t.BOOKID = b.BOOKID where t.STUDENTID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                studentTransaction studentTransaction = new studentTransaction(
                    resultSet.getInt("transactionId"),
                    resultSet.getString("title"),
                    resultSet.getDate("issueDate"),
                    resultSet.getDate("returnDate"),
                    resultSet.getInt("fine"),
                    resultSet.getInt("rating")
                );
                studentTransactions.add(studentTransaction);
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
        }
        return studentTransactions;
    }
}