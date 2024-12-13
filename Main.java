import java.util.*;

// Interface for Menu and Order Operations
interface RestaurantOperations {
    void loadItems();
    void saveItems();
}

// Abstract class to represent common attributes of Food Items and Orders
abstract class RestaurantItem {
    protected String name;
    protected int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

// Class for Food Items
class FoodItem extends RestaurantItem {
    public FoodItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + price;
    }
}

// Class for Orders
class Order extends RestaurantItem {
    private int quantity;
    private int total;

    public Order(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return name + " - " + quantity + " x " + price + " = " + total;
    }
}

// Menu class to manage food items
class Menu implements RestaurantOperations {
    private List<FoodItem> foodItems = new ArrayList<>();

    @Override
    public void loadItems() {
        // Initial setup for demonstration, no file operations
        foodItems.add(new FoodItem("Burger", 120));
        foodItems.add(new FoodItem("Pizza", 300));
        foodItems.add(new FoodItem("Pasta", 200));
    }

    @Override
    public void saveItems() {
        // Placeholder for functionality; not needed without file operations
    }

    public void addFoodItem(String name, int price) {
        foodItems.add(new FoodItem(name, price));
    }

    public void removeFoodItem(String name) {
        foodItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    public void updateFoodItem(String name, int newPrice) {
        for (FoodItem item : foodItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                foodItems.set(foodItems.indexOf(item), new FoodItem(name, newPrice));
                return;
            }
        }
        System.out.println("Food item not found.");
    }

    public void viewMenu() {
        System.out.println("Food Item Menu:");
        for (FoodItem item : foodItems) {
            System.out.println(item);
        }
    }

    public FoodItem findFoodItem(String name) {
        for (FoodItem item : foodItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}

// OrderManager class to manage orders
class OrderManager implements RestaurantOperations {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void loadItems() {
        // Placeholder for functionality; not needed without file operations
    }

    @Override
    public void saveItems() {
        // Placeholder for functionality; not needed without file operations
    }

    public void addOrder(String name, int quantity, int price) {
        orders.add(new Order(name, quantity, price));
    }

    public void calculateBill() {
        int totalBill = 0;
        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println(order);
            totalBill += order.getTotal();
        }
        System.out.println("Total Bill: " + totalBill);
    }
}

// Main Restaurant Management System class
 public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Menu menu = new Menu();
        OrderManager orderManager = new OrderManager();

        menu.loadItems();
        orderManager.loadItems();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=============== Welcome ===============");
        System.out.println("<<<<<<<<< AZ'S SITE >>>>>>>>>>>>\n===== Restaurant Management System =====");

        while (true) {
            System.out.println("\n1. Add Food Item\n2. Remove Food Item\n3. Update Food Item\n4. View Menu\n5. Take Order\n6. Calculate Bill\n7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Food Item name: ");
                    String name = scanner.next();
                    System.out.print("Enter Food Item price: ");
                    int price = scanner.nextInt();
                    menu.addFoodItem(name, price);
                    break;
                case 2:
                    System.out.print("Enter Food Item name to remove: ");
                    name = scanner.next();
                    menu.removeFoodItem(name);
                    break;
                case 3:
                    System.out.print("Enter Food Item name to update: ");
                    name = scanner.next();
                    System.out.print("Enter new price: ");
                    price = scanner.nextInt();
                    menu.updateFoodItem(name, price);
                    break;
                case 4:
                    menu.viewMenu();
                    break;
                case 5:
                    System.out.print("Enter Food Item name: ");
                    name = scanner.next();
                    FoodItem item = menu.findFoodItem(name);
                    if (item != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        orderManager.addOrder(item.getName(), quantity, item.getPrice());
                    } else {
                        System.out.println("Food Item not found.");
                    }
                    break;
                case 6:
                    orderManager.calculateBill();
                    break;
                case 7:
                    System.out.println("<<<<<<<<< Have a nice day >>>>>>>>>>");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
