import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderModifikasi {
    public static void main(String[] args) {
        String csvFile = "D:students.csv";
        String line;
        String csvSplitBy = ",";
        int indeks = 0;
        int jumlahData = 0; // jumlah baris data (tidak termasuk header)

        System.out.println("NIM, NAMA, UMUR, PRODI");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                indeks++;
                if (indeks > 1) {
                    String[] student = line.split(csvSplitBy);
                    System.out.println(student[0] + ", " + student[1] + ", " + student[2] + ", " + student[3]);
                    jumlahData++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nTotal baris (termasuk header): " + indeks);
        System.out.println("Total data mahasiswa: " + jumlahData);
    }
}

// Melihat isi file CSV dan menghitung jumlah barisnya