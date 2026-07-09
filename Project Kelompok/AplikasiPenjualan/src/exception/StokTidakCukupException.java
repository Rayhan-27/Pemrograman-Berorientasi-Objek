package exception;

/**
 * Exception khusus (buatan sendiri) yang dilempar kalau stok barang
 * tidak mencukupi jumlah yang mau dibeli di form transaksi.
 * Ini contoh implementasi "Exception Handling" custom di project.
 */
public class StokTidakCukupException extends Exception {
    public StokTidakCukupException(String pesan) {
        super(pesan);
    }
}
