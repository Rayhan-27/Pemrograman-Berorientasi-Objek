package com.rayhan.perpustakaan.model;

public class BukuNonFiksi extends Buku {

    public BukuNonFiksi(String id, String judul) {
        super(id, judul);
    }

    @Override
    public void displayInfo() {
        System.out.println("[NON FIKSI] " + getId() + " - " + getJudul());
    }
}