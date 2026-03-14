public class ProdukDigital extends Produk { // Kelas turunan dari Produk dengan kata kunci "extends"
    private final String linkDownload;
    private final String formatFile; 
    
    ProdukDigital(String nama, double harga, int stok, String linkDownload, String formatFile) {
        super(nama, harga, stok); // mengambil constructor dari produk / super class
        this.linkDownload = linkDownload;
        this.formatFile = formatFile;
    }

    @Override // tanda untuk menandakan bahwa ini adalah method yang sama dengan super class / Produk namun di override atau di timpa
    public String getInfo() { // Override method getInfo() yang sudah ada di Produk / Super class
        return super.getInfo() + " | Format: " + formatFile + " | Ongkir: Gratis";
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public String getFormatFile() { 
        return formatFile; 
    }
}
