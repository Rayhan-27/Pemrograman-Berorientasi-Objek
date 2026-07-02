import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "D:students.csv";
        String[] data = {
            "4,David,23",
            "5,Eva,22",
            "6,Ferdi,21"
        };
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// WARNING !!!!
// writer ini mengubah atau mengganti semua data yang ada di dalam file CSV bukan menambahkan yang sudah ada