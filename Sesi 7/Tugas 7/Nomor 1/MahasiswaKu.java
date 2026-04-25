public class MahasiswaKu {

    public static void main(String[] args) {

        Mahasiswa<String, String, Integer> m = new Mahasiswa<>();

        m.setNim("20240040187");
        m.setName("Rayhan");
        m.setClas(24);

        System.out.println(m.getNim());
        System.out.println(m.getName());
        System.out.println(m.getClas());
    }
}