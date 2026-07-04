package model;

public class Treasurer extends Person {
    private String jabatan;

    public Treasurer(String nama, String kelas) {
        super(nama, kelas);
        this.jabatan = "Bendahara Kelas";
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @Override
    public void tampilInfo() {
        System.out.println("Nama: " + getNama() +
                " | Kelas: " + getKelas() +
                " | Jabatan: " + jabatan);
    }
}
