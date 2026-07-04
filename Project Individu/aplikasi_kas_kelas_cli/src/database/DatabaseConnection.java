package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Sesuaikan USER dan PASSWORD dengan konfigurasi MySQL di komputer Anda
    private static final String URL = "jdbc:mysql://localhost:3306/db_kas_kelas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Gagal terhubung ke database: " + e.getMessage());
        }
        return conn;
    }
}
