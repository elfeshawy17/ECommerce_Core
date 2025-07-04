package services;

import models.products.Shippable;

import java.util.Map;

public interface ShippingService {
    void ship(Map<Shippable, Integer> shippedItems);
    double getShippingFees(Map<Shippable, Integer> shippedItems);
}
