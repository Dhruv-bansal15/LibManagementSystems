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