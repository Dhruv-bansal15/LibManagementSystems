-- Insert data into admin table
INSERT INTO admin (adminId, username, password, name) VALUES (1, 'admin1', 'pass123', 'John Doe');
INSERT INTO admin (adminId, username, password, name) VALUES (2, 'admin2', 'pass456', 'Jane Smith');

-- Insert data into librarian table
INSERT INTO librarian (librarianId, username, password, name, sectionId) VALUES (1, 'librarian1', 'libpass1', 'Alice Johnson', 1);
INSERT INTO librarian (librarianId, username, password, name, sectionId) VALUES (2, 'librarian2', 'libpass2', 'Bob Brown', 2);

-- Insert data into book table
INSERT INTO book (bookId, title, author, sectionId, availableCopies, rating, numIssues) VALUES (1, 'The Great Gatsby', 'F. Scott Fitzgerald', 1, 5, 4.5, 10);
INSERT INTO book (bookId, title, author, sectionId, availableCopies, rating, numIssues) VALUES (2, '1984', 'George Orwell', 2, 3, 4.7, 15);
INSERT INTO book (bookId, title, author, sectionId, availableCopies, rating, numIssues) VALUES (3, 'To Kill a Mockingbird', 'Harper Lee', 1, 2, 4.6, 8);
INSERT INTO book (bookId, title, author, sectionId, availableCopies, rating, numIssues) VALUES (4, 'The Catcher in the Rye', 'J.D. Salinger', 2, 4, 4.3, 12);

-- Insert data into student table
INSERT INTO student (studentId, name, username, password, accountBalance, numIssuedBooks) VALUES (1, 'Charlie Green', 'charlie', 'charlie123', 0, 1);
INSERT INTO student (studentId, name, username, password, accountBalance, numIssuedBooks) VALUES (2, 'Danielle Blue', 'danielle', 'danielle123', 10, 2);

-- Insert data into transaction table
INSERT INTO transaction (studentId, bookId, issueDate, returnDate, fine, rating) VALUES (1, 1, TO_DATE('2023-06-01', 'YYYY-MM-DD'), TO_DATE('2023-06-10', 'YYYY-MM-DD'), 0, 4.5);
INSERT INTO transaction (studentId, bookId, issueDate, returnDate, fine, rating) VALUES (2, 2, TO_DATE('2023-06-05', 'YYYY-MM-DD'), TO_DATE('2023-06-15', 'YYYY-MM-DD'), 5, 4.7);
INSERT INTO transaction (studentId, bookId, issueDate, returnDate, fine, rating) VALUES (2, 3, TO_DATE('2023-06-12', 'YYYY-MM-DD'), NULL, 0, NULL);