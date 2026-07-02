import java.io.*;

public class BuatFile {
    public static void main(String[] args) {
        try {
            File f = new File("File.txt"); // Membuat file baru dengan path "File.txt"
            if (f.createNewFile()) { //Bila file belum di buat maka munculkan pesan
                System.out.println("File created: " + f.getName());
            }
            else { // Bila file sudah ada maka munculkan pesan "File already exists"
                System.out.println("File already exists");
            }
        }

        catch (IOException e) { // Bila terjadi error IO maka di tangkap di sini
            System.out.println("An error occured"); // memunculkan pesan error
            e.printStackTrace(); // menampilkan stack trace error atau informasi baris yang menyebabkan error
        }
    }
}