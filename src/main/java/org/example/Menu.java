package org.example;

import java.util.Scanner;

import static org.example.Product.*;

public class Menu  {
    static void mainMenu(){
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
    static Type foodTypeMenu(){
        Scanner scanner = new Scanner(System.in);
        Type type = null;
        boolean validType = false;
        while (!validType) {
            System.out.println("Choose a type of food:  ");
            for (Type t : Type.values()) {
                System.out.println(t.getDisplayName());
            }
            String userInputType = scanner.nextLine();

            try {
                switch (userInputType) {
                    case "1":
                        type = Type.BURGER;
                        validType = true;
                        break;
                    case "2":
                        type = Type.PIZZA;
                        validType = true;
                        break;
                    case "3":
                        type = Type.FRIES;
                        validType = true;
                        break;
                    case "4":
                        type = Type.SANDWICH;
                        validType = true;
                        break;
                    case "5":
                        type = Type.HOTDOG;
                        validType = true;
                        break;
                    default:
                        System.out.println("Invalid food type, choose from 1 - 5");

                }
            } catch (IllegalArgumentException e) {
                System.out.println("invalid type, please try again");
            }
        }
        return type;
    }
}


