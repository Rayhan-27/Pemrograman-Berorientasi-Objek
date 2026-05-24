public class ThrowExample2 {

    public static void main(String[] args) {
        try {
            throw new Exception ("Here's my Exception");
        }
        catch (Exception e) {
            System.out.println("Caught Exception");
            System.out.println("e.getMessage(): " + e.getMessage());
            System.out.println("e.toString(): " + e.toString());
            System.out.println("e.printStackTrace(): ");
            e.printStackTrace();
        }
    }
}
// Kita membuat Exception baru dengan pesan "Here's my Exception"
// Kemudian ketika menangkap Exception tersebut, otomatis akan menampilkan pesan yang sudah di buat yaitu "Here's my Exception"
// e.toString untuk menampilkan Nama Exception yang terjadi dan pesan errornya dalam bentuk string
// e.printStackTrace() untuk menampilkan letak kesalahan pada baris kode