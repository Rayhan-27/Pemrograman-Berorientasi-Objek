import java.util.Scanner; // agar program bisa menerima input dari keyboard

public class DemoNilai {

    public static void main(String[] args) { // program yang pertama di jalankan pasti main 

        try (Scanner input = new Scanner(System.in)) { // Membuat objek Scanner untuk menerima input
        // menggunakan try agar scanner ditutup otomatis tidak perlu input.close()
            int jumlah = 4; // program akan memproses 4 mahasiswa

            NilaiMahasiswa[] data = new NilaiMahasiswa[jumlah]; // array berisi object mahasiswa maksimalnya 4

            // Berikut adalah data awal untuk menghitung statistik nilai
            int lulus = 0;
            int tidakLulus = 0;
            String namaLulus = "";
            String namaTidakLulus = ""; 
            String namaNilaiA = "";
            String namaNilaiB = "";
            String namaNilaiC = "";
            String namaNilaiD = "";
            String namaNilaiE = "";

            int jumlahA = 0;
            int jumlahB = 0;
            int jumlahC = 0; // menjadi ada peringatan karena data tidak terpakai
            int jumlahD = 0;
            int jumlahE = 0;

            int totalNilai = 0;

            for (int i = 0; i < jumlah; i++) { // program akan mengulang proses input sebanyak 4 kali

                System.out.println("Data Mahasiswa ke-" + (i + 1)); // menampilkan data mahasiswa ke-i

                System.out.print("NIM : ");
                String nim = input.nextLine(); // User diminta memasukkan NIM

                System.out.print("Nama : ");
                String nama = input.nextLine(); // User diminta memasukkan Nama

                System.out.print("Nilai : ");
                int nilai = input.nextInt(); // User diminta memasukkan Nilai
                input.nextLine(); 

                data[i] = new NilaiMahasiswa(nim, nama, nilai); // membuat object mahasiswa baru, lalu disimpan ke dalam array

                totalNilai += nilai; // menjumlahkan semua nilai

                if (data[i].isLulus()) { // jika indikatornya lulus maka total mahasiswa yang lulus bertambah
                    lulus++;
                    namaLulus += data[i].getNama() + " "; // Menyimpan nama mahasiswa yang lulus
                } else { // jika indikatornya tidak lulus maka total mahasiswa yang tidak lulus bertambah
                    tidakLulus++;
                    namaTidakLulus += data[i].getNama() + " "; // Menyimpan nama mahasiswa yang tidak lulus
                }

                String grade = data[i].hitungGrade(); // Menghitung grade mahasiswa

                if (grade.equals("A")) {
                    jumlahA++; // Jika grade A, jumlahA bertambah
                    namaNilaiA += data[i].getNama() + " "; // Menyimpan nama mahasiswa dengan nilai A
                }

                if (grade.equals("B")) {
                    jumlahB++; // Jika grade B, jumlahB bertambah
                    namaNilaiB += data[i].getNama() + " "; // Menyimpan nama mahasiswa dengan nilai B
                }

                if (grade.equals("C")) {
                    jumlahC++; // Jika grade C, jumlahC bertambah
                    namaNilaiC += data[i].getNama() + " "; // Menyimpan nama mahasiswa dengan nilai C
                }

                if (grade.equals("D")) {
                    jumlahD++; // Jika grade D, jumlahD bertambah
                    namaNilaiD += data[i].getNama() + " "; // Menyimpan nama mahasiswa dengan nilai D
                }

                if (grade.equals("E")) {
                    jumlahE++; // Jika grade E, jumlahE bertambah
                    namaNilaiE += data[i].getNama() + " "; // Menyimpan nama mahasiswa dengan nilai E
                }

                System.out.println(data[i].getInfo());
                System.out.println("===============================");
            }
            
            double rata = (double) totalNilai / jumlah; // menghitung rata rata

            System.out.println("Jumlah Mahasiswa : " + jumlah);
            System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus + ", yaitu: " + namaLulus);
            System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulus + ", yaitu " + namaTidakLulus);
            System.out.println("Jumlah Mahasiswa dengan Nilai A : " + jumlahA + ", yaitu " + namaNilaiA);
            System.out.println("Jumlah Mahasiswa dengan Nilai B : " + jumlahB + ", yaitu " + namaNilaiB);
            System.out.println("Jumlah Mahasiswa dengan Nilai D : " + jumlahD + ", yaitu " + namaNilaiD);
            System.out.println("Rata-rata nilai mahasiswa adalah : " + rata);
        }

        

        
    }
}