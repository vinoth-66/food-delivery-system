package com.foodDeliverySystem.foodModel;

import java.util.*;

public class Cart {

    private int cartId;
    private int customerId;
    private List<MenuItem> items;
    private String promoCode;

    public Cart(int cartId, int customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public int getCartId() {
        return cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getPromoCode() {
        return promoCode;
    }

    public boolean addItem(MenuItem item) {
        if (item == null) {
            return false;
        }
        return items.add(item);
    }

    public boolean removeItem(int itemId) {
        return items.removeIf(menuItem -> menuItem.getItemId() == itemId);
    }

    public void clearItems() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customerId=" + customerId +
                ", items=" + items +
                ", promoCode='" + promoCode + '\'' +
                '}';
    }
}
