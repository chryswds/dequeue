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
                System.out.println("│  3. Search Product                 │");
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
                        removeProdMenu();
                        break;
                    case 3:
                        searchMenu();
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

    static void searchMenu() {
        int choice = 0;
        while (choice != 6) {
            try {
                System.out.println("\n╔════════════════════════════════════╗");
                System.out.println("║          SEARCH PRODUCT BY         ║");
                System.out.println("╠═══════════════════════════════════─╣");
                System.out.println("║  1. Name                           ║");
                System.out.println("║  2. Type                           ║");
                System.out.println("║  3. Weight                         ║");
                System.out.println("║  4. Added Date                     ║");
                System.out.println("║  5. Best Before Date               ║");
                System.out.println("╠────────────────────────────────────╣");
                System.out.println("║  6. Cancel                         ║");
                System.out.println("╚════════════════════════════════════╝");
                System.out.print("\n➤ Select option (1-6): ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        searchByName();
                        break;
                    case 2:
                        searchFoodType();
                        break;
                    case 3:
                        searchByWeight();
                        break;
                    case 4:
                        searchByAddedDate();
                        break;
                    case 5:
                        searchByBestBeforeDate();
                        break;
                    case 6:
                        System.out.println("Search cancelled");
                        break;
                    default:
                        System.out.println("Choose between 1 and 4");
                }
            }
            catch (Exception e){
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│      ⚠   INVALID CHOICE    ⚠      │");
                System.out.println("│     Enter a number between 1-4    │");
                System.out.println("└───────────────────────────────────┘");
                scanner.nextLine();
            }
        }
    }

    static void removeProdMenu() {
        int removalType = 0;
        while (removalType != 5) {
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
            System.out.println("║  4. Undo the last action                                       ║");
            System.out.println("╠────────────────────────────────────────────────────────────────╣");
            System.out.println("║  5. Cancel                                                     ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            System.out.print("\n➤ Select option (1-5): ");
                removalType = scanner.nextInt();
                switch (removalType) {
                    case 1:
                        removeFirstTray();
                        break;
                    case 2:
                        removeLastTray();
                        break;
                    case 3:
                        removeAllTrays();
                        break;
                    case 4:
                        undoLastAction();
                        break;
                    case 5:
                        System.out.println("Exiting removal menu...");
                        break;
                    default:
                        System.out.print("\n➤ Enter your choice (1-5): ");
                }
            }
        }

    }



