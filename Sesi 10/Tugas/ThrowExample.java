public class ThrowExample {
    
    static void demo() {
        NullPointerException t;
        t = new NullPointerException ("Coba Throw");
        throw t;
        // Baris ini tidak lagi dikerjakan
        //System.out.println("Ini tidak lagi di cetak"); bila baris ini di aktifkan maka error karena sudah di Throw (code selesai)
    }

    public static void main(String[] args) {
        try {
            demo ();
            System.out.println("Selesai");
        }
        catch (NullPointerException e) {
            System.out.println("Ada pesan error : " + e); // ini akan menampilkan pesan error yang terjadi dengan pesan yang sudah di setting pada demo()
        }
    }
}
