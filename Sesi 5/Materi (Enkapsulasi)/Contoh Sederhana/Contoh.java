class Tabungan {
    private double saldo; //tidak di enkapsulasi (tidak ada modifier / default)

    void tambah(double jumlah) {
        saldo += jumlah;
    }

    void ambil(double jumlah) {
        saldo -= jumlah;
    }

    void infoSaldo() {
        System.out.println("Saldo :" + saldo);
    }
}

public class Contoh {
    public static void main(String[] args) {
        Tabungan Rayhan = new Tabungan();
        //Rayhan.saldo = -100000; //-------> bila tanpa enkapsulasi, bisa di ubah langsung
        Rayhan.ambil(10000); // --------> bila pakai enkapsulasi harus menggunakan method atau setter yang sudah di buat khusus
        Rayhan.tambah(100000);
        Rayhan.infoSaldo();
    }
}
