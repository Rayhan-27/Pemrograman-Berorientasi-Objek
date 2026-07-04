package model;

import java.sql.Date;

public class Payment {
    private int idPembayaran;
    private int idAnggota;
    private double nominal;
    private Date tanggalBayar;

    public Payment(int idAnggota, double nominal, Date tanggalBayar) {
        this.idAnggota = idAnggota;
        this.nominal = nominal;
        this.tanggalBayar = tanggalBayar;
    }

    public Payment(int idPembayaran, int idAnggota, double nominal, Date tanggalBayar) {
        this.idPembayaran = idPembayaran;
        this.idAnggota = idAnggota;
        this.nominal = nominal;
        this.tanggalBayar = tanggalBayar;
    }

    public int getIdPembayaran() {
        return idPembayaran;
    }

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }
}
