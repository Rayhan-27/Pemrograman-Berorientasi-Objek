package com.rayhan.perpustakaan.model;

public abstract class Buku {

    private String id;
    private String judul;
    private boolean dipinjam;

    public Buku(String id, String judul) {
        this.id = id;
        this.judul = judul;
        this.dipinjam = false;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public boolean isDipinjam() {
        return dipinjam;
    }

    public void setDipinjam(boolean dipinjam) {
        this.dipinjam = dipinjam;
    }

    // abstract method
    public abstract void displayInfo();
}