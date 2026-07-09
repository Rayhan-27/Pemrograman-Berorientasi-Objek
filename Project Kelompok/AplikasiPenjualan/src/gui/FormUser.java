package gui;

import koneksi.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormUser extends JFrame {

    private JTextField txtId, txtUsername, txtNamaLengkap, txtCari;
    private JPasswordField txtPassword;
    private JComboBox<String> comboLevel;
    private JTable tabelUser;
    private DefaultTableModel modelTabel;
    private JButton btnTambah, btnEdit, btnHapus, btnBersih, btnCari;

    public FormUser() {
        setTitle("Data User");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data User"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtNamaLengkap = new JTextField();
        comboLevel = new JComboBox<>(new String[]{"admin", "kasir"});

        panelForm.add(new JLabel("ID User"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Username"));
        panelForm.add(txtUsername);
        panelForm.add(new JLabel("Password"));
        panelForm.add(txtPassword);
        panelForm.add(new JLabel("Nama Lengkap"));
        panelForm.add(txtNamaLengkap);
        panelForm.add(new JLabel("Level"));
        panelForm.add(comboLevel);

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
        panelCari.add(new JLabel("Cari Username:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        modelTabel = new DefaultTableModel(new String[]{"ID", "Username", "Nama Lengkap", "Level"}, 0);
        tabelUser = new JTable(modelTabel);
        JScrollPane scrollTabel = new JScrollPane(tabelUser);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollTabel, BorderLayout.CENTER);
        add(panelCari, BorderLayout.SOUTH);

        tampilkanData("");

        btnTambah.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnBersih.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> tampilkanData(txtCari.getText().trim()));

        tabelUser.getSelectionModel().addListSelectionListener(e -> {
            int baris = tabelUser.getSelectedRow();
            if (baris >= 0) {
                txtId.setText(modelTabel.getValueAt(baris, 0).toString());
                txtUsername.setText(modelTabel.getValueAt(baris, 1).toString());
                txtNamaLengkap.setText(modelTabel.getValueAt(baris, 2).toString());
                comboLevel.setSelectedItem(modelTabel.getValueAt(baris, 3).toString());
            }
        });
    }

    private void tampilkanData(String kataKunci) {
        modelTabel.setRowCount(0);
        String sql = "SELECT * FROM user WHERE username LIKE ? ORDER BY id_user";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + kataKunci + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelTabel.addRow(new Object[]{
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("nama_lengkap"),
                        rs.getString("level")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + ex.getMessage());
        }
    }

    private void tambahData() {
        if (txtUsername.getText().trim().isEmpty() || txtPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Username dan password wajib diisi!");
            return;
        }

        String sql = "INSERT INTO user (username, password, nama_lengkap, level) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, txtUsername.getText().trim());
            ps.setString(2, new String(txtPassword.getPassword()));
            ps.setString(3, txtNamaLengkap.getText().trim());
            ps.setString(4, comboLevel.getSelectedItem().toString());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data user berhasil ditambahkan");
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

        String sql = "UPDATE user SET username=?, password=?, nama_lengkap=?, level=? WHERE id_user=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, txtUsername.getText().trim());
            ps.setString(2, new String(txtPassword.getPassword()));
            ps.setString(3, txtNamaLengkap.getText().trim());
            ps.setString(4, comboLevel.getSelectedItem().toString());
            ps.setInt(5, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data user berhasil diubah");
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

        String sql = "DELETE FROM user WHERE id_user=?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data user berhasil dihapus");
            bersihkanForm();
            tampilkanData("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage());
        }
    }

    private void bersihkanForm() {
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtNamaLengkap.setText("");
        comboLevel.setSelectedIndex(0);
    }
}
