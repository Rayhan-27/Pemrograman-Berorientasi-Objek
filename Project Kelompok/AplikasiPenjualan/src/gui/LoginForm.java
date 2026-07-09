package gui;

import koneksi.Koneksi;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnKeluar;

    public LoginForm() {
        setTitle("Login - Aplikasi Penjualan");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblJudul = new JLabel("LOGIN APLIKASI PENJUALAN");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblJudul, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username"), gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password"), gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtPassword, gbc);

        btnLogin = new JButton("Login");
        btnKeluar = new JButton("Keluar");

        JPanel panelTombol = new JPanel();
        panelTombol.add(btnLogin);
        panelTombol.add(btnKeluar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(panelTombol, gbc);

        btnLogin.addActionListener(this::aksiLogin);
        btnKeluar.addActionListener(e -> System.exit(0));
    }

    private void aksiLogin(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_user"));
                user.setNama(rs.getString("nama_lengkap"));
                user.setUsername(rs.getString("username"));
                user.setLevel(rs.getString("level"));

                JOptionPane.showMessageDialog(this, "Login berhasil, selamat datang " + user.getNama());
                new MainFrame(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau password salah!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan koneksi database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
