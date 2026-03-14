
public class DemoProduk { 
    public static void main(String[] args) {
        System.out.println("=== DEMO INHERITANCE - PRODUK ===\n");

        // 1. Produk FISIK (buku) - punya berat & ongkir
        ProdukFisik buku = new ProdukFisik("Buku Pemrograman Java", 85000, 50, 350); // objek baru untuk ProdukFisik yaitu buku dengan kunci "new"
        // dan karena ProdukFisik memiliki constructor, jadi langsung di isi nama, harga, stok, dan beratGram
        System.out.println("Produk Fisik (buku):");
        System.out.println("  " + buku.getInfo()); // mengambil info dari ProdukFisik buku
        System.out.println("  Ongkir terpisah: Rp " + (int) buku.hitungOngkir());

        // 2. Produk DIGITAL (e-book) - tidak ada ongkir, ada link download
        ProdukDigital ebook = new ProdukDigital("E-book Panduan OOP", 45000, 70, // objek baru untuk ProdukDigital yaitu ebook dengan kunci "new"
                "https://toko.com/download/ebook-oop", "PDF");
        // dan karena ProdukDigital memiliki constructor, jadi langsung di isi nama, harga, stok, linkDownload, dan formatFile
        System.out.println("\nProduk Digital (e-book):");
        System.out.println("  " + ebook.getInfo()); // mengambil info dari ProdukDigital ebook
        System.out.println("  Link unduh: " + ebook.getLinkDownload());

        // 3. Method warisan: kurangiStok() dari superclass dipakai oleh semua
        System.out.println("\nMethod warisan: kurangiStok()");
        buku.kurangiStok(2); // mengurangi stok untuk buku, beda dengan ebook
        ebook.kurangiStok(2);
        System.out.println("  Stok buku setelah beli 2: " + buku.getStok());
        System.out.println("  Stok ebook setelah beli 1: " + ebook.getStok());

        System.out.println("\n===");
    }
}
