// TO BE REMOVED

// src/main/java/service/BookIssueService.java
package service;

import dao.BookDAO;
import dao.BookIssueDAO;
import dao.StudentDAO;
import model.Book;
import model.BookIssue;
import model.Student;

import java.sql.SQLException;
import java.util.Date;

public class BookIssueService {
    private BookIssueDAO bookIssueDAO;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;

    public BookIssueService() {
        this.bookIssueDAO = new BookIssueDAO();
        this.bookDAO = new BookDAO();
        this.studentDAO = new StudentDAO();
    }

    public void issueBook(String studentId, String bookId) throws SQLException {
        Student student = studentDAO.getStudent(studentId);
        Book book = bookDAO.getBook(bookId);

        if (student != null && book != null && book.getNumCopiesAvailable() > 0) {
            BookIssue bookIssue = new BookIssue();
            bookIssue.setStudentId(studentId);
            bookIssue.setBookId(bookId);
            bookIssue.setDateOfIssue(new Date());
            bookIssueDAO.addBookIssue(bookIssue);

            book.setNumCopiesAvailable(book.getNumCopiesAvailable() - 1);
            bookDAO.updateBook(book);

            student.setCurrentlyIssuedBooks(student.getCurrentlyIssuedBooks() + "," + bookId);
            studentDAO.updateStudent(student);
        } else {
            System.out.println("Book not available or invalid student/book ID");
        }
    }

    public void returnBook(String studentId, String bookId, int rating) throws SQLException {
        BookIssue bookIssue = bookIssueDAO.getBookIssue(studentId, bookId);

        if (bookIssue != null) {
            bookIssue.setDateOfReturn(new Date());
            bookIssue.setRatingGiven(rating);
            bookIssueDAO.updateBookIssue(bookIssue);

            Book book = bookDAO.getBook(bookId);
            book.setNumCopiesAvailable(book.getNumCopiesAvailable() + 1);
            book.setNumIssues(book.getNumIssues() + 1);
            book.setRating((book.getRating() * (book.getNumIssues() - 1) + rating) / book.getNumIssues());
            bookDAO.updateBook(book);

            Student student = studentDAO.getStudent(studentId);
            student.setCurrentlyIssuedBooks(student.getCurrentlyIssuedBooks().replace("," + bookId, ""));
            student.setAllBooksIssued(student.getAllBooksIssued() + "," + bookId);
            studentDAO.updateStudent(student);
        } else {
            System.out.println("Book issue record not found");
        }
    }
}
