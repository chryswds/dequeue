package org.example;

import java.util.Scanner;

import static org.example.Product.*;

public class Menu  {
    static void mainMenu(){
        int choice = 0;
        while(choice != 4) {
            try {
                System.out.println("\n╔════════════════════════════════════╗");
                System.out.println("║            TRAY CONTENTS           ║");
                System.out.println("╚════════════════════════════════════╝");
                showItems();
                System.out.println("════════════════════════════════════════\n");

                System.out.println("┌────────────────────────────────────┐");
                System.out.println("│             MAIN MENU              │");
                System.out.println("├────────────────────────────────────┤");
                System.out.println("│  1. Add Product                    │");
                System.out.println("│  2. Remove Product                 │");
                System.out.println("│  3. Search Product Category        │");
                System.out.println("│  4. Exit                           │");
                System.out.println("│  5. Load Dummy Data                │");
                System.out.println("└────────────────────────────────────┘");
                System.out.print("\n➤ Choose an option: (1-5): ");
                choice = scanner.nextInt();
                scanner.nextLine();
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
                        System.out.println("Exiting...");
                        break;
                }
            }catch (Exception e){
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│      ⚠   INVALID CHOICE    ⚠      │");
                System.out.println("│     Enter a number between 1-5    │");
                System.out.println("└───────────────────────────────────┘");
                scanner.nextLine();
            }
        }
    }
    static Type foodTypeMenu(){
        Scanner scanner = new Scanner(System.in);
        Type type = null;
        boolean validType = false;
        while (!validType) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║        SELECT FOOD TYPE            ║");
            System.out.println("╚════════════════════════════════════╝");
            for (Type t : Type.values()) {
                System.out.println("│  " + t.getDisplayName() + "                    │");
            }
            System.out.println("──────────────────────────────────────");
            System.out.print("\n➤ Enter your choice (1-5): ");
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
                        System.out.println("┌───────────────────────────────────┐");
                        System.out.println("│      ⚠   INVALID CHOICE    ⚠      │");
                        System.out.println("│     Enter a number between 1-5    │");
                        System.out.println("└───────────────────────────────────┘");

                }
            } catch (IllegalArgumentException e) {
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│      ⚠   INVALID CHOICE    ⚠      │");
                System.out.println("│     Enter a number between 1-5    │");
                System.out.println("└───────────────────────────────────┘");
            }
        }
        return type;
    }

    static void removeProdMenu(){
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    PRODUCT REMOVAL MENU                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Remove from FIRST tray                                     ║");
        System.out.println("╠────────────────────────────────────────────────────────────────╣");
        peekFirstItem();
        System.out.println("╠────────────────────────────────────────────────────────────────╣");
        System.out.println("║  2. Remove from LAST tray                                      ║");
        System.out.println("╠────────────────────────────────────────────────────────────────╣");
        peekLastItem();
        System.out.println("╠────────────────────────────────────────────────────────────────╣");
        System.out.println("║  3. Remove all trays                                           ║");
        System.out.println("╠────────────────────────────────────────────────────────────────╣");
        System.out.println("║  4. Cancel                                                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.print("\n➤ Select option (1-4): ");
    }

}


