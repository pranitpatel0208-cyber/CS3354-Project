
package restaurant.console;

import java.util.Scanner;

import restaurant.model.*;
import restaurant.service.RestaurantService;
import restaurant.service.PriceCalculator;

/**
 * Handles the console interface for the restaurant ordering system.
 * Allows customers to interact with the restaurant application.
 */
public class ConsoleUI {

    /** Reads input entered by the customer. */
    private Scanner scanner;

    /** Handles restaurant operations and business logic. */
    private RestaurantService restaurantService;

    /** Calculates order prices and totals. */
    private PriceCalculator priceCalculator;

    /**
     * Creates the console interface.
     *
     * @param restaurantService the restaurant service
     * @throws IllegalArgumentException if the restaurant service is null
     */
    public ConsoleUI(RestaurantService restaurantService) {
        if (restaurantService == null) {
            throw new IllegalArgumentException(
                    "Restaurant service cannot be null.");
        }

        this.scanner = new Scanner(System.in);
        this.restaurantService = restaurantService;
        this.priceCalculator = new PriceCalculator();
    }

    /**
     * Starts the restaurant application.
     * Continues running until the customer chooses Exit.
     */
    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== RESTAURANT ORDERING SYSTEM =====");
            System.out.println("1. View Menu");
            System.out.println("2. Create New Order");
            System.out.println("3. Add Item to Order");
            System.out.println("4. View Order");
            System.out.println("5. Update Order Status");
            System.out.println("6. Remove Item from Order");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    viewMenu();
                    break;

                case 2:
                    createNewOrder();
                    break;

                case 3:
                    addItemToOrder();
                    break;

                case 4:
                    viewOrder();
                    break;

                case 5:
                    updateOrderStatus();
                    break;

                case 6:
                    removeItemFromOrder();
                    break;

                case 7:
                    running = false;
                    System.out.println("\nThank you for visiting!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    /**
     * Reads an integer from the customer.
     * Handles invalid input without crashing the program.
     *
     * @return the integer entered by the customer
     */
    private int readInt() {

        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    /**
     * Displays all available restaurant menu items.
     */
    private void viewMenu() {

        System.out.println("\n===== RESTAURANT MENU =====");

        restaurantService.getMenu().displayMenu();
    }

    /**
     * Creates a new customer order.
     */
    private void createNewOrder() {

        Order order = restaurantService.createOrder();

        System.out.println("\nOrder created successfully!");
        System.out.println("Your Order ID is: " + order.getOrderId());
    }

    /**
     * Adds a menu item to an existing order.
     */
    private void addItemToOrder() {

        System.out.print("\nEnter your Order ID: ");
        int orderId = readInt();

        System.out.print("Enter the Menu Item ID: ");
        int itemId = readInt();

        System.out.print("Enter the quantity: ");
        int quantity = readInt();

        boolean success = restaurantService.addItemToOrder(
            orderId, itemId, quantity
        );

        if (success) {
            System.out.println("\nItem added to your order successfully!");
        } else {
            System.out.println(
                "\nUnable to add item. Check the order ID, item ID, and quantity."
            );
        }
    }

    /**
     * Displays an existing order and its price details.
     */
    private void viewOrder() {

        System.out.print("\nEnter your Order ID: ");
        int orderId = readInt();

        Order order = restaurantService.findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
        } else {
            order.displayOrder();
        }
    }

    /**
     * Allows an employee to update an order's status.
     */
    private void updateOrderStatus() {

        System.out.print("\nEnter Order ID: ");
        int orderId = readInt();

        System.out.println("\nChoose New Status:");
        System.out.println("1. PENDING");
        System.out.println("2. PREPARING");
        System.out.println("3. READY");
        System.out.println("4. COMPLETED");
        System.out.println("5. CANCELLED");

        System.out.print("Enter your choice: ");
        int choice = readInt();

        OrderStatus newStatus;

        switch (choice) {
            case 1:
                newStatus = OrderStatus.PENDING;
                break;
            case 2:
                newStatus = OrderStatus.PREPARING;
                break;
            case 3:
                newStatus = OrderStatus.READY;
                break;
            case 4:
                newStatus = OrderStatus.COMPLETED;
                break;
            case 5:
                newStatus = OrderStatus.CANCELLED;
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        boolean updated = restaurantService.updateOrderStatus(
            orderId, newStatus
        );

        if (updated) {
            System.out.println("Order status updated successfully!");
        } else {
            System.out.println("Order not found.");
        }
    }

    /**
     * Allows a customer to remove an item from an order.
     */
    private void removeItemFromOrder() {

        System.out.print("\nEnter Order ID: ");
        int orderId = readInt();

        Order order = restaurantService.findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.getOrderItems().isEmpty()) {
            System.out.println("This order is empty.");
            return;
        }

        // Display the current order.
        order.displayOrder();

        System.out.print("\nEnter the item position to remove (1, 2, 3...): ");
        int position = readInt();

        // Convert the customer's position to a list index.
        boolean removed = restaurantService.removeItemFromOrder(
            orderId, position - 1
        );

        if (removed) {
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Invalid item position.");
        }
    }
}
