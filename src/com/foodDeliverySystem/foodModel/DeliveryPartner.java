package com.foodDeliverySystem.foodModel;

public class DeliveryPartner {

    private int partnerId;
    private String name;
    private int phone;
    private String location;
    private boolean isAvailable;
    private int rating;

    public DeliveryPartner(int partnerId, String name, int phone, String location, boolean isAvailable, int rating) {
        this.partnerId = partnerId;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.isAvailable = isAvailable;
        this.rating = rating;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getRating() {
        return rating;
    }

    public boolean acceptDelivery() {
        if (!isAvailable) {
            return false;
        }
        isAvailable = false;
        return true;
    }

    public boolean markDelivered() {
        if (isAvailable) {
            return false;
        }
        isAvailable = true;
        return true;
    }

    @Override
    public String toString() {
        return "DeliveryPartner{" +
                "partnerId=" + partnerId +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", location='" + location + '\'' +
                ", isAvailable=" + isAvailable +
                ", rating=" + rating +
                '}';
    }
}
