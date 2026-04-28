package com.foodDeliverySystem.foodRepo;

import com.foodDeliverySystem.foodModel.Cart;
import com.foodDeliverySystem.foodModel.DeliveryPartner;
import com.foodDeliverySystem.foodModel.MenuItem;
import com.foodDeliverySystem.foodModel.Order;
import com.foodDeliverySystem.foodModel.Rating;
import com.foodDeliverySystem.foodModel.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository {
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<Cart> carts = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<DeliveryPartner> deliveryPartners = new ArrayList<>();
    private List<Rating> ratings = new ArrayList<>();

    public InMemoryRepository() {
        initializeSampleData();
        initializeDeliveryPartners();
    }

    private void initializeSampleData() {
        List<MenuItem> menu1 = new ArrayList<>();
        menu1.add(new MenuItem(1, "Dosa", 60, "Tamil Nadu", true, "15 mins"));
        menu1.add(new MenuItem(2, "Idly", 10, "Tamil Nadu", true, "25 mins"));
        menu1.add(new MenuItem(3, "Poori", 15, "Tamil Nadu", true, "20 mins"));
        menu1.add(new MenuItem(4, "Pongal", 30, "Tamil Nadu", true, "25 mins"));

        Restaurant restaurant1 = new Restaurant(101, "Mess", "Multi-cuisine", menu1, 4.5f, true);
        restaurants.add(restaurant1);

        List<MenuItem> menu2 = new ArrayList<>();
        menu2.add(new MenuItem(1, "Non", 80, "Tamil Nadu", true, "15 mins"));
        menu2.add(new MenuItem(2, "Grill", 200, "Tamil Nadu", true, "25 mins"));

        Restaurant restaurant2 = new Restaurant(102, "Mess", "Multi-cuisine", menu2, 4.5f, true);
        restaurants.add(restaurant2);
    }

    private void initializeDeliveryPartners() {
        deliveryPartners.add(new DeliveryPartner(1, "Zoro", 1234567890, "Tenkasi", true, 4));
        deliveryPartners.add(new DeliveryPartner(2, "Itachi",2134567890, "Tirunelveli", true, 5));
    }

    public Restaurant findRestaurantById(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }

    public Cart findCartById(int cartId) {
        for (Cart cart : carts) {
            if (cart.getCartId() == cartId) {
                return cart;
            }
        }
        return null;
    }

    public Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public DeliveryPartner findDeliveryPartnerById(int partnerId) {
        for (DeliveryPartner partner : deliveryPartners) {
            if (partner.getPartnerId() == partnerId) {
                return partner;
            }
        }
        return null;
    }

    public Rating findRatingById(int ratingId) {
        for (Rating rating : ratings) {
            if (rating.getRatingId() == ratingId) {
                return rating;
            }
        }
        return null;
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<DeliveryPartner> getDeliveryPartners() {
        return deliveryPartners;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public int generateNextOrderId() {
        return orders.size() + 1;
    }

    public int generateNextCartId() {
        return carts.size() + 1;
    }

    public int generateNextRatingId() {
        return ratings.size() + 1;
    }
}
