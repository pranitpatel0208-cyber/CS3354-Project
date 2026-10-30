
package restaurant.model;

import java.util.ArrayList;

/**
 * Represents a customer order containing priced items and an order status.
 */
public class Order {

    /** Unique ID number assigned to this order. */
    private int orderId;

    /** List containing all items added to this order. */
    private ArrayList<OrderItem> orderItems;

    /** Current status of the order. */
    private OrderStatus status;

    /**
     * Creates an empty order in the pending state.
     *
     * @param orderId the positive identifier for this order
     * @throws IllegalArgumentException if the ID is not positive
     */
    public Order(int orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }

        this.orderId = orderId;
        orderItems = new ArrayList<>();
        status = OrderStatus.PENDING;
    }

    /**
     * Adds an order line unless the supplied item is null.
     *
     * @param item the order line to add
     * @return {@code true} if added; otherwise {@code false}
     */
    public boolean addItem(OrderItem item) {
        if (item == null) {
            return false;
        }

        orderItems.add(item);
        return true;
    }

    /**
     * Removes the order line at the specified zero-based position.
     *
     * @param index the position of the line to remove
     * @return {@code true} if removed; otherwise {@code false} for an invalid index
     */
    public boolean removeItem(int index) {
        if (index < 0 || index >= orderItems.size()) {
            return false;
        }

        orderItems.remove(index);
        return true;
    }

    /**
     * Calculates the subtotal of all items in the order before tax.
     *
     * @return the sum of the total prices of all order lines
     */
    public double getSubtotal() {
        double subtotal = 0.0;

        for (OrderItem item : orderItems) {
            subtotal += item.getTotalPrice();
        }

        return subtotal;
    }

    /**
     * Calculates tax at the order's fixed 8.25% rate.
     *
     * @return the tax on the subtotal
     */
    public double getTax() {
        return getSubtotal() * 0.0825;
    }

    /**
     * Calculates the final order amount, including tax.
     *
     * @return the subtotal plus tax
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Gets the unique ID assigned to this order.
     *
     * @return this order's ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Gets the current order status.
     *
     * @return current order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Updates the order status.
     *
     * @param status the non-null status to assign
     * @throws IllegalArgumentException if {@code status} is null
     */
    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }

        this.status = status;
    }

    /**
     * Returns a shallow copy so callers cannot directly modify the order's
     * list of lines.
     *
     * @return a new list containing the order lines
     */
    public ArrayList<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    /**
     * Prints the order ID and status, followed by its lines and totals when
     * the order contains items.
     */
    public void displayOrder() {
        System.out.println("===== ORDER #" + orderId + " =====");
        System.out.println("Status: " + status);

        if (orderItems.isEmpty()) {
            System.out.println("The order is empty.");
            return;
        }

        for (OrderItem item : orderItems) {
            System.out.println(item.displayDetails());
        }

        System.out.printf("Subtotal: $%.2f%n", getSubtotal());
        System.out.printf("Tax:      $%.2f%n", getTax());
        System.out.printf("Total:    $%.2f%n", getTotal());
    }
}
