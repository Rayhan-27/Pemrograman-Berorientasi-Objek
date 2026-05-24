import java.util.Scanner;

public class FormatException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hasil = 0;
        try {
            System.out.println("Isikan Bilangan 1: ");
            String bil1 = sc.next();
            System.out.println("Isikan Bilangan 2: ");
            String bil2 = sc.next();

            hasil = Integer.parseInt(bil1) + Integer.parseInt(bil2);
        } 
        catch (NumberFormatException nf) {
            System.out.println("Proses format salah");
        }
        finally{
            System.out.println(hasil);
        }
    }
}
