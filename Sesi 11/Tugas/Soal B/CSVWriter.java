import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "D:students.csv";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan NIM   : ");
        String nim = scanner.nextLine();

        System.out.print("Masukkan Nama  : ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Umur  : ");
        String umur = scanner.nextLine();

        System.out.print("Masukkan Prodi : ");
        String prodi = scanner.nextLine();

        String newLine = nim + "," + nama + "," + umur + "," + prodi;

        // true -> mode append, supaya data lama tidak ditimpa
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(newLine);
            bw.newLine();
            System.out.println("\nData berhasil ditambahkan ke " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

// Menambahkan (append) 1 baris data mahasiswa baru ke file CSV berdasarkan input pengguna