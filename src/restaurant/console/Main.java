
package restaurant.console;

import restaurant.model.*;
import restaurant.service.RestaurantService;

/**
 * Main class used to start the restaurant ordering system.
 * Creates the sample menu, initializes the restaurant service,
 * and launches the console application.
 */
public class Main {
    /**
    * Creates the main application class.
     */
    public Main() {
        // No initialization is needed.
    }

    /**
     * Main entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Create the restaurant menu.
        Menu menu = new Menu();

        // Add sample food items.
        menu.addItem(new FoodItem(1, "Burger", 8.99));
        menu.addItem(new FoodItem(2, "Pizza", 12.99));

        // Add sample drinks.
        menu.addItem(new DrinkItem(3, "Soda", 2.50));
        menu.addItem(new DrinkItem(4, "Lemonade", 3.00));

        // Add sample desserts.
        menu.addItem(new DessertItem(5, "Chocolate Cake", 4.50, "Cake"));

        // Create the restaurant service.
        RestaurantService service = new RestaurantService(menu);

        // Start the console application.
        ConsoleUI console = new ConsoleUI(service);
        console.start();
    }
}
