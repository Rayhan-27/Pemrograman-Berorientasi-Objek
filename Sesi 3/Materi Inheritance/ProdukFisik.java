public class ProdukFisik extends Produk { // Kelas turunan dari Produk dengan kata kunci "extends"
    private final double beratGram;  

    ProdukFisik(String nama, double harga, int stok, double beratGram) {
        super(nama, harga, stok);  // Mengambil nilai constructor dari Produk dengan kunci "super"
        this.beratGram = beratGram; // ini constructor milik ProdukFisik bukan mengambil dari Produk / super class (induk)
    }

    @Override // tanda untuk menandakan bahwa ini adalah method yang sama dengan super class / Produk namun di override atau di timpa
    public String getInfo() {
        return super.getInfo() + " | Berat: " + (int) beratGram + " g | Ongkir: Rp " + (int) hitungOngkir();
    }

    public double hitungOngkir() {
        // Rp 5.000 per 100 gram, minimal Rp 10.000
        double ongkir = (beratGram / 100) * 5000;
        return ongkir < 10000 ? 10000 : ongkir;
    }

    public double getBeratGram() { 
        return beratGram; 
    }
}
