import java.util.ArrayDeque;
import java.util.ArrayList;

public class CollectionDemo {

    public static void main(String[] args) {

        // =========================
        // ARRAYLIST
        // =========================
        System.out.println("=== ARRAYLIST ===");

        ArrayList<String> daftarMahasiswa = new ArrayList<>();

        daftarMahasiswa.add("Rayhan");
        daftarMahasiswa.add("Budi");
        daftarMahasiswa.add("Siti");

        System.out.println("Isi ArrayList:");
        for (String nama : daftarMahasiswa) {
            System.out.println(nama);
        }

        System.out.println();

        // =========================
        // ARRAYDEQUE
        // =========================
        System.out.println("=== ARRAYDEQUE ===");

        ArrayDeque<String> antrian = new ArrayDeque<>();

        antrian.add("Antrian 1");
        antrian.add("Antrian 2");
        antrian.add("Antrian 3");

        System.out.println("Isi Antrian:");
        while (!antrian.isEmpty()) {
            System.out.println(antrian.poll());
        }
    }
}