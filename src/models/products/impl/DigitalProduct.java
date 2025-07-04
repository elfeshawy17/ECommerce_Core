package models.products.impl;

import models.products.BaseProduct;

public class DigitalProduct extends BaseProduct {

    public DigitalProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isAvailable(int amount) {
        return quantity >= amount;
    }
}
