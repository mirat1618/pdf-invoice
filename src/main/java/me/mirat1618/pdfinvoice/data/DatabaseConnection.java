package me.mirat1618.pdfinvoice.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:sqlite:";
    private static Connection connection;

    public static Connection getConnection() {
        connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL + getDbLocation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static String getDbLocation() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "database.properties");
            properties.load(fis);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("database.location");
    }
}
