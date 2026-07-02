import java.io.*;
import java.util.Scanner;

public class BacaFile {
    public static void main(String[] args) {
        try {
            File f = new File ("File.txt");
            Scanner r = new Scanner (f); // Membaca file f menggunakan Scanner
            while (r.hasNextLine()) { // Selama masih ada baris yang bisa dibaca
                String data = r.nextLine(); // Membaca baris selanjutnya
                System.out.println(data); // Menampilkan data yang dibaca
            }
            r.close(); // Menutup Scanner
        }
        catch (FileNotFoundException e) { // Bila file tidak ditemukan akan di tangkap Exception ini
            System.out.println("Terjadi kesalahan file tidak ditemukan!"); // Menampilkan pesan kesalahan
            //e.printStackTrace();
        }
    }
}
