package com.ai.testing.DBIntg;

import java.io.IOException;
import java.sql.*;

public class CreateLoadDB {
    public static void main(String[] args) throws SQLException {
        // JDBC Connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/automationexerciseDB";
        String username = "root";
        String password = "root";

        //Create Table SQL
        String createTableSQL = "CREATE TABLE IF NOT EXISTS testUsers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "age INT NOT NULL" +
                ")";
        //Insert Data SQL
        String insertDataSQL = "INSERT INTO testUsers (name, email, age) VALUES (?, ?, ?)";

        //Establish Connection to a Database
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            //Create Statement and Execute Create Table SQL
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
                System.out.println("Table 'testUsers' created successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Prepare Statement for Parameterized Insert Data SQL
            try (PreparedStatement pstatement = connection.prepareStatement(insertDataSQL)) {
                //Insert Data into the Table
                pstatement.setString(1, "John Doe");
                pstatement.setString(2, "  JohnDoe@gmail.com");
                pstatement.setInt(3, 30);
                pstatement.executeUpdate();
                
                pstatement.setString(1, "Jane Smith");
                pstatement.setString(2, "JaneSmith@yopmail.com");
                pstatement.setInt(3, 25);
                pstatement.executeUpdate();
                
                System.out.println("Data inserted successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
