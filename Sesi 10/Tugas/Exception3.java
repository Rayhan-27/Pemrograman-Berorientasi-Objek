// public class Exception3 {

//     public static void main(String[] args) {
//         int bil = 10;
//         System.out.println(bil / 0);
//     }
// }
// program ini akan menghasilkan ArithmeticException karena pembagian dengan nol tidak diperbolehkan



// =========== Perbaikan kode 1 =================
// public class Exception3 {
//     public static void main(String[] args) {
//         int bil = 10;
//         try{
//             System.out.println(bil / 0);
//         }
//         catch (Exception e) {
//             System.out.println("Ini menghandle error yang terjadi");
//         }
//     }
// }
// >>>> kode ini akan menghandle semua jenis error yang terjadi dan mencetak pesan "Ini menghandle error yang terjadi"



//============ Perbaikan Kode 2 =================
public class Exception3 {

    public static void main(String[] args) {
        int bil = 10;
        try {
            System.out.println(bil / 0);
        } 
        catch (ArithmeticException ae) {
            System.out.println("Terjadi Aritmatika error");
        } 
        catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}
// Karena catch (ArithmeticException ae) akan menangkap ArithmeticException secara spesifik, maka pesan "Terjadi Aritmatika error" yang akan dicetak
// Bila error yang terjadi di luar ArithmeticException maka akan masuk ke Exception biasa