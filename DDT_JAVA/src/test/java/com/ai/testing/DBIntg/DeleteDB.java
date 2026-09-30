package com.ai.testing.DBIntg;

import java.sql.*;

public class DeleteDB {
    public static void main(String[] args) throws SQLException {
        // JDBC Connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/automationexerciseDB";
        String username = "root";
        String password = "root";

        //Update Query
        String deleteQuery = "Delete from testUsers where id=?";
        String deleteTable = "Drop table IF EXISTS testUsers";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstatement = connection.prepareStatement(deleteQuery)) {

            pstatement.setInt(1, 2);

            int rowstobeDeleted = pstatement.executeUpdate();
            System.out.println("The table Data deleted successfully" + rowstobeDeleted);

            try (Statement statement = connection.createStatement()) {
                statement.execute(deleteTable);
                System.out.println("Table testUSers deleted successfully");
            }
        }
    }
}
