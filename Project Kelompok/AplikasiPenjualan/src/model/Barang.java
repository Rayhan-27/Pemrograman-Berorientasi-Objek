package model;

/**
 * Model data Barang. Class ini berdiri sendiri (tidak extends apa-apa)
 * karena barang bukan termasuk "orang", jadi tidak perlu extends Person.
 * Tetap pakai enkapsulasi (atribut private + getter setter).
 */
public class Barang {

    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private double harga;
    private int stok;

    public Barang() {
    }

    public Barang(int idBarang, String kodeBarang, String namaBarang, double harga, int stok) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return namaBarang + " - Rp" + harga;
    }
}
