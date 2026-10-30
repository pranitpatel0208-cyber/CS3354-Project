
package restaurant.model;

/**
 * Represents a menu item and its quantity as one line in a customer order.
 */
public class OrderItem {

    /** The menu item included in this order line. */
    private MenuItem menuItem;

    /** The number of units of the menu item ordered. */
    private int quantity;

    /** The menu item's price captured when this order line was created. */
    private double unitPrice;

    /**
     * Creates an order line and captures the menu item's current price.
     *
     * @param menuItem the non-null item being ordered
     * @param quantity the positive quantity ordered
     * @throws IllegalArgumentException if the item is null or quantity is not positive
     */
    public OrderItem(MenuItem menuItem, int quantity) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        this.menuItem = menuItem;
        this.quantity = quantity;
        this.unitPrice = menuItem.getPrice();
    }

    /**
     * Gets the menu item on this order line.
     *
     * @return the menu item on this order line
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Gets the number of units ordered.
     *
     * @return the number of units ordered
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the unit price captured when this order line was created.
     *
     * @return the captured unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Calculates the total price for this order line.
     *
     * @return the captured unit price multiplied by the quantity
     */
    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    /**
     * Changes the quantity without changing the captured unit price.
     *
     * @param quantity the positive quantity to set
     * @throws IllegalArgumentException if the quantity is not positive
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        this.quantity = quantity;
    }

    /**
     * Formats the item's name, quantity, captured unit price, and line total.
     *
     * @return this order line's display text
     */
    public String displayDetails() {
        return menuItem.getName()
                + " | Quantity: " + quantity
                + " | Unit Price: $"
                + String.format("%.2f", unitPrice)
                + " | Total: $"
                + String.format("%.2f", getTotalPrice());
    }
}
