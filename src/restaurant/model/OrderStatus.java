
package restaurant.model;

/**
 * Represents the possible statuses of a restaurant order.
 */
public enum OrderStatus {

    /** The order has been created but work has not started. */
    PENDING,

    /** The restaurant is currently preparing the order. */
    PREPARING,

    /** The order is ready for customer pickup or delivery. */
    READY,

    /** The order has been successfully completed. */
    COMPLETED,

    /** The order has been cancelled. */
    CANCELLED
}
