package com.rayhan.perpustakaan.main;

import com.rayhan.perpustakaan.model.Buku;
import com.rayhan.perpustakaan.model.BukuFiksi;
import com.rayhan.perpustakaan.model.BukuNonFiksi;
import com.rayhan.perpustakaan.service.Perpustakaan;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Perpustakaan perpus = new Perpustakaan();

        while (true) {
            System.out.println("\n=== MENU PERPUSTAKAAN ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Lihat Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {

                case 1:
                    System.out.print("Masukkan ID Buku: ");
                    String id = input.nextLine();

                    System.out.print("Masukkan Judul Buku: ");
                    String judul = input.nextLine();

                    System.out.print("Jenis (1. Fiksi / 2. Non Fiksi): ");
                    int jenis = input.nextInt();
                    input.nextLine();

                    Buku buku;

                    if (jenis == 1) {
                        buku = new BukuFiksi(id, judul);
                    } else {
                        buku = new BukuNonFiksi(id, judul);
                    }

                    perpus.tambahBuku(buku);
                    System.out.println("Buku berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.println("\n=== DAFTAR BUKU ===");
                    perpus.tampilkanBuku();
                    break;

                case 3:
                    System.out.print("Masukkan ID Buku yang dipinjam: ");
                    String idPinjam = input.nextLine();
                    perpus.pinjamBuku(idPinjam);
                    break;

                case 4:
                    System.out.print("Masukkan ID Buku yang dikembalikan: ");
                    String idKembali = input.nextLine();
                    perpus.kembalikanBuku(idKembali);
                    break;

                case 5:
                    System.out.println("Terima kasih!");
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}