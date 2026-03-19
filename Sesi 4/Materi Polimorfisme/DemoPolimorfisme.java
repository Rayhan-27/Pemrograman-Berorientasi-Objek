// SUPER CLASS
class Produk{
    protected String nama;
    protected int harga;

    public Produk(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String tampilInfo() {
        return "[UMUM]    " + nama + "     | Harga: " + harga;
    }
}

// SUB CLASS 1
class ProdukFisik extends Produk {
    protected double beratKg;

    public ProdukFisik(String nama, int harga, double beratKg) {
        super(nama, harga);
        this.beratKg = beratKg;
    }

    @Override // OVERRIDING
    public String tampilInfo() {
        return "[FISIK]   " + nama + "    | Harga: " + harga + " | Berat: " + beratKg + " kg";
    }
}

// SUB CLASS 2
class ProdukDigital extends Produk {
    protected String masaAktif;

    public ProdukDigital(String nama, int harga, String masaAktif) {
        super(nama, harga);
        this.masaAktif = masaAktif;
    }

    @Override //OVERRIDING
    public String tampilInfo() {
        return "[DIGITAL] " + nama + "  | Harga: " + harga + " | Masa Aktif: " + masaAktif;
    }
}

// CLASS KASIR (OVERLOADING)
class Kasir {

    // Overload 1: tanpa diskon
    public int hitungBayar(int harga, int qty) {
        return harga * qty;
    }

    // Overload 2: dengan diskon
    public int hitungBayar(int harga, int qty, double diskonPersen) { // yang di lihat nanti sesuai parameter, bila parameternya 2 maka masuk ke tanpa diskon
        int total = harga * qty;
        return (int) (total - (total * diskonPersen / 100));
    }
}

// PROGRAM UTAMA
public class DemoPolimorfisme {
    public static void main(String[] args) {
        System.out.println("=== DEMO POLIMORFISME ===");
        System.out.println("Memanggil tampilInfo() dari masing-masing objek:\n");

        Produk p1        = new Produk("Kaos Polos", 85000);
        ProdukFisik p2   = new ProdukFisik("Sepatu Lari", 350000, 0.8);
        ProdukDigital p3 = new ProdukDigital("Microsoft 365", 600000, "1 Tahun");

        System.out.println(p1.tampilInfo());
        System.out.println(p2.tampilInfo());
        System.out.println(p3.tampilInfo());

        System.out.println("\n=== DEMO OVERLOADING ===");
        System.out.println("Memanggil hitungBayar() dengan argumen berbeda: \n");

        Kasir kasir = new Kasir();

        int total1 = kasir.hitungBayar(50000, 2); // bila parameter nya hanya 2 maka masuk ke tanpa diskon
        int total2 = kasir.hitungBayar(50000, 2, 15); // bila parameter nya 3 maka masuk ke dengan diskon meskipun nama method nya sama

        System.out.println("hitungBayar(50000, 2)              = " + total1);
        System.out.println("hitungBayar(50000, 2, 15)          = " + total2);
        
    }
}