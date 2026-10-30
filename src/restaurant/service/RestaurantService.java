
package restaurant.service;

import restaurant.model.Menu;
import restaurant.model.MenuItem;
import restaurant.model.Order;
import restaurant.model.OrderItem;
import restaurant.model.OrderStatus;

import java.util.ArrayList;

/**
 * Handles restaurant operations involving menus and customer orders.
 * Keeps the main business logic separate from console input.
 */
public class RestaurantService {

    /** The restaurant's available menu items. */
    private Menu menu;

    /** Stores the orders created by customers. */
    private ArrayList<Order> orders;

    /** ID assigned to the next order. */
    private int nextOrderId;

    /**
     * Creates a restaurant service using the supplied menu.
     *
     * @param menu the restaurant menu
     * @throws IllegalArgumentException if the menu is null
     */
    public RestaurantService(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException(
                    "Menu cannot be null.");
        }

        this.menu = menu;
        orders = new ArrayList<>();
        nextOrderId = 1;
    }

    /**
     * Gets the restaurant menu.
     *
     * @return the restaurant menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Creates a new empty order and assigns it a unique ID.
     *
     * @return the newly created order
     */
    public Order createOrder() {
        Order order = new Order(nextOrderId);
        orders.add(order);
        nextOrderId++;

        return order;
    }

    /**
     * Finds an order using its ID.
     *
     * @param orderId the ID of the order to find
     * @return the matching order, or {@code null} if not found
     */
    public Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    /**
     * Adds a copy of a menu item to an existing customer order.
     *
     * @param orderId the ID of the order
     * @param itemId the ID of the menu item
     * @param quantity the quantity being ordered
     * @return {@code true} if the item was added successfully;
     *         {@code false} otherwise
     */
    public boolean addItemToOrder(int orderId, int itemId, int quantity) {

        // Check that the quantity is valid.
        if (quantity <= 0) {
            return false;
        }

        // Find the customer's order.
        Order order = findOrderById(orderId);

        if (order == null) {
            return false;
        }

        // Find the original item in the restaurant menu.
        MenuItem menuItem = menu.findItemById(itemId);

        if (menuItem == null) {
            return false;
        }

        // Create an independent copy of the menu item.
        MenuItem customizedItem = menuItem.copy();

        // Add the copied item to the customer's order.
        OrderItem orderItem = new OrderItem(customizedItem, quantity);

        return order.addItem(orderItem);
    }

    /**
     * Removes an item from an existing customer order.
     *
     * @param orderId the ID of the order
     * @param itemIndex the zero-based position of the item
     * @return {@code true} if the item was removed successfully;
     *         {@code false} otherwise
     */
    public boolean removeItemFromOrder(int orderId, int itemIndex) {

        // Find the customer's order.
        Order order = findOrderById(orderId);

        // Check whether the order exists.
        if (order == null) {
            return false;
        }

        // Remove the item from the order.
        return order.removeItem(itemIndex);
    }

    /**
     * Returns a copy of the list of orders.
     *
     * @return a new list containing the restaurant's orders
     */
    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Updates the status of an existing order.
     *
     * @param orderId the ID of the order
     * @param newStatus the new order status
     * @return {@code true} if the status was updated successfully;
     *         {@code false} otherwise
     */
    public boolean updateOrderStatus(int orderId, OrderStatus newStatus) {

        Order order = findOrderById(orderId);

        if (order == null || newStatus == null) {
            return false;
        }

        order.setStatus(newStatus);

        return true;
    }
}
