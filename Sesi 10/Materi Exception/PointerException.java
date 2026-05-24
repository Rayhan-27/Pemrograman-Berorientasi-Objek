

public class PointerException {
    
    public static void main(String[] args) {
        
        try {
            String nama = null;
            System.out.println(nama.length());
        } 
        catch (NullPointerException e) {
            System.out.println("Variabel nama harus di inisialisasi");
        }
        finally{
            System.out.println("Null Pointer");
        }
    }
}
