DROP DATABASE IF EXISTS school;

CREATE DATABASE school
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE school;


CREATE TABLE tbl_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password CHAR(64) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(100) NOT NULL
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;


INSERT INTO tbl_user (
    username,
    password,
    email,
    fullname
) VALUES (
    'admin',
    SHA2('123456', 256),
    'admin@gmail.com',
    'Quản trị viên'
);


CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ho_ten VARCHAR(100) NOT NULL,
    lop VARCHAR(50) NOT NULL,
    mssv VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- Dữ liệu mẫu
INSERT INTO students (
    ho_ten,
    lop,
    mssv,
    email
) VALUES
(
    'Bùi Hoàng Oanh',
    '2305CT8',
    '2305CT8232',
    'hoangoanh1292005@gmail.com'
),
(
    'Huỳnh Như',
    '2305CT6',
    '2305CT0685',
    'huynhnhu@gmail.com'
);