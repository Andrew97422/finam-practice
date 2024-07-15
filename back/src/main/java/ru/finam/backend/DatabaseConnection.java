package ru.finam.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws IOException, SQLException {
        Properties props = new Properties();

        // Чтение файла из ресурсов
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }
            props.load(input);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        String driver = props.getProperty("db.driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, user, password);
    }

    public static void executeSqlFromFile(Connection conn, String filePath) throws SQLException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Statement stmt = conn.createStatement()) {
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
                // Assuming that each statement ends with a semicolon
                if (line.trim().endsWith(";")) {
                    try {
                        stmt.execute(sql.toString());
                    } catch (SQLException e) {
                        System.err.println("Error executing SQL: " + sql.toString());
                        throw e;
                    }
                    sql.setLength(0); // Clear the StringBuilder
                }
            }
            // Execute any remaining SQL if file doesn't end with a semicolon
            if (!sql.isEmpty()) {
                try {
                    stmt.execute(sql.toString());
                } catch (SQLException e) {
                    System.err.println("Error executing SQL: " + sql.toString());
                    throw e;
                }
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");

                // Путь к файлу схемы
                String schemaFilePath = "back/src/main/resources/schema.sql";
                executeSqlFromFile(conn, schemaFilePath);
                System.out.println("Schema executed successfully.");

                // Путь к файлу данных
                String dataFilePath = "back/src/main/resources/data.sql";
                executeSqlFromFile(conn, dataFilePath);
                System.out.println("Data inserted successfully.");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
