package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class ini tugasnya cuma satu: nyambungin aplikasi ke database MySQL pakai JDBC.
 * Dibuat static supaya di semua form tinggal panggil Koneksi.getKoneksi()
 * tanpa harus bikin object baru terus-terusan.
 */
public class Koneksi {

    // sesuaikan dengan nama database, username, dan password MySQL di komputer masing-masing
    private static final String URL = "jdbc:mysql://localhost:3306/db_penjualan";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection koneksi;

    // constructor sengaja dibuat private supaya class ini tidak bisa di-instantiate (cukup pakai method static)
    private Koneksi() {
    }

    public static Connection getKoneksi() throws SQLException {
        try {
            if (koneksi == null || koneksi.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                koneksi = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL tidak ditemukan, cek apakah file jar sudah ditambahkan ke library project. Detail: " + e.getMessage());
        }
        return koneksi;
    }

    public static void tutupKoneksi() {
        try {
            if (koneksi != null && !koneksi.isClosed()) {
                koneksi.close();
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi database: " + e.getMessage());
        }
    }
}
