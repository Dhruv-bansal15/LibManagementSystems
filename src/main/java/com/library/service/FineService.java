// src/main/java/service/FineService.java
package service;

import dao.BookIssueDAO;

public class FineService {
    private BookIssueDAO bookIssueDAO;

    public FineService() {
        this.bookIssueDAO = new BookIssueDAO();
    }

    public void imposeFine(String studentId, int daysLate) throws SQLException {
        double fine = calculateFine(daysLate);
        bookIssueDAO.updateFine(studentId, fine);
    }

    private double calculateFine(int daysLate) {
        return daysLate * 1.0; // Assuming $1 fine per day late
    }
}
