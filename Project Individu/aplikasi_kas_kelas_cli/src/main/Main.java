package main;

import controller.AnggotaController;
import controller.PembayaranController;
import java.util.List;
import model.Student;
import model.Treasurer;
import util.InputHelper;

public class Main {

    public static void main(String[] args) {
        Treasurer bendahara = new Treasurer("Admin Kas", "Kelas PBO");
        AnggotaController anggotaController = new AnggotaController();
        PembayaranController pembayaranController = new PembayaranController();

        System.out.println("=== Aplikasi Manajemen Kas Kelas ===");
        bendahara.tampilInfo();

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Kelola Anggota");
            System.out.println("2. Pembayaran Kas");
            System.out.println("3. Laporan");
            System.out.println("4. Keluar");
            int pilihan = InputHelper.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    menuKelolaAnggota(anggotaController);
                    break;
                case 2:
                    menuPembayaranKas(pembayaranController);
                    break;
                case 3:
                    menuLaporan(pembayaranController);
                    break;
                case 4:
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void menuKelolaAnggota(AnggotaController controller) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Kelola Anggota ---");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Lihat Data Anggota");
            System.out.println("3. Kembali");
            int pilihan = InputHelper.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    String nama = InputHelper.bacaString("Nama: ");
                    String kelas = InputHelper.bacaString("Kelas: ");
                    int absen = InputHelper.bacaInt("Nomor Absen: ");
                    controller.tambahAnggota(nama, kelas, absen);
                    break;
                case 2:
                    List<Student> daftar = controller.lihatAnggota();
                    System.out.println("=== Data Anggota ===");
                    if (daftar.isEmpty()) {
                        System.out.println("Belum ada data anggota.");
                    } else {
                        for (Student s : daftar) {
                            s.tampilInfo();
                        }
                    }
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void menuPembayaranKas(PembayaranController controller) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Pembayaran Kas ---");
            System.out.println("1. Tambah Pembayaran Kas");
            System.out.println("2. Lihat Riwayat Pembayaran");
            System.out.println("3. Kembali");
            int pilihan = InputHelper.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    int idAnggota = InputHelper.bacaInt("ID Anggota: ");
                    double nominal = InputHelper.bacaDouble("Nominal: ");
                    String tanggal = InputHelper.bacaString("Tanggal (format yyyy-MM-dd): ");
                    controller.tambahPembayaran(idAnggota, nominal, tanggal);
                    break;
                case 2:
                    controller.lihatRiwayatPembayaran();
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void menuLaporan(PembayaranController controller) {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Laporan ---");
            System.out.println("1. Lihat Total Kas (Function)");
            System.out.println("2. Lihat Laporan Pembayaran (View)");
            System.out.println("3. Keluar");
            int pilihan = InputHelper.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    double total = controller.lihatTotalKas();
                    System.out.println("Total Kas Terkumpul: Rp" + total);
                    break;
                case 2:
                    controller.lihatLaporanPembayaran();
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
