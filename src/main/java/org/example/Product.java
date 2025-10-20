package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import static org.example.Menu.*;

public class Product {
    private Type type;
    private String name;
    private double weight;
    private Date bestBefore;
    private Date date;

    public Product(Type type, String name, double weight, Date bestBefore) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.bestBefore = bestBefore;
        this.date = new Date();
    }

    public Product(){

    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public Date getDate() {
        return date;
    }

    public static Deque<Product> getProductQueue() {
        return productQueue;
    }

    public static void setProductQueue(Deque<Product> productQueue) {
        Product.productQueue = productQueue;
    }

    static Deque<Product> productQueue = new Deque<>();
    static Scanner scanner  = new Scanner(System.in);
    static Stack<Action> undoStack = new Stack<>();


    static boolean checkLimits(){
        int maxSize = 8;
        if (productQueue.size() == maxSize){
            return false;
        }
        return true;
    }

    static void addProd(){
        if (checkLimits()) {
            // TYPE
            //Prints ENUM and let the user choose by typing
            Type type = foodTypeMenu();
            // NAME
            // User can choose name of the food
            System.out.println("➤ Enter the food name: ");
            String userInputName = scanner.nextLine();
            // WEIGHT
            double weight = productWeight();
            // DATE
            Date date = validate2weeks();
            // CREATE PRODUCT
            Product foodItem = new Product(type, userInputName, weight, date);
            productQueue.addFirst(foodItem);
            System.out.println("Successfully added food item");
        } else {
            System.out.println("┌────────────────────────────────────┐");
            System.out.println("│             ⚠   FULL   ⚠           │");
            System.out.println("│ You reached the maximum of 8 trays │");
            System.out.println("└────────────────────────────────────┘");
        }

    }

    static Date validate2weeks(){
        Scanner scanner = new Scanner(System.in);
        Date date = null;
        boolean validDate = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        while (!validDate) {
            System.out.print("➤ Enter best before date (dd/MM/yyyy) - (Max 2 weeks from today) : ");
            String inputDate = scanner.nextLine();
            try {
                date = dateFormat.parse(inputDate);
                Date today = new Date();

                Calendar currentDateAfter2Weeks = Calendar.getInstance();
                currentDateAfter2Weeks.add(Calendar.DAY_OF_WEEK, 14);
                Date twoWeeksLater = currentDateAfter2Weeks.getTime();

                if (date.after(twoWeeksLater)) {
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│       ⚠   INVALID DATE   ⚠        │");
                    System.out.println("│      Max 2 weeks from today       │");
                    System.out.println("└───────────────────────────────────┘");
                } else if (date.before(today)) {
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│       ⚠   INVALID DATE   ⚠        │");
                    System.out.println("│   The date can't be in the past   │");
                    System.out.println("└───────────────────────────────────┘");
                } else {

                    validDate = true;
                    return date;
                }
            } catch (ParseException e) {
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│       ⚠   INVALID DATE   ⚠        │");
                System.out.println("│     Enter a date (dd/MM/yyyy)     │");
                System.out.println("└───────────────────────────────────┘");
            }
        }
        return null;
    }

    static double productWeight(){
        double weight = -1;
        boolean validWeight = false;
        while (!validWeight) {
            // user specifies how much the food weights
            System.out.println("➤ Enter the weight? (grams)");
            try {
                weight = scanner.nextDouble();
                if (weight > 0) {
                    validWeight = true;
                    return weight;
                } else {
                    System.out.println("┌───────────────────────────────────┐");
                    System.out.println("│      ⚠   INVALID WEIGHT   ⚠       │");
                    System.out.println("│     Choose a positive number      │");
                    System.out.println("└───────────────────────────────────┘");
                }
            } catch (InputMismatchException e) {
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│      ⚠   INVALID WEIGHT   ⚠       │");
                System.out.println("│       Choose a valid number       │");
                System.out.println("└───────────────────────────────────┘");
                scanner.nextLine();
            }
        }
        return weight;
    }

    static void peekFirstItem(){
        if(productQueue.isEmpty()) {
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│   ⚠   All trays are empty    ⚠    │");
            System.out.println("└───────────────────────────────────┘");
        } else {
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                      FIRST TRAY ITEM                           ║");
            System.out.println("╠════════════════════════════════════════════════════════════════╣");
            System.out.println( productQueue.peekFirst());
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
        }
    }

    static void peekLastItem(){
        if(productQueue.isEmpty()) {
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│   ⚠   All trays are empty    ⚠    │");
            System.out.println("└───────────────────────────────────┘");
        } else {
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                       LAST TRAY ITEM                           ║");
            System.out.println("╠════════════════════════════════════════════════════════════════╣");
            System.out.println( productQueue.peekLast());
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
        }
    }

    static void removeFirstTray(){
        try {
            Product removed = productQueue.peekFirst();
            Action action = new Action(ActionType.REMOVE_FIRST);
            action.addProduct(removed);
            undoStack.push(action);

            productQueue.removeFirst();
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│   ✓ First tray has been removed   │");
            System.out.println("└───────────────────────────────────┘");
        }catch (Exception e){
            System.out.println("┌──────────────────────────────────────┐");
            System.out.println("│ ⚠ You can't remove from empty tray ⚠ │");
            System.out.println("└──────────────────────────────────────┘");
        }
    }

    static void removeLastTray(){
        try {
            Product removed = productQueue.peekLast();
            Action action = new Action(ActionType.REMOVE_LAST);
            action.addProduct(removed);
            undoStack.push(action);

            productQueue.removeLast();
            System.out.println("┌───────────────────────────────────┐");;
            System.out.println("│   ✓ Last tray has been removed    │");
            System.out.println("└───────────────────────────────────┘");
        } catch (Exception e){
            System.out.println("┌──────────────────────────────────────┐");
            System.out.println("│ ⚠ You can't remove from empty tray ⚠ │");
            System.out.println("└──────────────────────────────────────┘");
        }
    }

    static void removeAllTrays(){
        try {
            if (productQueue.isEmpty()) {
                System.out.println("┌───────────────────────────────┐");
                System.out.println("│  All trays are already empty  │");
                System.out.println("└───────────────────────────────┘");
                return;
            }

            Action action = new Action(ActionType.REMOVE_ALL);

            // Store all products in reverse order (from first to last)
            Deque<Product> tempDeque = new Deque<>();
            for (Product p : productQueue) {
                tempDeque.addFirst(p);
            }

            // Add them to the action in correct order
            for (Product p : tempDeque) {
                action.addProduct(p);
            }

            undoStack.push(action);

            while (!productQueue.isEmpty()){
                productQueue.removeFirst();
            }
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│   ✓ All trays have been removed   │");
            System.out.println("└───────────────────────────────────┘");
        } catch (Exception e){
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│  ⚠ All trays have been removed ⚠  │");
            System.out.println("└───────────────────────────────────┘");
        }

    }

    static void undoLastAction(){
        if (undoStack.isEmpty()) {
            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│     ⚠   NO ACTION TO UNDO   ⚠    │");
            System.out.println("└──────────────────────────────────┘");
            return;
        }

        Action lastAction = undoStack.pop();
        switch (lastAction.getType()) {
            case REMOVE_FIRST:
                Product product = lastAction.getRemovedProducts().get(0);
                productQueue.addFirst(product);
                System.out.println("┌──────────────────────────────────┐");
                System.out.println("│     ✓ Last removal undone ✓      │");
                System.out.println("└──────────────────────────────────┘");
                break;

            case REMOVE_LAST:
                Product lastProduct = lastAction.getRemovedProducts().get(0);
                Deque<Product> tempDeque = new Deque<>();
                while (!productQueue.isEmpty()) {
                    tempDeque.addFirst(productQueue.peekFirst());
                    productQueue.removeFirst();
                }
                productQueue.addFirst(lastProduct);
                for (Product p : tempDeque) {
                    productQueue.addFirst(p);
                }

                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│     ✓ Last removal undone ✓       │");
                System.out.println("└───────────────────────────────────┘");
                break;

            case REMOVE_ALL:
                for (Product p : lastAction.getRemovedProducts()) {
                    productQueue.addFirst(p);
                }
                System.out.println("┌───────────────────────────────────┐");
                System.out.println("│     ✓ Last removal undone ✓       │");
                System.out.println("└───────────────────────────────────┘");
                break;
        }
    }

    static void removeProd(){
        int removalType = 0;
        while (removalType != 5) {
                removeProdMenu();
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

    static void showItems(){
        if(productQueue.isEmpty()) {
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠   TRAY IS EMPTY   ⚠       │");
            System.out.println("└───────────────────────────────────┘");
        } else if (!checkLimits()) {
            for (Product p : productQueue) {
                System.out.println(p + " ");
            }
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│        ⚠   TRAY IS FULL   ⚠       │");
            System.out.println("└───────────────────────────────────┘");
        } else {
            for (Product p : productQueue) {
                System.out.println(p + " ");
            }
        }
    }

    static void searchFoodType(){
        System.out.println("─────────────────────────────────────────────");
        System.out.println("What type of food do you want to search for? ");
        System.out.println("─────────────────────────────────────────────");
        Type type = foodTypeMenu();
        System.out.println("\n┌────────────────────────────────────────────");
        System.out.println("│  🔍 Searching for: " + type + "...");
        System.out.println("└────────────────────────────────────────────┘\n");

        boolean found = false;
        for (Product p : productQueue) {
            if (p.getType() == type) {
                System.out.println(p + " ");
                found = true;
            }
        }
        if(!found){
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠  NO RESULT FOUND  ⚠       │");
            System.out.println("└───────────────────────────────────┘");
        }

    }

    static void searchByName(){
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("➤ Enter the name of the food you want to search for: ");
        System.out.println("─────────────────────────────────────────────────────");
        String name = scanner.nextLine();
        System.out.println("\n┌────────────────────────────────────────────");
        System.out.println("│  🔍 Searching for: " + name + "...");
        System.out.println("└────────────────────────────────────────────┘\n");

        boolean found = false;
        for (Product p : productQueue) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println(p + " ");
                found = true;
            }
        }
        if(!found){
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠  NO RESULT FOUND  ⚠       │");
            System.out.println("└───────────────────────────────────┘");
        }

    }

    static void searchByBestBeforeDate(){
        Date date = validate2weeks();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n┌────────────────────────────────────────────────────────────────");
        System.out.println("│  🔍 Searching for products with best before date: " + dateFormat.format(date) + "...");
        System.out.println("└────────────────────────────────────────────────────────────────────┘\n");

        boolean found = false;
        for (Product p : productQueue) {
            if (p.getBestBefore().equals(date)) {
                System.out.println(p + " ");
                found = true;
            }
        }
        if(!found){
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠  NO RESULT FOUND  ⚠       │");
            System.out.println("└───────────────────────────────────┘");
        }

    }

    private static Date truncateTime(Date d) {
        if (d == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    static void searchByAddedDate(){
        System.out.println("─────────────────────────────────────────────");
        System.out.println("What added date do you want to search for? ");
        System.out.println("─────────────────────────────────────────────");
        String addedDate = scanner.nextLine();
        if (addedDate.trim().isEmpty()) {
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠   INVALID DATE   ⚠        │");
            System.out.println("│     Enter a date (dd/MM/yyyy)     │");
            System.out.println("└───────────────────────────────────┘");
            return;
        }

        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            date = dateFormat.parse(addedDate);
        } catch (ParseException e) {
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠   INVALID DATE   ⚠        │");
            System.out.println("│     Enter a date (dd/MM/yyyy)     │");
            System.out.println("└───────────────────────────────────┘");
            return;
        }

        date = truncateTime(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n┌────────────────────────────────────────────────────────────────");
        System.out.println("│  🔍 Searching for products added on date: " + dateFormat.format(date) + "...");
        System.out.println("└────────────────────────────────────────────────────────────────────┘\n");

        boolean found = false;
        for (Product p : productQueue) {
            if (p.getDate()!= null && truncateTime(p.getDate()).equals(date)) {
                System.out.println(p + " ");
                found = true;
            }
        }
        if(!found){
            System.out.println("┌───────────────────────────────────┐");
            System.out.println("│       ⚠  NO RESULT FOUND  ⚠       │");
            System.out.println("└───────────────────────────────────┘");
        }

    }

    static void loadDummyData() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Clear existing data
            productQueue = new Deque<>();

            // Create products with specific dates (adjust these dates as needed)
            Product burger = new Product(Type.BURGER, "Whopper", 250.0, dateFormat.parse("28/10/2025"));
            Product pizza = new Product(Type.PIZZA, "Hawaiian", 400.0, dateFormat.parse("30/10/2025"));
            Product fries = new Product(Type.FRIES, "Curly Fries", 120.5, dateFormat.parse("26/10/2025"));
            Product sandwich = new Product(Type.SANDWICH, "BLT", 200.0, dateFormat.parse("30/10/2025"));
            Product hotdog = new Product(Type.HOTDOG, "Classic Dog", 160.0, dateFormat.parse("27/10/2025"));
            Product burger1 = new Product(Type.BURGER, "Helo-Burger", 350.0, dateFormat.parse("29/10/2025"));
            Product pizza1 = new Product(Type.PIZZA, "Pizza-Chrys", 420.0, dateFormat.parse("24/10/2025"));
            Product fries1 = new Product(Type.FRIES, "Barbs-Fries", 125.5, dateFormat.parse("27/10/2025"));

            // Add to queue (first added will be last in queue due to addFirst)
            productQueue.addFirst(hotdog);
            productQueue.addFirst(sandwich);
            productQueue.addFirst(fries);
            productQueue.addFirst(pizza);
            productQueue.addFirst(burger);
            productQueue.addFirst(fries1);
            productQueue.addFirst(pizza1);
            productQueue.addFirst(burger1);

            System.out.println("Dummy data loaded! 8 products added to the queue.");

        } catch (Exception e) {
            System.out.println("Error loading dummy data: " + e.getMessage());
        }
    }

    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String addedDate = (getDate() != null) ? format.format(getDate()) : "N/A";
        String bestBeforeDate = (getBestBefore() != null) ? format.format(getBestBefore()) : "N/A";

        return "┌─ " + getType() + " ────────────────────────────────────────────────────\n" +
                "│ Name: " + getName() + "\n" +
                "│ Weight: " + getWeight() + "g " +
                "│ Added: " + addedDate + " " +
                "│ Best Before: " + bestBeforeDate + " \n" +
                "└───────────────────────────────────────────────────────────────┘";
    }
}
