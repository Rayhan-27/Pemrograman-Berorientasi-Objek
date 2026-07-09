package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model data Transaksi. Satu transaksi bisa punya banyak barang di dalamnya,
 * makanya disimpan dalam bentuk List<DetailBarang>.
 */
public class Transaksi {

    private int idTransaksi;
    private String noTransaksi;
    private String tanggal;
    private int idPelanggan;
    private double totalBayar;
    private List<DetailBarang> daftarBarang;

    public Transaksi() {
        daftarBarang = new ArrayList<>();
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }

    public List<DetailBarang> getDaftarBarang() {
        return daftarBarang;
    }

    public void tambahBarang(DetailBarang detail) {
        daftarBarang.add(detail);
    }

    /**
     * Class kecil di dalam Transaksi, buat nyimpen tiap baris barang
     * yang dibeli dalam satu transaksi (istilahnya "keranjang belanja").
     */
    public static class DetailBarang {
        private int idBarang;
        private String namaBarang;
        private int jumlah;
        private double subtotal;

        public DetailBarang(int idBarang, String namaBarang, int jumlah, double subtotal) {
            this.idBarang = idBarang;
            this.namaBarang = namaBarang;
            this.jumlah = jumlah;
            this.subtotal = subtotal;
        }

        public int getIdBarang() {
            return idBarang;
        }

        public String getNamaBarang() {
            return namaBarang;
        }

        public int getJumlah() {
            return jumlah;
        }

        public double getSubtotal() {
            return subtotal;
        }
    }
}
