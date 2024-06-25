CREATE TABLE admin (
    adminId NUMBER,
    username VARCHAR2(50),
    password VARCHAR2(50),
    name VARCHAR2(100)
);

INSERT INTO admin (adminId, username, password, name)
VALUES (1, 'dhruv087', 'dhruv087', 'dhruv');

CREATE TABLE librarian (
    librarianId NUMBER,
    username VARCHAR2(50),
    password VARCHAR2(50),
    name VARCHAR2(50),
    sectionId NUMBER
);

CREATE TABLE book (
    bookId NUMBER,
    title VARCHAR2(50),
    author VARCHAR2(50),
    sectionId NUMBER,
    availableCopies NUMBER,
    rating NUMBER,
    numIssues NUMBER
);

CREATE TABLE student (
    studentId NUMBER,
    name VARCHAR2(50),
    username VARCHAR2(50),
    password VARCHAR2(50),
    accountBalance NUMBER,
    numIssuedBooks NUMBER
);

CREATE TABLE transaction (
    transactionId NUMBER,
    studentId NUMBER,
    bookId NUMBER,
    issueDate DATE,
    returnDate DATE,
    fine NUMBER,
    rating NUMBER
);