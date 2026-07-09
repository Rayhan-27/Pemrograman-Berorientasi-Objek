package gui;

import koneksi.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormPelanggan extends JFrame {

    private JTextField txtId, txtKode, txtNama, txtAlamat, txtTelepon, txtCari;
    private JTable tabelPelanggan;
    private DefaultTableModel modelTabel;
    private JButton btnTambah, btnEdit, btnHapus, btnBersih, btnCari;

    public FormPelanggan() {
        setTitle("Data Pelanggan");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Pelanggan"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtKode = new JTextField();
        txtNama = new JTextField();
        txtAlamat = new JTextField();
        txtTelepon = new JTextField();

        panelForm.add(new JLabel("ID Pelanggan"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Kode Pelanggan"));
        panelForm.add(txtKode);
        panelForm.add(new JLabel("Nama Pelanggan"));
        panelForm.add(txtNama);
        panelForm.add(new JLabel("Alamat"));
        panelForm.add(txtAlamat);
        panelForm.add(new JLabel("No Telepon"));
        panelForm.add(txtTelepon);

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
        panelCari.add(new JLabel("Cari Nama Pelanggan:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        modelTabel = new DefaultTableModel(new String[]{"ID", "Kode", "Nama", "Alamat", "No Telepon"}, 0);
        tabelPelanggan = new JTable(modelTabel);
        JScrollPane scrollTabel = new JScrollPane(tabelPelanggan);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollTabel, BorderLayout.CENTER);
        add(panelCari, BorderLayout.SOUTH);

        tampilkanData("");

        btnTambah.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnBersih.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> tampilkanData(txtCari.getText().trim()));

        tabelPelanggan.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabelPelanggan.getSelectedRow();
            if (baris >= 0) {
                txtId.setText(modelTabel.getValueAt(baris, 0).toString());
                txtKode.setText(modelTabel.getValueAt(baris, 1).toString());
                txtNama.setText(modelTabel.getValueAt(baris, 2).toString());
                txtAlamat.setText(modelTabel.getValueAt(baris, 3).toString());
                txtTelepon.setText(modelTabel.getValueAt(baris, 4).toString());
            }
        });
    }

    private void tampilkanData(String kataKunci) {
        modelTabel.setRowCount(0);
        String sql = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE ? ORDER BY id_pelanggan";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + kataKunci + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelTabel.addRow(new Object[]{
                        rs.getInt("id_pelanggan"),
                        rs.getString("kode_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_telepon")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + ex.getMessage());
        }
    }

    private void tambahData() {
        if (txtKode.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kode dan nama pelanggan wajib diisi!");
            return;
        }

        String sql = "INSERT INTO pelanggan (kode_pelanggan, nama_pelanggan, alamat, no_telepon) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, txtKode.getText().trim());
            ps.setString(2, txtNama.getText().trim());
            ps.setString(3, txtAlamat.getText().trim());
            ps.setString(4, txtTelepon.getText().trim());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil ditambahkan");
            bersihkanForm();
            tampilkanData("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + ex.getMessage());
        }
    }

    private void editData() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang mau diedit dulu di tabel!");
            return;
        }

        String sql = "UPDATE pelanggan SET kode_pelanggan=?, nama_pelanggan=?, alamat=?, no_telepon=? WHERE id_pelanggan=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, txtKode.getText().trim());
            ps.setString(2, txtNama.getText().trim());
            ps.setString(3, txtAlamat.getText().trim());
            ps.setString(4, txtTelepon.getText().trim());
            ps.setInt(5, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil diubah");
            bersihkanForm();
            tampilkanData("");

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

        String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil dihapus");
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
        txtAlamat.setText("");
        txtTelepon.setText("");
    }
}
