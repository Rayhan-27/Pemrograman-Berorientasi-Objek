package com.rayhan.perpustakaan.service;

public interface Peminjaman {
    void pinjamBuku(String id);
    void kembalikanBuku(String id);
}