public class Exception5 {
    
    public static void main(String[] args) {
        int bil = 10;
        try {
            System.out.println(bil / 0);
        }
        catch (ArithmeticException ae) {
            System.out.println("Pesan Error: ");
            System.out.println(ae.getMessage());
            System.out.println("Info stack trace: ");
            ae.printStackTrace();
            ae.printStackTrace(System.out);
        }
        catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}

// karena terdapat pembagian dengan 0, maka akan terjadi ArithmeticException
// pada ae.getMessage() akan mengembalikan pesan error yang terjadi
// pada ae.printStackTrace() akan menampilkan informasi letak baris program yang menyebabkan error
// pada ae.printStackTrace(System.out) sama seperti di atasnya namun outputnya di kirim ke System.out
// Exception e berfungsi untuk menangkap semua jenis exception selain ArithmeticException