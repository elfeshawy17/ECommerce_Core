import models.cart.Cart;
import models.customer.Customer;
import models.products.Product;
import models.products.impl.DigitalProduct;
import models.products.impl.Perishippable;
import models.products.impl.ShippableProduct;
import services.CheckoutService;
import services.impl.CheckoutProcess;
import services.impl.ShippingProcess;

import java.time.LocalDate;

public class Main {

    public static void main (String[] args) {

        LocalDate cheeseExpireDate = LocalDate.of(2025, 10, 30);
        LocalDate biscuitsExpireDate = LocalDate.of(2025, 12, 5);

        Product cheese = new Perishippable("cheese", 100, 50, 200, cheeseExpireDate);
        Product biscuits = new Perishippable("Biscuits", 150, 20, 700, biscuitsExpireDate);
        Product tv = new ShippableProduct("TV", 7000, 10, 5000);
        Product scratchCard = new DigitalProduct("scratchCard", 50, 200);

        Customer customer = new Customer("Mohamed", 50000);
        Cart cart = new Cart(customer);

        CheckoutService checkoutService = new CheckoutProcess(new ShippingProcess());

        try {
            cart.add(cheese, 2);
            cart.add(tv, 3);
            cart.add(scratchCard, 1);
            cart.add(biscuits, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Adding items failed: " + e.getMessage());
        }

        try {
            checkoutService.checkout(cart);
        } catch (IllegalStateException e) {
            System.out.println("Checkout Failed: " + e.getMessage());
        }

    }

}