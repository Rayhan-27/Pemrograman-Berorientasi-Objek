// public class Exception2 {
//     public static void main(String[] args) {
//         int i = 0;
//         String greeting [] = {
//             "Hello World!",
//             "No, I mean it!",
//             "Hellow World"
//         };
//         while(i < 4) {
//             System.out.println(greeting[i]);
//             i++;
//         }
//     }
// }
// Program ini akan menghasilkan ArrayIndexOutOfBoundsException karena index ke-4 tidak ada dalam array greeting yang hanya memiliki 3 elemen (index 0, 1, dan 2)

// Perbaikan Kode
public class Exception2 {
    public static void main(String[] args) {
        int i = 0;
        String greeting [] = {
            "Hello World!",
            "No, I mean it!",
            "Hellow World"
        };
        while(i < 4) {
            try {
                System.out.println(greeting[i]);
                i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Resetting index value");
                i = 0;
            }
        }
    }
}
// Ketika ada error yang tertangkap, program akan mencetak "Resetting index value" dan mengatur ulang nilai index i ke 0
// Maka terjadi looping tidak terhingga karena nilai i selalu direset ke 0