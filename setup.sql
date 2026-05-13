-- Database setup script for BCS323L-OCa Activity 1
-- Run this in MySQL command line or MySQL Workbench

-- Create the database
CREATE DATABASE IF NOT EXISTS bcs323l_oca;
USE bcs323l_oca;

-- Create employees table
CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a dedicated MySQL user (optional but recommended)
-- Comment out if this user already exists
CREATE USER IF NOT EXISTS 'bcs323l_user'@'localhost' IDENTIFIED BY 'BCS323L@2026';
GRANT ALL PRIVILEGES ON bcs323l_oca.* TO 'bcs323l_user'@'localhost';
FLUSH PRIVILEGES;

-- Display created tables
SHOW TABLES;
DESCRIBE employees;
DESCRIBE users;
