package com.ai.testing.DBIntg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private String jdbcUrl;
    private String username;
    private String password;

    public DBUtils(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    // Executes SELECT query and returns data as List of Maps (ideal for Data-Driven Testing)
    public List<Map<String, Object>> executeQuery(String query, Object... params) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Bind variable parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> rowMap = new HashMap<>();
                    for (int col = 1; col <= columnCount; col++) {
                        String columnName = metaData.getColumnLabel(col);
                        Object value = rs.getObject(col);
                        rowMap.put(columnName, value);
                    }
                    resultList.add(rowMap);
                }
            }
        }
        return resultList;
    }

    // Executes INSERT / UPDATE / DELETE statements
    public int executeUpdate(String query, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            return pstmt.executeUpdate();
        }
    }
}