package gui;

import koneksi.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FormLaporan extends JFrame {

    private JTable tabelLaporan;
    private DefaultTableModel modelTabel;
    private JTextField txtIdTransaksi, txtHasilTotal;
    private JButton btnRefresh, btnHitungTotal;

    public FormLaporan() {
        setTitle("Laporan Penjualan");
        setSize(780, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelTabel = new DefaultTableModel(new String[]{
                "ID Transaksi", "No Transaksi", "Tanggal", "Pelanggan", "Barang", "Jumlah", "Subtotal", "Total Bayar"
        }, 0);
        tabelLaporan = new JTable(modelTabel);
        JScrollPane scrollTabel = new JScrollPane(tabelLaporan);

        JPanel panelBawah = new JPanel();
        btnRefresh = new JButton("Refresh Laporan");
        txtIdTransaksi = new JTextField(5);
        btnHitungTotal = new JButton("Hitung Total (Function)");
        txtHasilTotal = new JTextField(10);
        txtHasilTotal.setEditable(false);

        panelBawah.add(btnRefresh);
        panelBawah.add(new JLabel("ID Transaksi:"));
        panelBawah.add(txtIdTransaksi);
        panelBawah.add(btnHitungTotal);
        panelBawah.add(new JLabel("Total:"));
        panelBawah.add(txtHasilTotal);

        add(scrollTabel, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        muatLaporan();

        btnRefresh.addActionListener(e -> muatLaporan());
        btnHitungTotal.addActionListener(e -> hitungTotalTransaksi());
    }

    private void muatLaporan() {
        modelTabel.setRowCount(0);
        // ambil data langsung dari VIEW yang sudah gabungan beberapa tabel di database
        String sql = "SELECT * FROM view_laporan_penjualan";

        try (Connection conn = Koneksi.getKoneksi();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                modelTabel.addRow(new Object[]{
                        rs.getInt("id_transaksi"),
                        rs.getString("no_transaksi"),
                        rs.getTimestamp("tanggal"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("nama_barang"),
                        rs.getInt("jumlah"),
                        rs.getDouble("subtotal"),
                        rs.getDouble("total_bayar")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat laporan: " + ex.getMessage());
        }
    }

    private void hitungTotalTransaksi() {
        if (txtIdTransaksi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi ID Transaksi dulu!");
            return;
        }

        // panggil FUNCTION di database buat hitung ulang total transaksi tertentu
        String sql = "SELECT fn_hitung_total_transaksi(?) AS total";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(txtIdTransaksi.getText().trim()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtHasilTotal.setText(String.valueOf(rs.getDouble("total")));
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Transaksi harus berupa angka!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghitung total: " + ex.getMessage());
        }
    }
}
