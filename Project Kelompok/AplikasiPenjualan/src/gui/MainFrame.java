package gui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private User userLogin;

    public MainFrame(User userLogin) {
        this.userLogin = userLogin;

        setTitle("Menu Utama - Aplikasi Penjualan");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblSelamat = new JLabel(
                "Selamat datang, " + userLogin.getNama() + " (" + userLogin.getLevel() + ")",
                SwingConstants.CENTER);
        lblSelamat.setFont(new Font("Arial", Font.BOLD, 14));
        lblSelamat.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblSelamat, BorderLayout.NORTH);

        JPanel panelMenu = new JPanel(new GridLayout(3, 2, 15, 15));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnUser = new JButton("Data User");
        JButton btnBarang = new JButton("Data Barang");
        JButton btnPelanggan = new JButton("Data Pelanggan");
        JButton btnTransaksi = new JButton("Transaksi Penjualan");
        JButton btnLaporan = new JButton("Laporan Penjualan");
        JButton btnLogout = new JButton("Logout");

        panelMenu.add(btnUser);
        panelMenu.add(btnBarang);
        panelMenu.add(btnPelanggan);
        panelMenu.add(btnTransaksi);
        panelMenu.add(btnLaporan);
        panelMenu.add(btnLogout);

        add(panelMenu, BorderLayout.CENTER);

        btnUser.addActionListener(e -> new FormUser().setVisible(true));
        btnBarang.addActionListener(e -> new FormBarang().setVisible(true));
        btnPelanggan.addActionListener(e -> new FormPelanggan().setVisible(true));
        btnTransaksi.addActionListener(e -> new FormTransaksi().setVisible(true));
        btnLaporan.addActionListener(e -> new FormLaporan().setVisible(true));

        btnLogout.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }
}
