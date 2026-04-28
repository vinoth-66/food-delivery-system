package com.foodDeliverySystem.foodModel;

public class Rating {
    private int ratingId;
    private int orderId;
    private int restaurantRating;
    private int deliveryRating;
    private String review;

    public Rating(int ratingId, int orderId, int restaurantRating, int deliveryRating, String review) {
        this.ratingId = ratingId;
        this.orderId = orderId;
        this.restaurantRating = restaurantRating;
        this.deliveryRating = deliveryRating;
        this.review = review;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRestaurantRating() {
        return restaurantRating;
    }

    public int getDeliveryRating() {
        return deliveryRating;
    }

    public String getReview() {
        return review;
    }

    public boolean submit() {
        return validate();
    }

    public boolean validate() {
        return restaurantRating >= 1 && restaurantRating <= 5
                && deliveryRating >= 1 && deliveryRating <= 5
                && review != null && !review.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", orderId=" + orderId +
                ", restaurantRating=" + restaurantRating +
                ", deliveryRating=" + deliveryRating +
                ", review='" + review + '\'' +
                '}';
    }
}
