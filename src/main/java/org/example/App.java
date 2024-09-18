package org.example;

import java.util.*;


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

    public static Price[] dataInput(Scanner data) {
        Price[] priceData = new Price[24];
        String strNumbers = data.nextLine();

        for (int i = 0; i < priceData.length; i++) {
            int value = Integer.parseInt(strNumbers);
            priceData[i] = new Price(i, value);
        }
        return priceData;
    }


    public record Price(int index, int value){
        public String intervalRepresentation(){
            if (this.index() > 24){
                throw new IllegalArgumentException("Index provided exceeds 24 hours");
            }
            int startTime = this.index();
            int endTime = (1 + startTime) == 24 ? 24 : (1 + startTime) % 24 ;
            return String.format("%02d - %02d", startTime, endTime);
        }
    }

    public static double[] priceStats(Price[] prices){
        double[] statsOnPricing = new double[3];
        for (Price price : prices){
            System.out.printf("Interval %s ",price.intervalRepresentation());
        }
        return statsOnPricing;

    }

    public static void main(String[] args) {
        Price[] data = new Price[24];
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        displayMenu();
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("e")) {
                break;
            } else if (input.equals("1")) {
                data = dataInput(scanner);

            } else if (input.equals("2")) {
                double [] statsData = priceStats(data);
                System.out.printf("%.2f", statsData[0]);
            } else {
                displayMenu();
            }


        }

    }

}

