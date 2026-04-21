public class Main {
    public static void main(String[] args) {
        BankAccount Rayhan = new BankAccount (1000000, "20240040187", "Rayhan Sandika");
        // Rayhan adalah objek baru dari kelas BankAccount
        Rayhan.deposit(5000000);
        Rayhan.withdraw(500000);
        // Pengurangan dan Penambahan menggunakan Setter karena saldo tidak dapat diakses langsung (private)
        System.out.println(Rayhan); // Bila tidak menggunakan String toString, output akan mencetak alamat objek atau memori yang tersimpan
    }
}
