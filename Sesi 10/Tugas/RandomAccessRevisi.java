import java.io.*;

class RandomAccessRevisi {
    
    public static void main(String[] args) {
        String bookList[] = {"Satu", "Dua", "Tiga"};
        int yearList[] = {1920, 1230, 1940};
        try {
            RandomAccessFile books = new RandomAccessFile ("books.txt", "rw");
            for (int i=0 ; i<3 ; i++) {
                books.writeUTF (bookList[i]);
                books.writeInt (yearList[i]);
            }
            books.seek(0);
            System.out.println(books.readUTF()+ " " + books.readInt());
            System.out.println(books.readUTF()+ " " + books.readInt());
            System.out.println(books.readUTF()+ " " + books.readInt());
            System.out.println(books.readUTF()+ " " + books.readInt());
            books.close();
        }
        catch (IOException e) {
            System.out.println("Indeks melebihi batas");
        }
        System.out.println("test");
    }
}

// saat looping indeks 0 sampai 2 tidak ada error, namun ketika looping indeks 3 terjadi error karena sudah lebih
// namun untuk IOException itu harusnya error ketika gagal membuka file, file tidak ditemukan, gagal menulis file, stream error, atau koneksi terputus
// jadi Exception yang lebih tepat yaitu menggunakan ArrayIndexOutOfBoundsException