package com.estsoft.jdbc;

import java.sql.*;

public class UpdateJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "admin";
    private static final String sql = "update students set age = 21 where name = 'name2'";

    public static void main(String[] args) {

        try(
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();) {

                int updateCnt = statement.executeUpdate(sql);
                System.out.println("Update count: " + updateCnt);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + + e.getErrorCode() + " - " + e.getMessage());
        }
    }
}
