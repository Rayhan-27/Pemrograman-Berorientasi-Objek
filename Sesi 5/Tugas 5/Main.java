import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // INPUT STUDENT
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMhs = input.nextLine();

        System.out.print("Masukkan alamat mahasiswa: ");
        String alamatMhs = input.nextLine();

        Student mhs = new Student(namaMhs, alamatMhs);

        System.out.print("Jumlah mata kuliah: ");
        int jumlahMK = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahMK; i++) {
            System.out.print("Nama MK: ");
            String mk = input.nextLine();

            System.out.print("Nilai: ");
            int nilai = input.nextInt();
            input.nextLine();

            mhs.addCourseGrade(mk, nilai);
        }

        // OUTPUT STUDENT
        System.out.println("\n=== DATA MAHASISWA ===");
        System.out.println(mhs);
        mhs.printGrades();
        System.out.println("Rata-rata: " + mhs.getAverageGrade());

        // INPUT TEACHER
        System.out.print("\nMasukkan nama dosen: ");
        String namaDosen = input.nextLine();

        System.out.print("Masukkan alamat dosen: ");
        String alamatDosen = input.nextLine();

        Teacher dosen = new Teacher(namaDosen, alamatDosen);

        System.out.print("Jumlah MK yang diajar: ");
        int jumlahAjar = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahAjar; i++) {
            System.out.print("Nama MK: ");
            String mk = input.nextLine();
            dosen.addCourse(mk);
        }

        // OUTPUT TEACHER
        System.out.println("\n=== DATA DOSEN ===");
        System.out.println(dosen);

        input.close();
    }
}