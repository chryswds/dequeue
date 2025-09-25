package org.example;
import java.util.Scanner;

import static org.example.Product.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int choice = 0;

        while(choice != 4) {
            try {
                System.out.println("-----TRAY----");
                showItems();
                System.out.println("--------------");
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Search Product category");
                System.out.println("4. Exit");
                System.out.println("5. Load Dummy Data");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addProd();
                        break;
                    case 2:
                        removeProd();
                        break;
                    case 3:
                        searchFoodType();
                        break;
                    case 5:
                        loadDummyData();
                        break;
                    default:
                        System.out.println("Choose between 1 and 5");

                }

            }catch (Exception e){
                System.out.println("Invalid choice - insert a number");
                scanner.nextLine();
            }
        }



    }
}