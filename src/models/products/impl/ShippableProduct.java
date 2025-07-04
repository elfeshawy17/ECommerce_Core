package models.products.impl;

import models.products.BaseProduct;
import models.products.Shippable;

public class ShippableProduct extends BaseProduct implements Shippable {

    private final double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isAvailable(int amount) {
        return quantity >= amount;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
