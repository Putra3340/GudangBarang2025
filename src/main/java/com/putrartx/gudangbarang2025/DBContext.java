/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.putrartx.gudangbarang2025;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DBContext {

    private static final String URL = "jdbc:mysql://localhost:3306/databarangjava";
    private static final String USER = "puput";
    private static final String PASS = "123";

    // static initializer loads the driver once
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found. Add mysql-connector-j to dependencies!", e);
        }
    }

    // get a new connection each time
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}