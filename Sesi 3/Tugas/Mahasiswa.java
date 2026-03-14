public class Mahasiswa {

    protected String nim; // pakai protected agar subclass bisa mengakses
    protected String nama;
    protected int nilai;

    Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim; // this.nim itu milik constructor dan nim adalah parameternya
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public int getNilai() {
        return nilai;
    }

    public String getInfo() {
        return "NIM : " + nim +
               "\nNama : " + nama +
               "\nNilai : " + nilai;
    }
}