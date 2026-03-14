public class NilaiMahasiswa extends Mahasiswa {

    NilaiMahasiswa(String nim, String nama, int nilai) {
        super(nim, nama, nilai);
    }

    public String hitungGrade() {

        if (nilai > 100 || nilai < 0) { // nilai lebih dari 100 atau kurang dari 0
            return "Input nilai anda salah";
        } else if (nilai >= 80) { // nilai di antara 80 dan 100
            return "A";
        } else if (nilai >= 70) { // nilai di antara 70 dan 79
            return "B";
        } else if (nilai >= 60) { // nilai di antara 60 dan 69
            return "C";
        } else if (nilai >= 50) { // nilai di antara 50 dan 59
            return "D";
        } else { // nilai di bawah 50
            return "E";
        }
    }

    public boolean isLulus() { // method untuk mengecek kelulusan, jika nilai >= 60 maka lulus
        return nilai >= 60;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nGrade : " + hitungGrade(); // override method getInfo dan menambahkan informasi grade
    }
}