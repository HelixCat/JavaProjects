package database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static String url = "";
    private static String driverName = "";
    private static String username = "";
    private static String password = "";
    private static Connection connection;


    public static void setValue(){
        try {

            InputStream inputStream = ConnectionManager.class.getClassLoader().getResourceAsStream("config-db.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            url = prop.getProperty("db.url");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            driverName = prop.getProperty("db.jdbcDriver");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                connection =  DriverManager.getConnection(url, username, password);
                System.out.println("Connected to Database.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }

        return connection;
    }

}
