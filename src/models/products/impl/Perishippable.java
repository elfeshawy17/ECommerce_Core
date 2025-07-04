package models.products.impl;

import models.products.BaseProduct;
import models.products.Expirable;
import models.products.Shippable;

import java.time.LocalDate;

public class Perishippable extends BaseProduct implements Expirable, Shippable {

    private final double weight;
    private final LocalDate expireDate;

    public Perishippable(String name, double price, int quantity, double weight, LocalDate expireDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expireDate = expireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Override
    public boolean isAvailable(int amount) {
        return quantity >= amount && !isExpired();
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expireDate);
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
