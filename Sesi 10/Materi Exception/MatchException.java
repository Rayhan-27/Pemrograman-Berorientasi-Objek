import java.util.InputMismatchException;
import java.util.Scanner;
public class MatchException {

    public static void main(String[] args) {

        

        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Isikan Bilangan 1 : ");
            int num1 = sc.nextInt();
            System.out.println("Isikan Bilangan 2 : ");
            int num2 = sc.nextInt();
            int result = num1 / num2;
            System.out.println("Hasil : " + result);
        }
        catch(ArithmeticException ae){
            ae.printStackTrace();
            System.out.println("Terjadi kesalahan: " + ae.getMessage());
        }
        catch(InputMismatchException ime){
            ime.printStackTrace();
            System.out.println("Input yang dimasukkan tidak valid");
        }
        finally{
            System.out.println("Finally akan selalu dikerjakan");
        }
    }
}