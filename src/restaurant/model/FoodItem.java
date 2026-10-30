
package restaurant.model;

import java.util.*;

/**
 * Represents a food menu item with a selectable size and optional priced toppings.
 */
public class FoodItem extends MenuItem {

    /** Stores the selected food size. */
    private String size;

    /** Stores the list of toppings added to the food item. */
    private ArrayList<String> toppings;

    /** Stores the additional price for the selected food size. */
    private double sizePrice;

    /** Stores the price charged for each topping. */
    private double toppingPrice;

    /**
     * Creates a medium food item with no size surcharge or toppings.
     *
     * @param itemId the positive identifier for this item
     * @param name the food's name
     * @param basePrice the finite, nonnegative starting price
     */
    public FoodItem(int itemId, String name, double basePrice) {
        super(itemId, name, basePrice);

        size = "Medium";
        toppings = new ArrayList<>();
        sizePrice = 0.0;
        toppingPrice = 1.00;
    }

    /**
     * Selects the food size and its additional charge.
     *
     * @param size the size, either Small, Medium, or Large
     * @param additionalPrice the finite, nonnegative surcharge
     * @throws IllegalArgumentException if the size or surcharge is invalid
     */
    public void setSize(String size, double additionalPrice) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be empty.");
        }

        if (additionalPrice < 0 || Double.isNaN(additionalPrice) || Double.isInfinite(additionalPrice)) {
            throw new IllegalArgumentException("Invalid size price.");
        }

        String selectedSize = size.trim();

        if (!selectedSize.equalsIgnoreCase("Small") && !selectedSize.equalsIgnoreCase("Medium") && !selectedSize.equalsIgnoreCase("Large")) {
            throw new IllegalArgumentException("Size must be Small, Medium, or Large.");
        }

        this.size = selectedSize;
        this.sizePrice = additionalPrice;
    }

    /**
     * Adds a topping, which contributes the item's topping charge to its price.
     *
     * @param topping the nonblank topping name
     * @throws IllegalArgumentException if the topping is null or blank
     */
    public void addTopping(String topping) {
        if (topping == null || topping.trim().isEmpty()) {
            throw new IllegalArgumentException("Topping cannot be empty.");
        }

        toppings.add(topping.trim());
    }

    /**
     * Removes the first matching topping, ignoring case.
     *
     * @param topping the topping name to remove
     * @return {@code true} if a topping was removed; otherwise {@code false}
     */
    public boolean removeTopping(String topping) {
        if (topping == null || topping.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < toppings.size(); i++) {
            if (toppings.get(i).equalsIgnoreCase(topping.trim())) {
                toppings.remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the currently selected food size.
     *
     * @return the selected food size
     */
    public String getSize() {
        return size;
    }

    /**
     * Returns a copy so callers cannot directly modify this item's toppings.
     *
     * @return a new list containing the topping names
     */
    public ArrayList<String> getToppings() {
        return new ArrayList<>(toppings);
    }

    /**
     * Calculates the final food price, including size and topping charges.
     *
     * @return the base price, size surcharge, and one charge per topping
     */
    public double getPrice() {
        return getBasePrice() + sizePrice + (toppings.size() * toppingPrice);
    }

    /**
     * Creates a formatted description of the food item, including its size and toppings.
     *
     * @return the shared item details along with size and toppings
     */
    public String displayDetails() {
        return super.displayDetails() + " | Size: " + size + "| Toppings: " + toppings;
    }

    /**
     * Creates an independent copy of this food item.
     *
     * @return a copied FoodItem with the same size and toppings
     */
    @Override
    public MenuItem copy() {
        FoodItem copiedItem = new FoodItem(
            getItemId(), getName(), getBasePrice()
        );

        copiedItem.setSize(this.size, this.sizePrice);

        for (String topping : this.toppings) {
            copiedItem.addTopping(topping);
        }

        return copiedItem;
    }
}
