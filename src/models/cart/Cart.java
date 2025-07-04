package models.cart;

import models.customer.Customer;
import models.products.Product;
import models.products.Shippable;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Customer customer;
    private Map<Product, Integer> cart;

    public Cart(Customer customer) {
        this.cart = new HashMap<>();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public void add(Product product, int quantity) throws IllegalArgumentException {
        if ( checkCartItemExist(product) ) {
            updateItemQuantity(product, quantity);
        } else {
            cart.put(product, quantity);
        }
    }

    private void updateItemQuantity(Product product, int quantity) throws IllegalArgumentException {
        int newQuantity = quantity + cart.get(product);
        checkAvailability(product, newQuantity);
        cart.put(product, newQuantity);
    }

    private void checkAvailability(Product product, int newQuantity) throws IllegalArgumentException {
        if ( !product.isAvailable(newQuantity) ) {
            throw new IllegalArgumentException("Not enough quantity available for " + product.getName());
        }
    }

    private boolean checkCartItemExist(Product product) {
        return cart.containsKey(product);
    }

    public double getSubTotal() {
        double subTotal = 0.0;
        for (Product product : cart.keySet()) {
            int quantity = cart.get(product);
            subTotal += product.getPrice() * quantity;
        }
        return subTotal;
    }

    public Map<Shippable, Integer> getShippedItems() {
        Map<Shippable, Integer> shippedItems = new HashMap<>();
        for (Product product : cart.keySet()) {
                if (product instanceof Shippable) {
                    int quantity = cart.get(product);
                    shippedItems.put((Shippable) product, quantity);
                }
        }
        return shippedItems;
    }
}
