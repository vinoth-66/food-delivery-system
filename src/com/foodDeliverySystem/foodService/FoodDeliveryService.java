package com.foodDeliverySystem.foodService;

import com.foodDeliverySystem.foodModel.Cart;
import com.foodDeliverySystem.foodModel.DeliveryPartner;
import com.foodDeliverySystem.foodModel.MenuItem;
import com.foodDeliverySystem.foodModel.Order;
import com.foodDeliverySystem.foodModel.Rating;
import com.foodDeliverySystem.foodModel.Restaurant;
import com.foodDeliverySystem.foodRepo.InMemoryRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodDeliveryService {
    private InMemoryRepository repository;

    public FoodDeliveryService(InMemoryRepository repository) {
        this.repository = repository;
    }

    public List<MenuItem> getMenu(int restaurantId) {
        Restaurant restaurant = repository.findRestaurantById(restaurantId);
        if (restaurant != null) {
            return restaurant.getMenu();
        } else {
            return Collections.emptyList();
        }
    }

    public boolean addMenuItem(int restaurantId, MenuItem item) {
        Restaurant restaurant = repository.findRestaurantById(restaurantId);
        if (restaurant != null) {
            return restaurant.addMenuItem(item);
        } else {
            return false;
        }
    }

    public boolean removeMenuItem(int restaurantId, int itemId) {
        Restaurant restaurant = repository.findRestaurantById(restaurantId);
        if (restaurant != null) {
            return restaurant.removeItem(itemId);
        } else {
            return false;
        }
    }

    public List<Restaurant> getRestaurents() {
        return repository.getRestaurants();
    }

    public boolean updatePriceForItem(int restaurantId, int itemId, int price) {
        if (price <= 0) {
            return false;
        }

        Restaurant restaurant = repository.findRestaurantById(restaurantId);
        if (restaurant == null) {
            return false;
        }

        for (MenuItem item : restaurant.getMenu()) {
            if (item.getItemId() == itemId) {
                item.updatePrice(price);
                return true;
            }
        }

        return false;
    }

    public Cart createCart() {
        int cartId = repository.generateNextCartId();
        Cart cart = new Cart(cartId, cartId);
        repository.addCart(cart);
        return cart;
    }

    public boolean addItemToCart(int cartId, int restaurantId, int itemId) {
        Cart cart = repository.findCartById(cartId);
        Restaurant restaurant = repository.findRestaurantById(restaurantId);
        if (cart == null || restaurant == null) {
            return false;
        }

        for (MenuItem item : restaurant.getMenu()) {
            if (item.getItemId() == itemId && item.isAvailable()) {
                return cart.addItem(item);
            }
        }

        return false;
    }

    public boolean removeItemFromCart(int cartId, int itemId) {
        Cart cart = repository.findCartById(cartId);
        return cart != null && cart.removeItem(itemId);
    }

    public List<MenuItem> getCartItems(int cartId) {
        Cart cart = repository.findCartById(cartId);
        if (cart == null) {
            return Collections.emptyList();
        }
        return cart.getItems();
    }

    public Order placeOrder(int cartId, int restaurantId) {
        Cart cart = repository.findCartById(cartId);
        Restaurant restaurant = repository.findRestaurantById(restaurantId);
        if (cart == null || restaurant == null) {
            return null;
        }

        List<MenuItem> items = cart.getItems();
        if (items.isEmpty()) {
            return null;
        }

        List<String> itemNames = new ArrayList<>();
        int total = 0;
        for (MenuItem item : items) {
            itemNames.add(item.getName());
            total += item.getPrice();
        }

        int customerId = cart.getCustomerId();
        Order order = new Order(repository.generateNextOrderId(), customerId, restaurantId, itemNames, "Placed", String.valueOf(total));
        repository.addOrder(order);
        cart.clearItems();
        return order;
    }

    public boolean cancelOrder(int orderId) {
        Order order = repository.findOrderById(orderId);
        return order != null && order.cancel();
    }

    public boolean acceptDelivery(int partnerId) {
        DeliveryPartner partner = repository.findDeliveryPartnerById(partnerId);
        return partner != null && partner.acceptDelivery();
    }

    public boolean markDelivered(int partnerId) {
        DeliveryPartner partner = repository.findDeliveryPartnerById(partnerId);
        return partner != null && partner.markDelivered();
    }

    public boolean submitRating(int orderId, int restaurantRating, int deliveryRating, String review) {
        if (repository.findOrderById(orderId) == null) {
            return false;
        }
        Rating rating = new Rating(repository.generateNextRatingId(), orderId, restaurantRating, deliveryRating, review);
        if (rating.submit()) {
            repository.addRating(rating);
            return true;
        }
        return false;
    }
}
