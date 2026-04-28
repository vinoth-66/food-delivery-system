package com.foodDeliverySystem.foodModel;

import java.util.*;

public class Order {

    private int orderId;
    private int customerId;
    private int restaurantId;
    private List<String> items;
    private String status;
    private String totalAmount;

    public Order(int orderId, int customerId, int restaurantId, List<String> items, String status, String totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public List<String> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public boolean place() {
        if ("Cancelled".equalsIgnoreCase(status) || "Delivered".equalsIgnoreCase(status)) {
            return false;
        }
        status = "Placed";
        return true;
    }

    public boolean cancel() {
        if ("Delivered".equalsIgnoreCase(status) || "Cancelled".equalsIgnoreCase(status)) {
            return false;
        }
        status = "Cancelled";
        return true;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", restaurantId=" + restaurantId +
                ", items=" + items +
                ", status='" + status + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
