package gui;

import koneksi.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormBarang extends JFrame {

    private JTextField txtId, txtKode, txtNama, txtHarga, txtStok, txtCari;
    private JTable tabelBarang;
    private DefaultTableModel modelTabel;
    private JButton btnTambah, btnEdit, btnHapus, btnBersih, btnCari;

    public FormBarang() {
        setTitle("Data Barang");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Barang"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtKode = new JTextField();
        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtStok = new JTextField();

        panelForm.add(new JLabel("ID Barang"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Kode Barang"));
        panelForm.add(txtKode);
        panelForm.add(new JLabel("Nama Barang"));
        panelForm.add(txtNama);
        panelForm.add(new JLabel("Harga"));
        panelForm.add(txtHarga);
        panelForm.add(new JLabel("Stok"));
        panelForm.add(txtStok);

        JPanel panelTombol = new JPanel();
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnBersih = new JButton("Bersihkan");
        panelTombol.add(btnTambah);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnBersih);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        JPanel panelCari = new JPanel();
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari Nama Barang:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        modelTabel = new DefaultTableModel(new String[]{"ID", "Kode", "Nama Barang", "Harga", "Stok"}, 0);
        tabelBarang = new JTable(modelTabel);
        JScrollPane scrollTabel = new JScrollPane(tabelBarang);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollTabel, BorderLayout.CENTER);
        add(panelCari, BorderLayout.SOUTH);

        tampilkanData("");

        btnTambah.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnBersih.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> tampilkanData(txtCari.getText().trim()));

        tabelBarang.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabelBarang.getSelectedRow();
            if (baris >= 0) {
                txtId.setText(modelTabel.getValueAt(baris, 0).toString());
                txtKode.setText(modelTabel.getValueAt(baris, 1).toString());
                txtNama.setText(modelTabel.getValueAt(baris, 2).toString());
                txtHarga.setText(modelTabel.getValueAt(baris, 3).toString());
                txtStok.setText(modelTabel.getValueAt(baris, 4).toString());
            }
        });
    }

    private void tampilkanData(String kataKunci) {
        modelTabel.setRowCount(0);
        // pencarian pakai LIKE supaya bisa cari sebagian nama saja
        String sql = "SELECT * FROM barang WHERE nama_barang LIKE ? ORDER BY id_barang";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + kataKunci + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelTabel.addRow(new Object[]{
                        rs.getInt("id_barang"),
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + ex.getMessage());
        }
    }

    private void tambahData() {
        if (txtKode.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kode dan nama barang wajib diisi!");
            return;
        }

        String sql = "INSERT INTO barang (kode_barang, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, txtKode.getText().trim());
            ps.setString(2, txtNama.getText().trim());
            ps.setDouble(3, Double.parseDouble(txtHarga.getText().trim()));
            ps.setInt(4, Integer.parseInt(txtStok.getText().trim()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data barang berhasil ditambahkan");
            bersihkanForm();
            tampilkanData("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + ex.getMessage());
        }
    }

    private void editData() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau diedit dulu di tabel!");
            return;
        }

        String sql = "UPDATE barang SET kode_barang=?, nama_barang=?, harga=?, stok=? WHERE id_barang=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, txtKode.getText().trim());
            ps.setString(2, txtNama.getText().trim());
            ps.setDouble(3, Double.parseDouble(txtHarga.getText().trim()));
            ps.setInt(4, Integer.parseInt(txtStok.getText().trim()));
            ps.setInt(5, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data barang berhasil diubah");
            bersihkanForm();
            tampilkanData("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + ex.getMessage());
        }
    }

    private void hapusData() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau dihapus dulu di tabel!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin mau hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM barang WHERE id_barang=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data barang berhasil dihapus");
            bersihkanForm();
            tampilkanData("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage());
        }
    }

    private void bersihkanForm() {
        txtId.setText("");
        txtKode.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
    }
}
