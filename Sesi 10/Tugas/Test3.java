import java.io.*;

public class Test3 {
    public void methodA() {
        System.out.println("Method A");
    }
    public void methodB() throws IOException {
        System.out.println(20 / 0);
        System.out.println("Method B");
    }
}

// class Utama {
//     public static void main(String[] args) {
//         Test3 c =  new Test3();
//         c.methodA();
//         c.methodB();
//     }
// }
// pada c.methodB() program akan memaksa untuk menangkap exception yang terjadi karena adanya exception yaitu ArithmeticException 20/0
// jadi program meminta untuk c.methodB() untuk memakai try-catch atau throw untuk menangani Exception nya
// untuk IOException, itu untuk menangani kesalahan berupa Input/Output yang terjadi saat program dijalankan
// namun ArithmeticException bukan Input/Output Exception, sehingga tidak ada penanganan untuk c.methodB()
// maka program akan terhenti karena terjadi ArithmeticException yang belum tertangani

// Perbaikan class Utama
class Utama {
    public static void main(String[] args) {
        Test3 c =  new Test3();
        c.methodA();
        try {
            c.methodB();
        }
        catch (Exception e) {
            System.out.println("Error di Method B");
        }
        finally {
            System.out.println("Ini selalu di cetak");
        }
    }
}
// Ketika running methodB terjadi ArithmeticException, maka program akan menampilkan "Error di Method B"