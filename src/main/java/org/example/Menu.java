package org.example;

import java.util.Date;
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

    static void addProd(){
        if (!checkLimits()) {
            System.out.println("┌────────────────────────────────────┐");
            System.out.println("│             ⚠   FULL   ⚠           │");
            System.out.println("│ You reached the maximum of 8 trays │");
            System.out.println("└────────────────────────────────────┘");
            return;
        }
        boolean productAdded = false;
        while (!productAdded) {
            try {
                // TYPE - Get and validate food type
                Type type = foodTypeMenu();
                if (type == null) {
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│      ⚠   INVALID TYPE    ⚠        │");
                    System.out.println("│     Please select a valid type    │");
                    System.out.println("└───────────────────────────────────┘");
                    continue; // Stay in loop
                }

                // NAME - Get and validate food name
                String userInputName = null;
                boolean validName = false;

                while (!validName) {
                    System.out.println("➤ Enter the food name: ");
                    userInputName = scanner.nextLine();

                    if (userInputName == null || userInputName.trim().isEmpty()) {
                        System.out.println("┌───────────────────────────────────┐");
                        System.out.println("│       ⚠   INVALID NAME   ⚠        │");
                        System.out.println("│       Name cannot be empty        │");
                        System.out.println("└───────────────────────────────────┘");
                        continue;
                    }

                    userInputName = userInputName.trim();
                    validName = true;
                }

                // WEIGHT - Get and validate weight
                double weight = productWeight();
                if (weight <= 0) {
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│     ⚠   WEIGHT INPUT FAILED  ⚠    │");
                    System.out.println("│      Restarting product entry     │");
                    System.out.println("└───────────────────────────────────┘");
                    continue;
                }

                // DATE - Get and validate best before date
                Date date = validate2weeks();
                if (date == null) {
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│      ⚠   DATE INPUT FAILED   ⚠    │");
                    System.out.println("│      Restarting product entry     │");
                    System.out.println("└───────────────────────────────────┘");
                    continue;
                }

                // CREATE PRODUCT - All validations passed
                Product foodItem = new Product(type, userInputName, weight, date);
                productQueue.addFirst(foodItem);

                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│     ✓ Successfully added item ✓   │");
                System.out.println("│ Name: " + String.format("%-28s", userInputName) + "│");
                System.out.println("│ Type: " + String.format("%-28s", type) + "│");
                System.out.println("│ Weight: " + String.format("%-26s", weight + "g") + "│");
                System.out.println("└───────────────────────────────────┘");

                productAdded = true;

            } catch (IllegalArgumentException e) {
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│       ⚠   ERROR OCCURRED   ⚠      │");
                System.out.println("│ " + String.format("%-35s", e.getMessage()) + "│");
                System.out.println("│      Restarting product entry     │");
                System.out.println("└───────────────────────────────────┘");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│    ⚠   UNEXPECTED ERROR   ⚠       │");
                System.out.println("│      Restarting product entry     │");
                System.out.println("└───────────────────────────────────┘");
                scanner.nextLine();
            }
        }
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



