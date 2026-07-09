package model;

/**
 * Model data Pelanggan. Sama seperti User, class ini extends Person
 * supaya kepakai konsep inheritance-nya.
 */
public class Pelanggan extends Person {

    private String kodePelanggan;
    private String alamat;
    private String noTelepon;

    public Pelanggan() {
        super();
    }

    public Pelanggan(int id, String nama, String kodePelanggan, String alamat, String noTelepon) {
        super(id, nama);
        this.kodePelanggan = kodePelanggan;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public String getKodePelanggan() {
        return kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) {
        this.kodePelanggan = kodePelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    @Override
    public String getInfo() {
        return "Pelanggan: " + getNama() + " (" + kodePelanggan + ") - Telp: " + noTelepon;
    }
}
