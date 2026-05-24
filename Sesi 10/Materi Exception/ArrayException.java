public class ArrayException {
    
    public static void main(String[] args) {
        try {
            String data [] = {"1", "2", "3"};
            int i0 = Integer.parseInt(data[0]);
            int i1 = Integer.parseInt(data[1]);
            int hasil = i0 + i1;
            System.out.println(data[3] + " = " + hasil); //ini akan menyebabkan error karna indeks 3 melebihi batas
        } 
        catch (ArrayIndexOutOfBoundsException arr) {
            System.out.println("Indeks melebihi batas");
        }
    }
}
