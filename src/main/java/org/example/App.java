package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class App {
    public static void displayMenu() {
        System.out.print("""
                "Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                "
                """);
    }

    public static String[] dataInput(Scanner data) {
        String[] resultData = new String[24];
        for (int i = 0; i < resultData.length; i++) {
            String numStr = data.nextLine();
            resultData[i] = numStr;
        }
        return resultData;
    }

    public static String[] priceStats(String[] data) {
        System.out.print(Arrays.toString(data));
        return data;

    }

    public static void main(String[] args) {
        String[] data = new String[24];
        Scanner scanner = new Scanner(System.in);
        displayMenu();
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("e")) {
                break;
            } else if (input.equals("1")) {
                data = dataInput(scanner);

            } else if (input.equals("2")) {
                String[] statsData = priceStats(data);
            } else {
                displayMenu();
            }


        }

    }

}

