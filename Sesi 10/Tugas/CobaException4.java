// public class CobaException4 {
//     public static void main(String[] args) {
//         int bil = 10;
//         String b[] = {"a", "b", "c"};
//         try {
//             System.out.println(b[3]);
//             System.out.println(bil / 0);
//         }
//         catch (ArithmeticException ae) {
//             System.out.println("Terjadi Aritmatika error");
//         }
//         catch (ArrayIndexOutOfBoundsException aie) {
//             System.out.println("Melebihi jumlah array");
//         }
//         catch (Exception e) {
//             System.out.println("Terjadi error");
//         }
//     }
// }
// >>> Kode akan menjalankan Try yang pertama dan menangkap ArrayIndexOutOfBoundsException


// ======== Perbaikan Kode ============
public class CobaException4 {
    public static void main(String[] args) {
        int bil = 10;
        String b[] = {"a", "b", "c"};
        try {
            System.out.println(bil / 0);
            System.out.println(b[3]);
        }
        catch (ArithmeticException ae) {
            System.out.println("Terjadi Aritmatika error");
        }
        catch (ArrayIndexOutOfBoundsException aie) {
            System.out.println("Melebihi jumlah array");
        }
        catch (Exception e) {
            System.out.println("Terjadi error");
        }
    }
}
// >>> Ketika urutan try di tukar, maka hasilnya akan Aritmatika error karena bil/0 terlebih dahulu di jalankan