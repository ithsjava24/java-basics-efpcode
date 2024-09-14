package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void displayMenu() {
        System.out.printf("""
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

    public ArrayList<String> dataInput(Scanner data){
        String values = data.nextLine();
        ArrayList<String> data = values.split("\n");
        return data;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayMenu();
        while(true){
           String input = scanner.nextLine().toLowerCase();
           if(input.equals("e")){
               break;
           }
           else if(input.equals("1")){
              ArrayList<String> data = dataInput(scanner);
               System.out.printf("%s",data);
           }
           displayMenu();


        }

        }

    }

