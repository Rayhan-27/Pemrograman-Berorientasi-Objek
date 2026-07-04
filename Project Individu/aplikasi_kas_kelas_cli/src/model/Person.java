package model;

public class Person {
    private String nama;
    private String kelas;

    public Person(String nama, String kelas) {
        this.nama = nama;
        this.kelas = kelas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    // Method ini akan di-override oleh class Student dan Treasurer (Polymorphism)
    public void tampilInfo() {
        System.out.println("Nama: " + nama + " | Kelas: " + kelas);
    }
}
