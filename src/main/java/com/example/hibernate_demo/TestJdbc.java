package com.example.hibernate_demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/?user=hbstudent?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";
        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myCon = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection succesfull!");
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
