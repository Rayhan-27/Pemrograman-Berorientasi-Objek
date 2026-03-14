public class Produk {
    private final String nama; // final berfungsi untuk mencegah perubahan nilai atau penggunaan (generate) hanya terpakai sekali
    // String nama menggunakan final karena tidak ada fungsi Setter
    private final double harga;
    private int stok;

    Produk(String nama, double harga, int stok) { // Constructor untuk nilai default
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getInfo() {
        return "" + nama + " - Rp " + (int) harga + " (Stok: " + stok + ")";
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah <= stok) {
            stok -= jumlah;
            return true;
        }
        return false;
    }

    public String getNama() { 
        return nama; 
    }
    
    public double getHarga() { 
        return harga; 
    }
    
    public int getStok() { 
        return stok; 
    }
}
