import java.util.ArrayList;

public class ShoppingList {
    private String name;
    private ArrayList<String> items;

    // Constructor
    public ShoppingList(String listName) {
        name = listName.trim();
        items = new ArrayList<>();
    }

    // Adds a single item to the list if not already present
    public void addItem(String item) {
        if (item == null) return;
        String lowerItem = item.trim().toLowerCase();
        if (!items.contains(lowerItem)) {
            items.add(lowerItem);
        }
    }

    // Adds all items from another ShoppingList
    public void addItem(ShoppingList otherList) {
        for (String item : otherList.items) {
            if (!items.contains(item)) {
                items.add(item);
            }
        }
    }

    // Removes a specified item if it exists
    public void removeItem(String item) {
        if (item == null) return;
        items.remove(item.trim().toLowerCase());
    }

    // Returns true if item is in the list
    public boolean hasItem(String item) {
        if (item == null) return false;
        return items.contains(item.trim().toLowerCase());
    }

    // Returns number of items in the list
    public int getItemCount() {
        return items.size();
    }

    // Prints the list in the specified format
    public void printList() {
        System.out.println(name + " (" + getItemCount() + ")");
        if (items.isEmpty()) {
            System.out.println("*");
        } else {
            for (String item : items) {
                System.out.println(item);
            }
        }
    }

    // Clears all items from the list
    public void clear() {
        items.clear();
    }

    // Returns the name of the list
    public String getName() {
        return name;
    }

    // Returns a new ShoppingList combining current list and another
    public ShoppingList combine(ShoppingList otherList) {
        ShoppingList combined = new ShoppingList(name + "_" + otherList.name);

        // Add all items from current list
        for (String item : items) {
            combined.addItem(item);
        }

        // Add items from otherList that aren't duplicates
        for (String item : otherList.items) {
            combined.addItem(item);
        }

        return combined;
    }
}
