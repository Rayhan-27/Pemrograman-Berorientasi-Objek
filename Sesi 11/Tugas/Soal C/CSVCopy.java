import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        String sourceFile = "D:students.csv";
        String targetFile = "D:students_copy.csv";
        String line;
        int jumlahBaris = 0;

        try (
            BufferedReader br = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))
        ) {
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                jumlahBaris++;
            }
            System.out.println("Berhasil menyalin " + jumlahBaris + " baris dari " + sourceFile + " ke " + targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Menyalin seluruh isi file CSV (termasuk header) ke file CSV baru