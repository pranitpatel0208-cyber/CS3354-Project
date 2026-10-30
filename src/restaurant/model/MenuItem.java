
package restaurant.model;

/**
 * Abstract superclass representing a general restaurant menu item.
 * Specific menu item types extend this class.
 */
public abstract class MenuItem {

    /** Unique ID assigned to the menu item. */
    private int itemId;

    /** Name of the menu item. */
    private String name;

    /** Original price of the menu item before additional charges. */
    private double basePrice;

    /**
     * Creates a menu item with an ID, name, and base price.
     *
     * @param itemId the positive ID of the item
     * @param name the name of the item
     * @param basePrice the non-negative base price
     * @throws IllegalArgumentException if the ID, name, or price is invalid
     */
    public MenuItem(int itemId, String name, double basePrice) {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be positive.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }

        if (!Double.isFinite(basePrice) || basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative.");
        }

        this.itemId = itemId;
        this.name = name;
        this.basePrice = basePrice;
    }

    /**
     * Gets the menu item's unique ID.
     *
     * @return the item ID
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Gets the menu item's name.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the original base price of the menu item.
     *
     * @return the base price
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Calculates the current price of the menu item.
     *
     * @return the calculated item price
     */
    public abstract double getPrice();

    /**
     * Creates a formatted description of the menu item.
     *
     * @return the item's ID, name, and current price
     */
    public String displayDetails() {
        return "ID: " + itemId
                + " | " + name
                + " | Price: $" + String.format("%.2f", getPrice());
    }

    /**
     * Creates an independent copy of this menu item.
     *
     * @return a copy of the menu item
     */
    public abstract MenuItem copy();
}
