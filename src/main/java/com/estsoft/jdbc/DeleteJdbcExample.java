package com.estsoft.jdbc;

import java.sql.*;

public class DeleteJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "admin";
    private static final String sql = "delete from students where id = 4";

    public static void main(String[] args) {

        try(
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();) {

                int deleteCnt = statement.executeUpdate(sql);
                System.out.println("Delete count: " + deleteCnt);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + + e.getErrorCode() + " - " + e.getMessage());
        }
    }
}
