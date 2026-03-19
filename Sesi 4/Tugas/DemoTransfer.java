// === OVERLOADING ===

// SUPER CLASS
class Bank {

    // Method 1
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("| Transfer: Rp" + jumlah + "    | ke rekening: " + rekeningTujuan + "  |");
    }

    // Method 2
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("| Transfer: Rp" + jumlah + "    | ke rekening: " + rekeningTujuan + "  | bank tujuan: " + bankTujuan + "  |");
    }

    // Method 3
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println("| Transfer: Rp" + jumlah + "   | ke rekening: " + rekeningTujuan + " | bank tujuan: " + bankTujuan + "      | Berita: " + berita + "  |");
    }

    // Method suku bunga
    public void sukuBunga() {
        System.out.println("=== Suku bunga standar adalah 3% ===");
    }
}

// === OVERRIDING ===
// Subclass BNI
class BankBNI extends Bank {

    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah 4%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BNI";
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
    }
}

// Subclass BCA
class BankBCA extends Bank {

    @Override
    public void sukuBunga() {
        System.out.println("Suku bunga dari BCA adalah 4,5%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan){
        bankTujuan = "BCA";
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
    }
}


// ==== CLASS MAIN / DEMO TRANSFER ====
public class DemoTransfer {
    public static void main (String [] args) {
        Bank bank = new Bank();

        // Method Overloading
        bank.transferUang(500000, "123456789");
        bank.transferUang(750000, "912345678", "Mandiri");
        bank.transferUang(1000000, "1122334455", "BRI", "Bayar Kuliah");

        bank.sukuBunga();

        System.out.println(); //pembatas

        // Method Overriding
        BankBNI bni = new BankBNI();
        bni.transferUang(200000, "123123123", "BNI");
        bni.sukuBunga();

        System.out.println();

        BankBCA bca = new BankBCA();
        bca.transferUang(300000, "44444444", "BCA");
        bca.sukuBunga();
    }
}