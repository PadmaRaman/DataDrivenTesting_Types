package com.ai.testing.DBIntg;

import java.sql.*;

public class ReadDB {
    public static void main(String[] args) throws SQLException {
        // JDBC Connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/automationexerciseDB";
        String username = "root";
        String password = "root";

        //Select Query
        String selectQuery = "SELECT * FROM testUsers";

        //Establish Connection to a database and Execute Select Query
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            try(PreparedStatement statement = connection.prepareStatement(selectQuery)) {

                try(ResultSet resultSet = statement.executeQuery()) {

                    System.out.println("The data from the 'testUsers' table is as follows: ");
                    while(resultSet.next())
                    {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        int age = resultSet.getInt("age");

                        System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Age: " + age);
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
