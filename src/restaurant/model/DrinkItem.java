
package restaurant.model;

/**
 * Represents a drink menu item with a selectable size and optional extra ice.
 */
public class DrinkItem extends MenuItem {

    /** Stores the selected drink size. */
    private String size;

    /** Stores the additional price for the selected drink size. */
    private double sizePrice;

    /** Indicates whether the customer requested extra ice. */
    private boolean extraIce;

    /**
     * Creates a medium drink with no size surcharge or extra ice.
     *
     * @param itemId the positive identifier for this item
     * @param name the drink's name
     * @param basePrice the finite, nonnegative starting price
     */
    public DrinkItem(int itemId, String name, double basePrice) {
        super(itemId, name, basePrice);

        size = "Medium";
        sizePrice = 0.0;
        extraIce = false;
    }

    /**
     * Selects the drink size and its additional charge.
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
     * Sets whether this drink should include extra ice.
     *
     * @param extraIce {@code true} to request extra ice
     */
    public void setExtraIce(boolean extraIce) {
        this.extraIce = extraIce;
    }

    /**
     * Gets the currently selected drink size.
     *
     * @return the selected drink size
     */
    public String getSize() {
        return size;
    }

    /**
     * Checks whether extra ice has been selected.
     *
     * @return {@code true} if extra ice is selected; otherwise {@code false}
     */
    public boolean hasExtraIce() {
        return extraIce;
    }

    /**
     * Calculates the final drink price, including the size surcharge.
     *
     * @return the base price plus the size surcharge
     */
    public double getPrice() {
        return getBasePrice() + sizePrice;
    }

    /**
     * Creates a formatted description of the drink, including its size and ice preference.
     *
     * @return the shared item details along with size and ice preference
     */
    public String displayDetails() {
        return super.displayDetails() + " | Size: " + size + " | Extra Ice: " + extraIce;
    }

    /**
     * Creates an independent copy of this drink item.
     *
     * @return a copied DrinkItem with the same size and ice preference
     */
    @Override
    public MenuItem copy() {
        DrinkItem copiedItem = new DrinkItem(
            getItemId(), getName(), getBasePrice()
        );

        copiedItem.setSize(this.size, this.sizePrice);
        copiedItem.setExtraIce(this.extraIce);

        return copiedItem;
    }
}
