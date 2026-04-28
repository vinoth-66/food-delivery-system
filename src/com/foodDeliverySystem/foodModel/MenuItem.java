package com.foodDeliverySystem.foodModel;

public class MenuItem {

    private int itemId;
    private String name;
    private int price;
    private String category;
    private boolean isAvailable;
    private String prepareTime;

    public MenuItem(int itemId, String name, int price, String category, boolean isAvailable, String prepareTime) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
        this.prepareTime = prepareTime;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getPrepareTime() {
        return prepareTime;
    }

    public void updatePrice(int newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
        }
    }

    public void markUnavailable() {
        this.isAvailable = false;
    }

    public void markAvailable() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                ", prepareTime='" + prepareTime + '\'' +
                '}';
    }
}
