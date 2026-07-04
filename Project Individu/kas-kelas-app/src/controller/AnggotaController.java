package controller;

import database.DatabaseConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnggotaController {

    // INSERT
    public void tambahAnggota(String nama, String kelas, int nomorAbsen) {
        String sql = "INSERT INTO anggota (nama, kelas, nomor_absen) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nama);
            stmt.setString(2, kelas);
            stmt.setInt(3, nomorAbsen);
            stmt.executeUpdate();
            System.out.println("Data anggota berhasil ditambahkan.");

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menambah anggota: " + e.getMessage());
        }
    }

    // SELECT
    public List<Student> lihatAnggota() {
        List<Student> daftar = new ArrayList<>();
        String sql = "SELECT * FROM anggota";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id_anggota"),
                        rs.getString("nama"),
                        rs.getString("kelas"),
                        rs.getInt("nomor_absen")
                );
                daftar.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat mengambil data anggota: " + e.getMessage());
        }
        return daftar;
    }
}
