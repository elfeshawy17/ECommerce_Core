package services.impl;

import models.cart.Cart;
import models.products.Product;
import services.CheckoutService;
import services.ShippingService;

public class CheckoutProcess implements CheckoutService {

    private final ShippingService shippingService ;

    public CheckoutProcess(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    public void checkout(Cart cart) throws IllegalStateException {
        checkCart(cart);
        double ShippingFees = shippingService.getShippingFees(cart.getShippedItems());
        double totalAmount = cart.getSubTotal() + ShippingFees;
        cart.getCustomer().reduceBalance(totalAmount);
        reduceProductsQuantity(cart);
        shippingService.ship(cart.getShippedItems());
        printReceipt(cart, ShippingFees, totalAmount);
    }

    private void reduceProductsQuantity(Cart cart) {
        for (Product product : cart.getCart().keySet()) {
            int quantity = cart.getCart().get(product);
            product.reduceQuantity(quantity);
        }
    }

    private void printReceipt(Cart cart, double ShippingFees, double totalAmount) {
        for (Product product : cart.getCart().keySet()) {
            int quantity = cart.getCart().get(product);
            double price = product.getPrice() * quantity;
            System.out.println(quantity + "x " + product.getName() + " " + price);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + cart.getSubTotal());
        if (ShippingFees > 0) System.out.println("Shipping " + ShippingFees);

        System.out.println("Amount " + totalAmount);
    }

    private void checkCart(Cart cart) throws IllegalStateException {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty.");
    }
}
