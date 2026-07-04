package controller;

import database.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PembayaranController {

    // Memanggil PROCEDURE tambah_pembayaran()
    public void tambahPembayaran(int idAnggota, double nominal, String tanggal) {
        String sql = "{CALL tambah_pembayaran(?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idAnggota);
            stmt.setDouble(2, nominal);
            stmt.setDate(3, Date.valueOf(tanggal));
            stmt.execute();
            System.out.println("Pembayaran kas berhasil dicatat.");

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menyimpan pembayaran: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Format tanggal salah. Gunakan format yyyy-MM-dd, contoh: 2026-07-03");
        }
    }

    // SELECT riwayat pembayaran
    public void lihatRiwayatPembayaran() {
        String sql = "SELECT * FROM pembayaran_kas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Riwayat Pembayaran Kas ===");
            while (rs.next()) {
                System.out.println("ID Pembayaran: " + rs.getInt("id_pembayaran") +
                        " | ID Anggota: " + rs.getInt("id_anggota") +
                        " | Nominal: " + rs.getDouble("nominal") +
                        " | Tanggal: " + rs.getDate("tanggal_bayar"));
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengambil riwayat: " + e.getMessage());
        }
    }

    // Memanggil FUNCTION total_kas()
    public double lihatTotalKas() {
        String sql = "SELECT total_kas() AS total";
        double total = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengambil total kas: " + e.getMessage());
        }
        return total;
    }

    // Mengambil data dari VIEW v_laporan_pembayaran
    public void lihatLaporanPembayaran() {
        String sql = "SELECT * FROM v_laporan_pembayaran";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Laporan Pembayaran Kas ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_pembayaran") +
                        " | Nama: " + rs.getString("nama") +
                        " | Kelas: " + rs.getString("kelas") +
                        " | Nominal: " + rs.getDouble("nominal") +
                        " | Tanggal: " + rs.getDate("tanggal_bayar"));
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengambil laporan: " + e.getMessage());
        }
    }
}
