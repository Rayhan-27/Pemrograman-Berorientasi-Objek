import java.io.*;

public class InfoFile {
    public static void main(String[] args) {
        File f = new File("File.txt");

        if (f.exists()) { // Bila file ada maka jalankan
            System.out.println("File name: " + f.getName()); // menampilkan nama file
            System.out.println("Absolute Path: " + f.getAbsolutePath()); // menampilkan path / lokasi file
            System.out.println("Writable : " + f.canWrite()); // menampilkan status writable (bisa diubah / diisi)
            System.out.println("Readable : " + f.canRead()); // menampilkan status readable (bisa dibaca)
            System.out.println("File Size : " + f.length() + " bytes"); // menampilkan ukuran file (dalam byte)
        }
        else {
            System.out.println("File tidak ada");
        }
    }
}
