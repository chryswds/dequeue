package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(Date bestBefore) {
        this.bestBefore = bestBefore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    static Deque<Product> productQueue = new Deque<>();
    static int maxSize = 8;

    static boolean checkLimits(){
        if (productQueue.size() == maxSize){
            return false;
        }
        return true;
    }

    static boolean checkEmpty(){
        if(productQueue.isEmpty()){
            return false;
        }
        return true;
    }



    static void addProd(){
        if (checkLimits()) {

            Scanner scanner = new Scanner(System.in);

            // TYPE
            //Prints ENUM and let the user choose by typing
            Type type = null;
            boolean validType = false;
            while (!validType) {

                System.out.println("Choose a type of food: ");
                for (Type t : Type.values()) {
                    System.out.println(t);
                }
                String userInputType = scanner.nextLine().toUpperCase();

                try {
                    type = Type.valueOf(userInputType);
                    validType = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("invalid type, please try again");
                }
            }

            // NAME
            // User can choose name of the food
            System.out.println("Choose a name of food: ");
            String userInputName = scanner.nextLine();

            // WEIGHT
            double weight = -1;
            boolean validWeight = false;
            while (!validWeight) {
                // user specifies how much the food weights
                System.out.println("How much it weights? (grams)");
                double weightInput = scanner.nextDouble();
                try {
                    weight = weightInput;
                    if (weight > 0) {
                        validWeight = true;
                    } else {
                        System.out.println("Invalid weight, it must be positive");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("invalid number, please enter a valid number");
                }
            }

            // prevents recursion
            scanner.nextLine();


            // DATE
            Date date = null;
            boolean validDate = false;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            while (!validDate) {
                System.out.print("Enter best before date (dd/MM/yyyy): ");
                String inputDate = scanner.nextLine();
                try {
                    date = dateFormat.parse(inputDate);
                    Date today = new Date();

                    Calendar currentDateAfter2Weeks = Calendar.getInstance();
                    currentDateAfter2Weeks.add(Calendar.DAY_OF_WEEK, 14);
                    Date twoWeeksLater = currentDateAfter2Weeks.getTime();

                    if (date.after(twoWeeksLater)) {
                        System.out.println("the best before date cant be more than 2 weeks from today");
                    } else if (date.before(today)) {
                        System.out.println("The date cannot be in the past");
                    } else {
                        validDate = true;
                    }
                } catch (ParseException e) {
                    System.out.println("invalid date, please enter a valid date");
                }
            }


            // CREATE PRODUCT
            Product foodItem = new Product(type, userInputName, weight, date);
            productQueue.addFirst(foodItem);
            System.out.println("Successfully added food item");
        } else {
            System.out.println("cant have more than 8 trays");
        }

    }

    static void showItems(){
        if(productQueue.isEmpty()) {
            System.out.println("All trays are empty");
        } else if (!checkLimits()) {
            for (Product p : productQueue) {
                System.out.println(p + " ");
            }
            System.out.println("TRAY IS FULL");
        } else {
            for (Product p : productQueue) {
                System.out.println(p + " ");
            }
        }
    }

    static void peekFirstItem(){
        if(productQueue.isEmpty()) {
            System.out.print(" - ");
            System.out.println("All trays are empty");
        } else {
            System.out.println(" | " + " Item in the first tray : " + productQueue.peekFirst());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            return;
        }
    }

    static void peekLastItem(){
        if(productQueue.isEmpty()) {
            System.out.print(" - ");
            System.out.println("All trays are empty");
        }else {
            System.out.println(" | " + " Item in the last tray : " + productQueue.peekLast());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            return;
        }
    }

    static void removeFirstTray(){
        try {
            productQueue.removeFirst();
        }catch (Exception e){
            System.out.println("Cannot remove from empty tray");
        }
    }
    static void removeLastTray(){
        try {
            productQueue.removeLast();
        } catch (Exception e){
            System.out.println("Cannot remove from empty tray");
        }
    }

    static Product searchFoodType(){
        Scanner scanner = new Scanner(System.in);
        Type type = null;
        boolean validType = false;
        while (!validType) {

            System.out.println("What type of food do you want to search for? ");
            for (Type t : Type.values()) {
                System.out.println(t);
            }
            String userInputType = scanner.nextLine().toUpperCase();

            try {
                type = Type.valueOf(userInputType);
                validType = true;
            } catch (IllegalArgumentException e) {
                System.out.println("invalid type, please try again");
            }
        }

        boolean found = false;


        for (Product p : productQueue) {
            if (p.getType() == type) {
                System.out.println(p + " ");
                found = true;
            }
        }
        if(!found){
            System.out.println("No results found");
        }

        return null;

    }



    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String addedDate = (getDate() != null) ? format.format(getDate()) : "N/A";
        String bestBeforeDate = (getBestBefore() != null) ? format.format(getBestBefore()) : "N/A";

        return "Type: " + getType() + ", Name: " + getName() + ", Weight: " + getWeight() + "g" + ", Time Added: " + addedDate + ", Best Before: " + bestBeforeDate ;
    }
}
