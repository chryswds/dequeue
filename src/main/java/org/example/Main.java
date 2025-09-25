package org.example;
import java.util.Scanner;

import static org.example.Product.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String choice = "";

        while(!choice.equals("4")) {
            try {
                System.out.println("-----TRAY----");
                showItems();
                System.out.println("--------------");
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Search Product category");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("1")) {
                    addProd();
                } else if (choice.equals("2")) {
                    String removalType = "";
                    while (!removalType.equals("3")) {
                        try {
                            System.out.println("\n--- Removal Type  - Select an option---");
                            System.out.print("1. Remove product from the first tray");
                            peekFirstItem();
                            System.out.print("2. Remove product from the last tray");
                            peekLastItem();
                            System.out.println("3. Cancel");
                            removalType = scanner.nextLine().trim().toLowerCase();

                            if (removalType.equals("1")) {
                                removeFirstTray();
                            }else if (removalType.equals("2")) {
                                removeLastTray();

                            }
                        }catch (Exception e){
                            System.out.println("Invalid input");
                        }
                    }
                }else if (choice.equals("3")) {
                    searchFoodType();
                }

            }catch (Exception e){
                System.out.println("Not a valid choice");
            }
        }



    }
}