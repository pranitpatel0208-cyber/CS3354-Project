
package restaurant.service;

import restaurant.model.Order;

/**
 * Calculates prices, tax, and totals for restaurant orders.
 */
public class PriceCalculator {

    /** Tax rate used by the restaurant (8.25%). */
    private static final double TAX_RATE = 0.0825;

    /**
     * Creates a price calculator.
     */
    public PriceCalculator() {
        // No initialization is needed.
    }

    /**
     * Calculates the subtotal of an order.
     *
     * @param order the order to calculate
     * @return subtotal before tax
     * @throws IllegalArgumentException if the order is null
     */
    public double calculateSubtotal(Order order) {
        if (order == null) {
            throw new IllegalArgumentException(
                    "Order cannot be null.");
        }

        return order.getSubtotal();
    }

    /**
     * Calculates the tax for an order.
     *
     * @param order the order to calculate
     * @return tax amount
     * @throws IllegalArgumentException if the order is null
     */
    public double calculateTax(Order order) {
        if (order == null) {
            throw new IllegalArgumentException(
                    "Order cannot be null.");
        }

        return calculateSubtotal(order) * TAX_RATE;
    }

    /**
     * Calculates the final order total including tax.
     *
     * @param order the order to calculate
     * @return total including tax
     * @throws IllegalArgumentException if the order is null
     */
    public double calculateTotal(Order order) {
        if (order == null) {
            throw new IllegalArgumentException(
                    "Order cannot be null.");
        }

        return calculateSubtotal(order) + calculateTax(order);
    }
}
