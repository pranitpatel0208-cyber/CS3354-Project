
package restaurant.model;

import java.util.ArrayList;

/**
 * Stores restaurant menu items and provides operations to add, find,
 * remove, display, and retrieve menu items.
 */
public class Menu {

    /** List containing all items currently available on the menu. */
    private ArrayList<MenuItem> items;

    /**
     * Creates an empty menu.
     */
    public Menu() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item unless it is null or its ID is already in the menu.
     *
     * @param item the item to add
     * @return {@code true} if added; otherwise {@code false}
     */
    public boolean addItem(MenuItem item) {
        if (item == null || findItemById(item.getItemId()) != null) {
            return false;
        }

        items.add(item);
        return true;
    }

    /**
     * Finds a menu item using its unique ID.
     *
     * @param itemId the ID to search for
     * @return the matching item, or {@code null} if none exists
     */
    public MenuItem findItemById(int itemId) {
        for (MenuItem item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }

        return null;
    }

    /**
     * Removes the item with the specified ID, if present.
     *
     * @param itemId the ID of the item to remove
     * @return {@code true} if an item was removed; otherwise {@code false}
     */
    public boolean removeItem(int itemId) {
        MenuItem item = findItemById(itemId);

        if (item == null) {
            return false;
        }

        items.remove(item);
        return true;
    }

    /**
     * Prints either an empty-menu notice or the details of each menu item.
     */
    public void displayMenu() {
        if (items.isEmpty()) {
            System.out.println("The menu is empty.");
            return;
        }

        System.out.println("==================== RESTAURANT MENU ====================");

        for (MenuItem item : items) {
            System.out.println(item.displayDetails());
        }
    }

    /**
     * Returns a shallow copy of the menu item list so callers cannot directly
     * add or remove items from the menu's internal collection.
     *
     * @return a new list containing the menu's items
     */
    public ArrayList<MenuItem> getItems() {
        return new ArrayList<>(items);
    }
}
