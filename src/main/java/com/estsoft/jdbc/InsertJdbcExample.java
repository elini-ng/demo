package com.estsoft.jdbc;

import java.sql.*;

public class InsertJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "admin";
    private static final String sql = "insert into students(name, age, address) values ('name2', 23, 'Tampines')";

    public static void main(String[] args) {
        try(
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();) {

                int insertCnt = statement.executeUpdate(sql);
                System.out.println("Insert count: " + insertCnt);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + + e.getErrorCode() + " - " + e.getMessage());
        }
    }
}
