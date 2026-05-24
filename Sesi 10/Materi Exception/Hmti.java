public class Hmti {
    public static void main(String[] args) {
        MhsInformatika mhs = new MhsInformatika("A", null);

        try{
            System.out.println(mhs.getNama());
            System.out.println(mhs.getNim());
            System.out.println(mhs.getNim().length());
        }
        catch(NullPointerException npe){
            System.out.println("NIM belum ada isinya");
        }
        finally{
            System.out.println("Program Selesai");
        }

    }
}
