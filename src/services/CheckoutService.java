package services;

import models.cart.Cart;
import models.customer.Customer;

public interface CheckoutService {
    void checkout(Cart cart);
}
