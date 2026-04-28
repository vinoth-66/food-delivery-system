package com.foodDeliverySystem;

import com.foodDeliverySystem.foodController.FoodDeliveryController;
import com.foodDeliverySystem.foodRepo.InMemoryRepository;
import com.foodDeliverySystem.foodService.FoodDeliveryService;

public class MainApplication {
    public static void main(String[] args) {
        InMemoryRepository repo=new InMemoryRepository();
        FoodDeliveryService service=new FoodDeliveryService(repo);
        FoodDeliveryController controller = new FoodDeliveryController(service);
        controller.start();
    }
}
