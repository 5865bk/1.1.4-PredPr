package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/- db1.1.4";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Iamguru50570723";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            System.out.println("OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
//            System.out.println("ERROR");
        }
        return connection;
    }
}
