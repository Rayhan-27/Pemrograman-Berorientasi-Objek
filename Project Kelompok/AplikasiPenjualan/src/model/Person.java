package model;

/**
 * Class abstract ini jadi induk (parent) dari User dan Pelanggan.
 * Dipakai buat nunjukin konsep Inheritance dan Enkapsulasi (atribut private + getter setter).
 * Method getInfo() sengaja dibuat abstract supaya tiap anak class wajib
 * bikin versinya sendiri-sendiri (konsep Polimorfisme).
 */
public abstract class Person {

    private int id;
    private String nama;

    public Person() {
    }

    public Person(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // wajib di-override sama class turunannya, ini yang bikin konsep polimorfisme kepakai
    public abstract String getInfo();
}
