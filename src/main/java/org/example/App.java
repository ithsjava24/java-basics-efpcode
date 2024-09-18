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
        //int num = data.nextInt();

        for (int i = 0; i < priceData.length; i++) {
            int num = data.nextInt();
            //int value = Integer.parseInt(num);
            priceData[i] = new Price(i, num);
            data.nextLine();
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
            return String.format("%02d-%02d", startTime, endTime);
        }
    }



    public static void main(String[] args) {
        Price[] data = new Price[24];
        Scanner scanner = new Scanner(System.in);
        Locale sweNumberFormat = new Locale("sv", "SE");
        Locale.setDefault(sweNumberFormat);
        //scanner.useDelimiter("\\n");
        displayMenu();
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("e")) {
                break;
            } else if (input.equals("1")) {
                data = dataInput(scanner);

            } else if (input.equals("2")) {
                double mean = PricesStats.priceAvg(data);
                Price [] minAndMaxPrices = PricesStats.pricesMinAndMax(data);
                System.out.printf("Lägsta pris: %s, %d öre/kWh\n", minAndMaxPrices[0].intervalRepresentation(), minAndMaxPrices[0].value());
                System.out.printf("Högsta pris: %s, %d öre/kWh\n", minAndMaxPrices[1].intervalRepresentation(), minAndMaxPrices[1].value());
                System.out.printf("Medelpris: %.2f öre/kWh\n", mean);
                System.out.print("\n");

            } else {
                displayMenu();
            }


        }

    }

}

