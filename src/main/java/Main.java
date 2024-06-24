// src/main/java/Main.java
import dao.*;
import model.*;
import service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static StudentDAO studentDAO = new StudentDAO();
    private static BookDAO bookDAO = new BookDAO();
    private static LibrarianDAO librarianDAO = new LibrarianDAO();
    private static AdminDAO adminDAO = new AdminDAO();
    private static FineService fineService = new FineService();
    private static BookIssueService bookIssueService = new BookIssueService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Librarian");
            System.out.println("2. Admin");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    librarianMenu(scanner);
                    break;
                case 2:
                    adminMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void librarianMenu(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();

        try {
            Librarian librarian = librarianDAO.getLibrarian(librarianId);

            if (librarian != null && librarian.isApproved()) {
                while (true) {
                    System.out.println("1. Add Student");
                    System.out.println("2. View Student");
                    System.out.println("3. Update Student");
                    System.out.println("4. Delete Student");
                    System.out.println("5. Add Book");
                    System.out.println("6. View Book");
                    System.out.println("7. Update Book");
                    System.out.println("8. Delete Book");
                    System.out.println("9. Issue Book");
                    System.out.println("10. Return Book");
                    System.out.println("11. Reserve Book");
                    System.out.println("12. Notify Admin for New Books");
                    System.out.println("13. Back");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            addStudent(scanner);
                            break;
                        case 2:
                            viewStudent(scanner);
                            break;
                        case 3:
                            updateStudent(scanner);
                            break;
                        case 4:
                            deleteStudent(scanner);
                            break;
                        case 5:
                            addBook(scanner);
                            break;
                        case 6:
                            viewBook(scanner);
                            break;
                        case 7:
                            updateBook(scanner);
                            break;
                        case 8:
                            deleteBook(scanner);
                            break;
                        case 9:
                            issueBook(scanner);
                            break;
                        case 10:
                            returnBook(scanner);
                            break;
                        case 11:
                            reserveBook(scanner);
                            break;
                        case 12:
                            notifyAdmin(scanner);
                            break;
                        case 13:
                            return;
                        default:
                            System.out.println("Invalid choice");
                    }
                }
            } else {
                System.out.println("Librarian not approved or invalid ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adminMenu(Scanner scanner) {
        System.out.println("Enter Admin ID:");
        String adminId = scanner.nextLine();

        try {
            Admin admin = adminDAO.getAdmin(adminId);

            if (admin != null) {
                while (true) {
                    System.out.println("1. Add Librarian");
                    System.out.println("2. View Librarian");
                    System.out.println("3. Update Librarian");
                    System.out.println("4. Delete Librarian");
                    System.out.println("5. Approve Librarian");
                    System.out.println("6. Assign Section to Librarian");
                    System.out.println("7. Add Student");
                    System.out.println("8. View Student");
                    System.out.println("9. Update Student");
                    System.out.println("10. Delete Student");
                    System.out.println("11. Back");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            addLibrarian(scanner);
                            break;
                        case 2:
                            viewLibrarian(scanner);
                            break;
                        case 3:
                            updateLibrarian(scanner);
                            break;
                        case 4:
                            deleteLibrarian(scanner);
                            break;
                        case 5:
                            approveLibrarian(scanner);
                            break;
                        case 6:
                            assignSection(scanner);
                            break;
                        case 7:
                            addStudent(scanner);
                            break;
                        case 8:
                            viewStudent(scanner);
                            break;
                        case 9:
                            updateStudent(scanner);
                            break;
                        case 10:
                            deleteStudent(scanner);
                            break;
                        case 11:
                            return;
                        default:
                            System.out.println("Invalid choice");
                    }
                }
            } else {
                System.out.println("Invalid Admin ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();
        System.out.println("Enter Student Name:");
        String name = scanner.nextLine();

        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(name);
        student.setAllBooksIssued("");
        student.setCurrentlyIssuedBooks("");
        student.setFineAmount(0);

        try {
            studentDAO.addStudent(student);
            System.out.println("Student added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewStudent(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();

        try {
            Student student = studentDAO.getStudent(studentId);
            if (student != null) {
                System.out.println("Student ID: " + student.getStudentId());
                System.out.println("Name: " + student.getName());
                System.out.println("All Books Issued: " + student.getAllBooksIssued());
                System.out.println("Currently Issued Books: " + student.getCurrentlyIssuedBooks());
                System.out.println("Fine Amount: " + student.getFineAmount());
            } else {
                System.out.println("Student not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();

        try {
            Student student = studentDAO.getStudent(studentId);
            if (student != null) {
                System.out.println("Enter new Name:");
                String name = scanner.nextLine();
                student.setName(name);
                studentDAO.updateStudent(student);
                System.out.println("Student updated successfully");
            } else {
                System.out.println("Student not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();

        try {
            studentDAO.deleteStudent(studentId);
            System.out.println("Student deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();
        System.out.println("Enter Book Name:");
        String bookName = scanner.nextLine();
        System.out.println("Enter Author:");
        String author = scanner.nextLine();
        System.out.println("Enter Genre:");
        String genre = scanner.nextLine();
        System.out.println("Enter Section:");
        String section = scanner.nextLine();
        System.out.println("Enter Number of Copies Available:");
        int numCopiesAvailable = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setSection(section);
        book.setNumCopiesAvailable(numCopiesAvailable);
        book.setRating(0);
        book.setNumIssues(0);

        try {
            bookDAO.addBook(book);
            System.out.println("Book added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewBook(Scanner scanner) {
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();

        try {
            Book book = bookDAO.getBook(bookId);
            if (book != null) {
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Book Name: " + book.getBookName());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Section: " + book.getSection());
                System.out.println("Number of Copies Available: " + book.getNumCopiesAvailable());
                System.out.println("Rating: " + book.getRating());
                System.out.println("Number of Issues: " + book.getNumIssues());
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();

        try {
            Book book = bookDAO.getBook(bookId);
            if (book != null) {
                System.out.println("Enter new Book Name:");
                String bookName = scanner.nextLine();
                System.out.println("Enter new Author:");
                String author = scanner.nextLine();
                System.out.println("Enter new Genre:");
                String genre = scanner.nextLine();
                System.out.println("Enter new Section:");
                String section = scanner.nextLine();
                System.out.println("Enter new Number of Copies Available:");
                int numCopiesAvailable = scanner.nextInt();
                scanner.nextLine();

                book.setBookName(bookName);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setSection(section);
                book.setNumCopiesAvailable(numCopiesAvailable);

                bookDAO.updateBook(book);
                System.out.println("Book updated successfully");
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBook(Scanner scanner) {
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();

        try {
            bookDAO.deleteBook(bookId);
            System.out.println("Book deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void issueBook(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();

        try {
            bookIssueService.issueBook(studentId, bookId);
            System.out.println("Book issued successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();
        System.out.println("Enter Rating (1-10):");
        int rating = scanner.nextInt();
        scanner.nextLine();

        try {
            bookIssueService.returnBook(studentId, bookId, rating);
            System.out.println("Book returned successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void reserveBook(Scanner scanner) {
        // Logic to reserve book
        System.out.println("Enter Student ID:");
        String studentId = scanner.nextLine();
        System.out.println("Enter Book ID:");
        String bookId = scanner.nextLine();

        // Add reservation logic here
        System.out.println("Book reserved successfully");
    }

    private static void notifyAdmin(Scanner scanner) {
        // Logic to notify admin for new books
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();
        System.out.println("Enter Book Name:");
        String bookName = scanner.nextLine();

        // Add notification logic here
        System.out.println("Admin notified successfully");
    }

    private static void addLibrarian(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();
        System.out.println("Enter Librarian Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Section:");
        String section = scanner.nextLine();

        Librarian librarian = new Librarian();
        librarian.setLibrarianId(librarianId);
        librarian.setName(name);
        librarian.setSection(section);
        librarian.setApproved(false);

        try {
            librarianDAO.addLibrarian(librarian);
            System.out.println("Librarian added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewLibrarian(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();

        try {
            Librarian librarian = librarianDAO.getLibrarian(librarianId);
            if (librarian != null) {
                System.out.println("Librarian ID: " + librarian.getLibrarianId());
                System.out.println("Name: " + librarian.getName());
                System.out.println("Section: " + librarian.getSection());
                System.out.println("Approved: " + librarian.isApproved());
            } else {
                System.out.println("Librarian not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateLibrarian(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();

        try {
            Librarian librarian = librarianDAO.getLibrarian(librarianId);
            if (librarian != null) {
                System.out.println("Enter new Name:");
                String name = scanner.nextLine();
                System.out.println("Enter new Section:");
                String section = scanner.nextLine();

                librarian.setName(name);
                librarian.setSection(section);

                librarianDAO.updateLibrarian(librarian);
                System.out.println("Librarian updated successfully");
            } else {
                System.out.println("Librarian not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteLibrarian(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();

        try {
            librarianDAO.deleteLibrarian(librarianId);
            System.out.println("Librarian deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void approveLibrarian(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();

        try {
            Librarian librarian = librarianDAO.getLibrarian(librarianId);
            if (librarian != null) {
                librarian.setApproved(true);
                librarianDAO.updateLibrarian(librarian);
                System.out.println("Librarian approved successfully");
            } else {
                System.out.println("Librarian not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void assignSection(Scanner scanner) {
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();
        System.out.println("Enter new Section:");
        String section = scanner.nextLine();

        try {
            Librarian librarian = librarianDAO.getLibrarian(librarianId);
            if (librarian != null) {
                librarian.setSection(section);
                librarianDAO.updateLibrarian(librarian);
                System.out.println("Section assigned successfully");
            } else {
                System.out.println("Librarian not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
