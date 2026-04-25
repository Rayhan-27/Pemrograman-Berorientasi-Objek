package com.rayhan.perpustakaan.service;

import com.rayhan.perpustakaan.model.Buku;
import com.rayhan.perpustakaan.util.DataManager;
import java.util.ArrayList;

public class Perpustakaan implements Peminjaman {

    private DataManager<Buku> daftarBuku = new DataManager<>();

    public void tambahBuku(Buku buku) {
        daftarBuku.tambahData(buku);
    }
        
    public void tampilkanBuku() {
        for (Buku b : daftarBuku.getAllData()) {
            b.displayInfo();
        }
    }

    @Override
    public void pinjamBuku(String id) {
        for (Buku b : daftarBuku.getAllData()) {
            if (b.getId().equals(id) && !b.isDipinjam()) {
                b.setDipinjam(true);
                System.out.println("Buku berhasil dipinjam");
                return;
            }
        }
        System.out.println("Buku tidak tersedia");
    }

    @Override
    public void kembalikanBuku(String id) {
        for (Buku b : daftarBuku) {
            if (b.getId().equals(id) && b.isDipinjam()) {
                b.setDipinjam(false);
                System.out.println("Buku berhasil dikembalikan");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan");
    }
}