package services.impl;

import models.products.Shippable;
import services.ShippingService;

import java.util.Map;

public class ShippingProcess implements ShippingService {

    @Override
    public void ship(Map<Shippable, Integer> shippedItems) {
        if ( !ShippedItemsExist(shippedItems) ) return;
        System.out.println("** Shipment notice **");

        double totalWeight = 0.0;
        for (Shippable product : shippedItems.keySet()) {
            int quantity = shippedItems.get(product);
            double weight = product.getWeight() * quantity;
            totalWeight += weight;
            String name = product.getName();
            System.out.println(quantity + "x " + name + " " + weight + "g");
        }
        System.out.println("Total package weight " + totalWeight / 1000 + "kg" + "\n");
    }

    @Override
    public double getShippingFees(Map<Shippable, Integer> shippedItems) {
        double shippingFees;
        if (!ShippedItemsExist(shippedItems)) shippingFees = 0;
        else shippingFees = 30.0;
        return shippingFees;
    }

    private boolean ShippedItemsExist(Map<Shippable, Integer> shippedItems) {
        return !shippedItems.isEmpty();
    }
}
