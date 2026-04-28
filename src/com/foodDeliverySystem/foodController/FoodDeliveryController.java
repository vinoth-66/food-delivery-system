package com.foodDeliverySystem.foodController;

import com.foodDeliverySystem.foodModel.MenuItem;
import com.foodDeliverySystem.foodModel.Restaurant;
import com.foodDeliverySystem.foodService.FoodDeliveryService;
import java.util.List;
import java.util.Scanner;

public class FoodDeliveryController {
    private FoodDeliveryService service;
    private Scanner scanner = new Scanner(System.in);

    public FoodDeliveryController(FoodDeliveryService service) {
        this.service = service;
    }

    public void start() {
        System.out.println("Welcome to the Food Delivery Console App");
        while (true) {
            printMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    showRestaurents();
                    break;
                case "2":
                    createCart();
                    break;
                case "3":
                    addItemToCart();
                    break;
                case "4":
                    removeItemFromCart();
                    break;
                case "5":
                    viewCart();
                    break;
                case "6":
                    placeOrder();
                    break;
                case "7":
                    cancelOrder();
                    break;
                case "8":
                    acceptDelivery();
                    break;
                case "9":
                    markDelivered();
                    break;
                case "10":
                    submitRating();
                    break;
                case "11":
                    addMenuItem();
                    break;
                case "12":
                    removeMenuItem();
                    break;
                case "13":
                    updatePriceForItem();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
    }

    public void printMenu() {
        System.out.println();
        System.out.println("=== Food Delivery Console ===");
        System.out.println("1. Show restaurant menu");
        System.out.println("2. Create cart");
        System.out.println("3. Add menu item to cart");
        System.out.println("4. Remove item from cart");
        System.out.println("5. View cart");
        System.out.println("6. Place order");
        System.out.println("7. Cancel order");
        System.out.println("8. Accept delivery");
        System.out.println("9. Mark delivery delivered");
        System.out.println("10. Submit rating");
        System.out.println("11. Add menu item");
        System.out.println("12. Remove menu item");
        System.out.println("13. Update price for an item");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public void showRestaurents(){
        List<Restaurant> restaurants = service.getRestaurents();
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }
        for (Restaurant restaurant : restaurants) {
            System.out.printf("Restaurant %d: %s | Cuisine: %s | Rating: %.1f | Open: %s%n",
                    restaurant.getRestaurantId(),
                    restaurant.getName(),
                    restaurant.getCuisine(),
                    restaurant.getRating(),
                    restaurant.isOpen() ? "Yes" : "No");
            showMenu(restaurant.getRestaurantId());
            System.out.println();
        }
    }

    public void showMenu(int restaurantId) {
        List<MenuItem> menu = service.getMenu(restaurantId);
        if (menu.isEmpty()) {
            System.out.println("  No menu items available for this restaurant.");
            return;
        }
        System.out.println("  Menu for restaurant " + restaurantId + ":");
        for (MenuItem item : menu) {
            System.out.printf("    %d - %s (%s) - %d - Available: %s - %s%n",
                    item.getItemId(), item.getName(), item.getCategory(), item.getPrice(),
                    item.isAvailable() ? "Yes" : "No", item.getPrepareTime());
        }
    }

    public void createCart() {
        com.foodDeliverySystem.foodModel.Cart cart = service.createCart();
        if (cart != null) {
            System.out.println("Cart created with id: " + cart.getCartId() + ". Use this cart id for later actions.");
        } else {
            System.out.println("Unable to create cart.");
        }
    }

    public void addItemToCart() {
        int cartId = readInt("Enter cart id: ");
        int restaurantId = readInt("Enter restaurant id: ");
        int itemId = readInt("Enter item id: ");
        if (service.addItemToCart(cartId, restaurantId, itemId)) {
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Unable to add item to cart. Check cart, restaurant, or item availability.");
        }
    }

    public void removeItemFromCart() {
        int cartId = readInt("Enter cart id: ");
        int itemId = readInt("Enter item id to remove: ");
        if (service.removeItemFromCart(cartId, itemId)) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Unable to remove item from cart.");
        }
    }

    public void viewCart() {
        int cartId = readInt("Enter cart id: ");
        List<MenuItem> items = service.getCartItems(cartId);
        if (items.isEmpty()) {
            System.out.println("Cart is empty or does not exist.");
            return;
        }
        System.out.println("Cart " + cartId + " items:");
        for (MenuItem item : items) {
            System.out.printf("  %d - %s - %d - %s%n", item.getItemId(), item.getName(), item.getPrice(), item.getPrepareTime());
        }
    }

    public void placeOrder() {
        int cartId = readInt("Enter cart id: ");
        int restaurantId = readInt("Enter restaurant id: ");
        com.foodDeliverySystem.foodModel.Order order = service.placeOrder(cartId, restaurantId);
        if (order != null) {
            System.out.println("Order placed: " + order);
        } else {
            System.out.println("Unable to place order. Check cart contents and restaurant selection.");
        }
    }

    public void cancelOrder() {
        int orderId = readInt("Enter order id: ");
        if (service.cancelOrder(orderId)) {
            System.out.println("Order cancelled.");
        } else {
            System.out.println("Unable to cancel order.");
        }
    }

    public void acceptDelivery() {
        int partnerId = readInt("Enter delivery partner id: ");
        if (service.acceptDelivery(partnerId)) {
            System.out.println("Delivery partner accepted the delivery.");
        } else {
            System.out.println("Unable to accept delivery. Partner may already be busy.");
        }
    }

    public void markDelivered() {
        int partnerId = readInt("Enter delivery partner id: ");
        if (service.markDelivered(partnerId)) {
            System.out.println("Delivery marked as delivered.");
        } else {
            System.out.println("Unable to mark delivered. Partner may already be available.");
        }
    }

    public void submitRating() {
        int orderId = readInt("Enter order id: ");
        int restaurantRating = readInt("Enter restaurant rating (1-5): ");
        int deliveryRating = readInt("Enter delivery rating (1-5): ");
        System.out.print("Enter review: ");
        String review = scanner.nextLine();
        if (service.submitRating(orderId, restaurantRating, deliveryRating, review)) {
            System.out.println("Rating submitted.");
        } else {
            System.out.println("Unable to submit rating. Please ensure all inputs are valid.");
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void addMenuItem() {
        System.out.print("Enter RestaurantId: ");
        int restaurantId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter item id: ");
        int itemId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        int price = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Is available (true/false): ");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine().trim());
        System.out.print("Enter prepare time: ");
        String prepareTime = scanner.nextLine();
        MenuItem item = new MenuItem(itemId, name, price, category, isAvailable, prepareTime);
        if (service.addMenuItem(restaurantId, item)) {
            System.out.println("Menu item added.");
        } else {
            System.out.println("Unable to add menu item.");
        }
    }

    public void removeMenuItem() {
        System.out.print("Enter RestaurantId: ");
        int restaurantId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter item id to remove: ");
        int itemId = Integer.parseInt(scanner.nextLine().trim());
        if (service.removeMenuItem(restaurantId, itemId)) {
            System.out.println("Menu item removed.");
        } else {
            System.out.println("Unable to remove menu item.");
        }
    }

    public void updatePriceForItem(){
        System.out.print("Enter the RestaurantId: ");
        int restaurantId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter the ItemId: ");
        int itemId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter the price for the item: ");
        int price = Integer.parseInt(scanner.nextLine().trim());
        boolean result = service.updatePriceForItem(restaurantId, itemId, price);
        if(result) {
            System.out.println("Updated successfully.");
        } else {
            System.out.println("Not updated. Please check the inputs.");
        }
    }
}
