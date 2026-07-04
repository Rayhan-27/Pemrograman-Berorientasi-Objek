package util;

import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String bacaString(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    public static int bacaInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat. Coba lagi.");
            }
        }
    }

    public static double bacaDouble(String label) {
        while (true) {
            try {
                System.out.print(label);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Coba lagi.");
            }
        }
    }
}
