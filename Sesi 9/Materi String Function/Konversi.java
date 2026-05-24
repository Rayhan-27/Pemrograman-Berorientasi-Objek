public class Konversi {
    
    public static void main(String[] args) {
        int val_1 = 2;
        int val_2 = 10;

        String v1 = String.valueOf(val_1);
        String v2 = String.valueOf(val_2);

        System.out.println(v1 + v2); //Karna sudah String maka bila di tambah akan jadi 210 bukan 12
    }
}
