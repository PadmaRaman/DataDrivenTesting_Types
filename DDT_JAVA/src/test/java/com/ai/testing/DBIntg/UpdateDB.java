package com.ai.testing.DBIntg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDB {
    public static void main(String[] args) throws SQLException {
        // JDBC Connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/automationexerciseDB";
        String username = "root";
        String password = "root";

        //Update Query
        String updateQuery = "Update testUsers set Age=? where name=?";

        //Establish Connection to DB
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username,password);
            PreparedStatement pstatement = connection.prepareStatement(updateQuery)) {
            pstatement.setInt(1, 31);
            pstatement.setString(2, "John Doe");

            int rowsUpdated = pstatement.executeUpdate();
            System.out.println("The rows has been updated successfully" + rowsUpdated);
            }


        }
    }

