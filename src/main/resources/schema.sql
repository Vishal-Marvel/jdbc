CREATE SCHEMA IF NOT EXISTS jdbcprojectdb;
use jdbcprojectdb;
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50)
);

CREATE TABLE Role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) UNIQUE
);

CREATE TABLE Login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    userId INT,
    FOREIGN KEY (userId) REFERENCES User(id)  on delete cascade
);

CREATE TABLE User_Role (
    user_id INT ,
    role_id INT ,
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES User(id) on delete cascade,
    FOREIGN KEY (role_id) REFERENCES Role(id)
);

-- Insert roles
INSERT INTO Role (name) VALUES ('ADMIN'), ('USER');

-- Insert admin user
INSERT INTO User (firstName, lastName) VALUES ('System', 'Admin');
INSERT INTO Login (username, password, userId) VALUES ('admin', 'admin123', 1);
INSERT INTO User_Role (user_id, role_id) VALUES (1, 1);
