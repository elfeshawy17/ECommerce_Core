package models.products.impl;

import models.products.BaseProduct;
import models.products.Expirable;

import java.time.LocalDate;

public class ExpirableProduct extends BaseProduct implements Expirable {

    private final LocalDate expireDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expireDate) {
        super(name, price, quantity);
        this.expireDate = expireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expireDate);
    }

    @Override
    public boolean isAvailable(int amount) {
        return quantity >= amount && !isExpired();
    }
}
