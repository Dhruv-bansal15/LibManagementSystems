// src/main/java/dao/StudentDAO.java
package dao;

import model.Student;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getCurrentlyIssuedBooks());
            statement.setDate(4, new java.sql.Date(student.getDateOfIssue().getTime()));
            statement.setDate(5, new java.sql.Date(student.getDateOfReturn().getTime()));
            statement.setDouble(6, student.getFineAmount());
            statement.setString(7, student.getAllBooksIssued());
            statement.executeUpdate();
        }
    }

    public Student getStudent(String studentId) throws SQLException {
        String query = "SELECT * FROM Student WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getString("student_id"));
                student.setName(resultSet.getString("name"));
                student.setCurrentlyIssuedBooks(resultSet.getString("currently_issued_books"));
                student.setDateOfIssue(resultSet.getDate("date_of_issue"));
                student.setDateOfReturn(resultSet.getDate("date_of_return"));
                student.setFineAmount(resultSet.getDouble("fine_amount"));
                student.setAllBooksIssued(resultSet.getString("all_books_issued"));
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE Student SET name = ?, currently_issued_books = ?, date_of_issue = ?, date_of_return = ?, fine_amount = ?, all_books_issued = ? WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getCurrentlyIssuedBooks());
            statement.setDate(3, new java.sql.Date(student.getDateOfIssue().getTime()));
            statement.setDate(4, new java.sql.Date(student.getDateOfReturn().getTime()));
            statement.setDouble(5, student.getFineAmount());
            statement.setString(6, student.getAllBooksIssued());
            statement.setString(7, student.getStudentId());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(String studentId) throws SQLException {
        String query = "DELETE FROM Student WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
        }
    }
}
