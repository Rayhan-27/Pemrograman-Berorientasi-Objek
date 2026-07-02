import java.io.*;

public class TulisFile {
    public static void main(String[] args) {
        try{
            FileWriter fw = new FileWriter("File.txt");
            fw.write("Belajar Pemrograman File Handling");
            fw.close();
            System.out.println("Proses Berhasil !!");
        }
        catch (IOException e) {
            System.out.println("Terjadi kesalahan dalam penulisan file!");
            System.out.println(e.getMessage());
        }
    }
    
}
