package com.foodDeliverySystem.foodModel;

import java.util.*;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String cuisine;
    private List<MenuItem> menu;
    private float rating;
    private boolean isOpen;

    public Restaurant(int restaurantId, String name, String cuisine, List<MenuItem> menu, float rating, boolean isOpen) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisine = cuisine;
        this.menu = menu;
        this.rating = rating;
        this.isOpen = isOpen;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public float getRating() {
        return rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean addMenuItem(MenuItem item) {
        if (item != null) {
            menu.add(item);
            return true;
        }
        return false;
    }

    public boolean removeItem(int itemId) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getItemId() == itemId) {
                menu.remove(i);
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", menu=" + menu +
                ", rating=" + rating +
                ", isOpen=" + isOpen +
                '}';
    }
}
