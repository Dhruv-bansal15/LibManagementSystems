// src/main/java/dao/StudentDAO.java
package com.library.dao;

// import com.library.model.Librarian;
import com.library.model.Student;
// import com.library.model.Transaction;
import com.library.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String query = "INSERT INTO student (studentId, name, username, password, accountBalance, numIssuedBooks) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getUsername());
            statement.setString(4, student.getPassword());
            statement.setInt(5, 0);
            statement.setInt(6, 0);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
        
    }

    public Student getStudent(int studentId) {
        String query = "SELECT * FROM student WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int accountBalance = resultSet.getInt("accountBalance");
                int numIssuedBooks = resultSet.getInt("numIssuedBooks");

                return new Student(studentId, name, username, password, accountBalance, numIssuedBooks);
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return null;
        }
        return null;
    }

    public int getNumIssuedBooksById(int studentId) {
        String query = "SELECT numIssuedBooks FROM student WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int numIssuedBooks = resultSet.getInt("numIssuedBooks");
                return numIssuedBooks;
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return -1;
        }
        return -1;
    }

    public int getAccountBalanceById(int studentId) {
        String query = "SELECT accountBalance FROM student WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int accountBalance = resultSet.getInt("accountBalance");
                return accountBalance;
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return -1;
        }
        return -1;
    }

    public boolean updateStudent(Student student) {
        String query = "UPDATE student SET name = ?, accountBalance = ?, numIssuedBooks = ? WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setDouble(2, student.getAccountBalance());
            statement.setInt(3, student.getNumIssuedBooks());
            statement.setInt(4, student.getStudentId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int studentId) {
        String query = "DELETE FROM student WHERE studentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
            return false;
        }
    }
    

    // public List<Transaction> getIssuedBooksForStudent(int studentId) {
    //     List<Transaction> transactions = new ArrayList<>();
    //     String query = "SELECT t.transaction_id, t.book_id, t.issue_date, t.return_date, t.fine, t.rating " +
    //                    "FROM transaction t " +
    //                    "WHERE t.student_id = ?";
    //     try (Connection connection = DatabaseConnection.getConnection();
    //          PreparedStatement statement = connection.prepareStatement(query)) {
    //         statement.setInt(1, studentId);
    //         ResultSet resultSet = statement.executeQuery();
    //         while (resultSet.next()) {
    //             int transactionId = resultSet.getInt("transaction_id");
    //             int bookId = resultSet.getInt("book_id");
    //             java.sql.Date issueDate = resultSet.getDate("issue_date");
    //             java.sql.Date returnDate = resultSet.getDate("return_date");
    //             int fine = resultSet.getInt("fine");
    //             int rating = resultSet.getInt("rating");

    //             Transaction transaction = new Transaction(transactionId, studentId, bookId, issueDate, returnDate, fine, rating);
    //             transactions.add(transaction);
    //         }
    //     } catch (SQLException ex) {
    //         System.out.println("Error occured: " + ex.getMessage());
    //     }
    //     return transactions;
    // }

    public Student getStudentByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student student = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM student WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int studentId = rs.getInt("studentId");
                String name = rs.getString("name");
                int accountBalance = rs.getInt("accountBalance");
                int numIssuedBooks = rs.getInt("numIssuedBooks");

                student = new Student(studentId, name, username, password, accountBalance, numIssuedBooks);
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }

        return student;
    }

    public String getNameById(int studentId) {
        String query = "SELECT name FROM student WHERE studentId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    return name;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error occured: " + ex.getMessage());
        }
        return "";
    }
}