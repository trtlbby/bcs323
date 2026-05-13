# Database Setup for Activity 1 - BACSAIN

## Prerequisites
- MySQL Server installed and running
- MySQL command-line client or MySQL Workbench

## Step 1: Create Database and Tables

Run the following SQL commands in MySQL:

```sql
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
```

## Step 2: Create MySQL User (Recommended)

```sql
-- Create a dedicated MySQL user for the application
CREATE USER 'bcs323l_user'@'localhost' IDENTIFIED BY 'BCS323L@2026';
GRANT ALL PRIVILEGES ON bcs323l_oca.* TO 'bcs323l_user'@'localhost';
FLUSH PRIVILEGES;
```

## Step 3: Set Environment Variables

### On Windows (PowerShell):
```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/bcs323l_oca"
$env:DB_USER = "bcs323l_user"
$env:DB_PASSWORD = "BCS323L@2026"
$env:ACT1_ADMIN_CODE = "admin123"
```

### On Windows (Command Prompt):
```cmd
set DB_URL=jdbc:mysql://localhost:3306/bcs323l_oca
set DB_USER=bcs323l_user
set DB_PASSWORD=BCS323L@2026
set ACT1_ADMIN_CODE=admin123
```

### On Linux/Mac:
```bash
export DB_URL="jdbc:mysql://localhost:3306/bcs323l_oca"
export DB_USER="bcs323l_user"
export DB_PASSWORD="BCS323L@2026"
export ACT1_ADMIN_CODE="admin123"
```

### To make environment variables permanent (Windows):
1. Right-click **This PC** or **My Computer** → **Properties**
2. Click **Advanced system settings**
3. Click **Environment Variables**
4. Add new user variables:
   - `DB_URL`: `jdbc:mysql://localhost:3306/bcs323l_oca`
   - `DB_USER`: `bcs323l_user`
   - `DB_PASSWORD`: `BCS323L@2026`
   - `ACT1_ADMIN_CODE`: `admin123`
5. Click **OK** and restart VS Code

## Step 4: Verify Connection

The application will attempt to connect to the database when you:
1. Open an Employee Entry or User Entry module
2. Try to submit data

If connection fails, check:
- MySQL server is running
- Environment variables are set correctly
- Database and tables exist
- MySQL user has proper permissions

## Troubleshooting

| Error | Solution |
|-------|----------|
| "Missing required environment variable: DB_URL" | Set the DB_URL environment variable |
| "Failed to connect to database" | Check MySQL is running, credentials are correct |
| "Access denied for user" | Verify DB_USER and DB_PASSWORD are correct |
| "Unknown database" | Run the SQL to create the database |

## Default Credentials
- **Admin Code**: `admin123`
- **Database**: `bcs323l_oca`
- **Database User**: `bcs323l_user`
- **Database Password**: `BCS323L@2026`
