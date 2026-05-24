// public class Exception1 {

//     public static void main(String[] args) {
//         int a[] = new int [5];
//         a[5] = 100; 
//     }
// }
// kode di atas ketika saat di running akan error karena index 5 itu melebihi batas yang di tentukan, maximal index itu sampai 4

// Perbaikan Program
public class Exception1 {

    public static void main(String[] args) {
        int a[] = new int [5];
        try {
            a[5] = 100;
        }
        catch (ArrayIndexOutOfBoundsException e) { // karena index 5 itu melebihi batas maka di tankap (catch) Exception
            System.out.println("Terjadi pelanggaran memory");
        }
    }
}