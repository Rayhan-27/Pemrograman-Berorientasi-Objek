package com.rayhan.perpustakaan.model;

public class BukuFiksi extends Buku {

    public BukuFiksi(String id, String judul) {
        super(id, judul);
    }

    @Override
    public void displayInfo() {
        System.out.println("[FIKSI] " + getId() + " - " + getJudul());
    }
}