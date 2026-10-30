
package restaurant.model;

/**
 * Represents a dessert menu item with a dessert type and selectable serving size.
 */
public class DessertItem extends MenuItem {

    /** Describes the category or type of dessert. */
    private String dessertType;

    /** Stores the selected serving size. */
    private String servingSize;

    /** Stores the additional price for the selected serving size. */
    private double sizePrice;

    /**
     * Creates a dessert with a regular serving and no size surcharge.
     *
     * @param itemId the positive identifier for this item
     * @param name the dessert's name
     * @param basePrice the finite, nonnegative starting price
     * @param dessertType the dessert's type; must not be {@code null}
     * @throws IllegalArgumentException if the shared item data or dessert type is invalid
     */
    public DessertItem(int itemId, String name, double basePrice, String dessertType) {
        super(itemId, name, basePrice);

        if (dessertType == null) {
            throw new IllegalArgumentException("Dessert type cannot be empty.");
        }

        this.dessertType = dessertType.trim();
        servingSize = "Regular";
        sizePrice = 0.0;
    }

    /**
     * Selects the serving size and its additional charge.
     *
     * @param servingSize the serving size, either Regular or Large
     * @param additionalPrice the finite, nonnegative surcharge
     * @throws IllegalArgumentException if the size or surcharge is invalid
     */
    public void setServingSize(String servingSize, double additionalPrice) {
        if (servingSize == null || servingSize.trim().isEmpty()) {
            throw new IllegalArgumentException("Serving size cannot be empty.");
        }

        if (additionalPrice < 0 || Double.isNaN(additionalPrice) || Double.isInfinite(additionalPrice)) {
            throw new IllegalArgumentException("Invalid serving size price.");
        }

        String selectedSize = servingSize.trim();

        if (!selectedSize.equalsIgnoreCase("Regular") && !selectedSize.equalsIgnoreCase("Large")) {
            throw new IllegalArgumentException("Serving size must be Regular or Large.");
        }

        this.servingSize = selectedSize;
        this.sizePrice = additionalPrice;
    }

    /**
     * Gets the type of dessert.
     *
     * @return the dessert's type
     */
    public String getDessertType() {
        return dessertType;
    }

    /**
     * Gets the currently selected serving size.
     *
     * @return the selected serving size
     */
    public String getServingSize() {
        return servingSize;
    }

    /**
     * Calculates the final dessert price, including the serving-size surcharge.
     *
     * @return the base price plus the serving-size surcharge
     */
    public double getPrice() {
        return getBasePrice() + sizePrice;
    }

    /**
     * Creates a formatted description of the dessert, including its type and serving size.
     *
     * @return the shared item details along with dessert type and serving size
     */
    public String displayDetails() {
        return super.displayDetails() + " | Type: " + dessertType + " | Serving Size: " + servingSize;
    }

    /**
     * Creates an independent copy of this dessert item.
     *
     * @return a copied DessertItem with the same type and serving size
     */
    @Override
    public MenuItem copy() {
        DessertItem copiedItem = new DessertItem(
            getItemId(), getName(), getBasePrice(), this.dessertType
        );

        copiedItem.setServingSize(this.servingSize, this.sizePrice);

        return copiedItem;
    }
}
