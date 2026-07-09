package gui;

import exception.StokTidakCukupException;
import koneksi.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class FormTransaksi extends JFrame {

    private JComboBox<String> comboPelanggan, comboBarang;
    private JTextField txtJumlah, txtTotal;
    private JTable tabelKeranjang;
    private DefaultTableModel modelKeranjang;
    private JButton btnTambahKeranjang, btnHapusBaris, btnSimpan;

    // dipakai buat nyimpen id asli dari nama yang lagi ditampilin di combo box
    private Map<String, Integer> mapPelanggan = new HashMap<>();
    private Map<String, double[]> mapBarang = new HashMap<>(); // isinya [idBarang, harga, stok]

    private double totalBayar = 0;

    public FormTransaksi() {
        setTitle("Transaksi Penjualan");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Transaksi"));

        comboPelanggan = new JComboBox<>();
        comboBarang = new JComboBox<>();
        txtJumlah = new JTextField();

        panelInput.add(new JLabel("Pelanggan"));
        panelInput.add(comboPelanggan);
        panelInput.add(new JLabel("Barang"));
        panelInput.add(comboBarang);
        panelInput.add(new JLabel("Jumlah"));
        panelInput.add(txtJumlah);

        btnTambahKeranjang = new JButton("Tambah ke Keranjang");
        JPanel panelTombolAtas = new JPanel();
        panelTombolAtas.add(btnTambahKeranjang);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombolAtas, BorderLayout.SOUTH);

        modelKeranjang = new DefaultTableModel(new String[]{"ID Barang", "Nama Barang", "Jumlah", "Subtotal"}, 0);
        tabelKeranjang = new JTable(modelKeranjang);
        JScrollPane scrollKeranjang = new JScrollPane(tabelKeranjang);

        JPanel panelBawah = new JPanel();
        btnHapusBaris = new JButton("Hapus Baris Terpilih");
        txtTotal = new JTextField(15);
        txtTotal.setEditable(false);
        btnSimpan = new JButton("Simpan Transaksi");

        panelBawah.add(btnHapusBaris);
        panelBawah.add(new JLabel("Total Bayar:"));
        panelBawah.add(txtTotal);
        panelBawah.add(btnSimpan);

        add(panelAtas, BorderLayout.NORTH);
        add(scrollKeranjang, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        muatDataPelanggan();
        muatDataBarang();

        btnTambahKeranjang.addActionListener(e -> tambahKeKeranjang());
        btnHapusBaris.addActionListener(e -> hapusBarisKeranjang());
        btnSimpan.addActionListener(e -> simpanTransaksi());
    }

    private void muatDataPelanggan() {
        String sql = "SELECT id_pelanggan, nama_pelanggan FROM pelanggan";

        try (Connection conn = Koneksi.getKoneksi();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            comboPelanggan.removeAllItems();
            mapPelanggan.clear();

            while (rs.next()) {
                String nama = rs.getString("nama_pelanggan");
                comboPelanggan.addItem(nama);
                mapPelanggan.put(nama, rs.getInt("id_pelanggan"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data pelanggan: " + ex.getMessage());
        }
    }

    private void muatDataBarang() {
        String sql = "SELECT id_barang, nama_barang, harga, stok FROM barang";

        try (Connection conn = Koneksi.getKoneksi();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            comboBarang.removeAllItems();
            mapBarang.clear();

            while (rs.next()) {
                String nama = rs.getString("nama_barang");
                comboBarang.addItem(nama);
                mapBarang.put(nama, new double[]{
                        rs.getInt("id_barang"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data barang: " + ex.getMessage());
        }
    }

    private void tambahKeKeranjang() {
        try {
            String namaBarang = (String) comboBarang.getSelectedItem();
            if (namaBarang == null) {
                JOptionPane.showMessageDialog(this, "Data barang belum ada, tambahkan dulu di menu Data Barang!");
                return;
            }

            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!");
                return;
            }

            double[] dataBarang = mapBarang.get(namaBarang);
            int idBarang = (int) dataBarang[0];
            double harga = dataBarang[1];
            int stok = (int) dataBarang[2];

            // validasi stok pakai custom exception, contoh Exception Handling
            if (jumlah > stok) {
                throw new StokTidakCukupException("Stok " + namaBarang + " tidak cukup! Sisa stok: " + stok);
            }

            double subtotal = harga * jumlah;

            modelKeranjang.addRow(new Object[]{idBarang, namaBarang, jumlah, subtotal});

            totalBayar += subtotal;
            txtTotal.setText(String.valueOf(totalBayar));
            txtJumlah.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
        } catch (StokTidakCukupException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Stok Tidak Cukup", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void hapusBarisKeranjang() {
        int baris = tabelKeranjang.getSelectedRow();
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris keranjang yang mau dihapus dulu!");
            return;
        }

        double subtotal = (double) modelKeranjang.getValueAt(baris, 3);
        totalBayar -= subtotal;
        txtTotal.setText(String.valueOf(totalBayar));
        modelKeranjang.removeRow(baris);
    }

    private void simpanTransaksi() {
        if (modelKeranjang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong, tambahkan barang dulu!");
            return;
        }

        String namaPelanggan = (String) comboPelanggan.getSelectedItem();
        if (namaPelanggan == null) {
            JOptionPane.showMessageDialog(this, "Pilih pelanggan dulu!");
            return;
        }

        int idPelanggan = mapPelanggan.get(namaPelanggan);
        String noTransaksi = "TRX" + System.currentTimeMillis();

        Connection conn = null;

        try {
            conn = Koneksi.getKoneksi();
            conn.setAutoCommit(false);

            // panggil stored procedure buat simpan data header transaksi
            CallableStatement csHeader = conn.prepareCall("{call sp_tambah_transaksi(?, ?, ?, ?)}");
            csHeader.setString(1, noTransaksi);
            csHeader.setInt(2, idPelanggan);
            csHeader.setDouble(3, totalBayar);
            csHeader.registerOutParameter(4, Types.INTEGER);
            csHeader.execute();

            int idTransaksiBaru = csHeader.getInt(4);

            // panggil stored procedure lagi buat simpan tiap baris detail barang di keranjang
            // trigger di database otomatis mengurangi stok barang tiap ada insert detail baru
            for (int i = 0; i < modelKeranjang.getRowCount(); i++) {
                int idBarang = (int) modelKeranjang.getValueAt(i, 0);
                int jumlah = (int) modelKeranjang.getValueAt(i, 2);
                double subtotal = (double) modelKeranjang.getValueAt(i, 3);

                CallableStatement csDetail = conn.prepareCall("{call sp_tambah_detail_transaksi(?, ?, ?, ?)}");
                csDetail.setInt(1, idTransaksiBaru);
                csDetail.setInt(2, idBarang);
                csDetail.setInt(3, jumlah);
                csDetail.setDouble(4, subtotal);
                csDetail.execute();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan dengan nomor: " + noTransaksi);

            modelKeranjang.setRowCount(0);
            totalBayar = 0;
            txtTotal.setText("");
            muatDataBarang(); // refresh biar stok yang ditampilkan sudah update

        } catch (SQLException ex) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex2) {
                System.out.println("Gagal rollback: " + ex2.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi: " + ex.getMessage());
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Gagal mengembalikan mode auto commit: " + ex.getMessage());
            }
        }
    }
}
