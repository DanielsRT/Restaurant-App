import java.util.*;
import java.io.*;

public class FastFoodDashApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the Fast Food Dash App");
        if (args.length < 1) {
            System.out.println("Please enter filenames on command line");
            System.exit(0);
        }
        
        List<Restaurant> restaurantChoices = new ArrayList<>();
        for (int ndx = 0; ndx < args.length; ndx++) {
            String filename = args[ndx];
            Restaurant newRestaurant = readDataFromFile(filename);
            restaurantChoices.add(newRestaurant);
            System.out.printf("%d) %s located at %s\n",
                              ndx,newRestaurant.getName(),newRestaurant.getLocation());
        }
        
        Scanner keyb = new Scanner(System.in);
        System.out.print("\nEnter the number for the new restaurant: ");
        int restaurantNumber = keyb.nextInt();
        Restaurant chosenRestaurant = restaurantChoices.get(restaurantNumber);
        
        System.out.println();
        System.out.println(chosenRestaurant);
        
        List<MenuItem> userOrder = getUserOrder(chosenRestaurant);
        
        Double subtotal = getPriceTotal(userOrder);
        Double tax = subtotal * 0.095;
        Double total = subtotal + tax;
        
        System.out.printf("\nYour %s Order:\n",chosenRestaurant.getName());
        for (MenuItem item : userOrder) {
            System.out.println(item);
        }
        System.out.printf("\t\t      Subtotal: $%2.2f\n",subtotal);
        System.out.printf("\t\t           tax: $%2.2f\n",tax);
        System.out.printf("\t\t         total: $%2.2f\n",total);
        
        System.out.println("\nThanks for using the Restaurant Dash App");
    }
    
    //Double subtotal = getPriceTotal(userOrder);
    public static Double getPriceTotal(List<MenuItem> userOrder) {
        double subtotal = 0;
        for (int ndx = 0; ndx < userOrder.size(); ndx++) {
            MenuItem currentItem = userOrder.get(ndx);
            Double itemPrice = currentItem.getPrice();
            subtotal += itemPrice;
        }
        return subtotal;
    }
    
    //List<MenuItem> userOrder = getUserOrder(r1);
    public static List<MenuItem> getUserOrder(Restaurant chosenRestaurant) {
        Scanner keyb = new Scanner(System.in);
        int itemNumber = 0;
        List<MenuItem> currentMenu = chosenRestaurant.getMenu();
        List<MenuItem> userOrder = new ArrayList<>();
        while (itemNumber >= 0) {
            System.out.print("Enter the number of the the item (negative to finish): ");
            itemNumber = keyb.nextInt();
            if (itemNumber >= 0) {
                MenuItem currentItem = currentMenu.get(itemNumber);
                String itemDescription = currentItem.getDescription();
                Double itemPrice = currentItem.getPrice();
                System.out.printf("Adding '%s' at $%.2f to the order\n\n",itemDescription,itemPrice);
                userOrder.add(currentItem);
                System.out.println(chosenRestaurant);
            } 
        }
        return userOrder;
    }
    
    //Restaurant r1 = readDataFromFile(filename);
    public static Restaurant readDataFromFile(String filename) {
        String name = "";
        String location = "";
        List<MenuItem> menu = new ArrayList<>();
        try (Scanner fileScan = new Scanner (new File(filename))) {
            name = fileScan.nextLine();
            location = fileScan.nextLine();
            while (fileScan.hasNextLine()) {
                String dataLine = fileScan.nextLine();
                MenuItem i = new MenuItem(dataLine);
                menu.add(i);
            }
        } catch (Exception e) {
            System.err.printf("Couldn't open %s\n",filename);
            System.exit(0);
        }
        return new Restaurant(name,location,menu);
    }
    
}