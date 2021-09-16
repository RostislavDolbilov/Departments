package epam.rostislav.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppConnection {
    private static Connection connection;
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/company";
    private static final String USERNAME = "epam";
    private static final String PASSWORD = "epam";

    public Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}
