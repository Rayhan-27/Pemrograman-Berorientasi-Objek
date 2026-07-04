package model;

public class Student extends Person {
    private int idAnggota;
    private int nomorAbsen;

    public Student(int idAnggota, String nama, String kelas, int nomorAbsen) {
        super(nama, kelas);
        this.idAnggota = idAnggota;
        this.nomorAbsen = nomorAbsen;
    }

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public int getNomorAbsen() {
        return nomorAbsen;
    }

    public void setNomorAbsen(int nomorAbsen) {
        this.nomorAbsen = nomorAbsen;
    }

    @Override
    public void tampilInfo() {
        System.out.println("ID: " + idAnggota +
                " | Nama: " + getNama() +
                " | Kelas: " + getKelas() +
                " | No. Absen: " + nomorAbsen);
    }
}
