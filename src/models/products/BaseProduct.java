package models.products;

public abstract class BaseProduct implements Product {

    protected String name;
    protected double price;
    protected int quantity;

    public BaseProduct(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public abstract boolean isAvailable(int amount);

    @Override
    public void reduceQuantity(int amount) throws IllegalArgumentException {
        if (!isAvailable(amount)) {
            throw new IllegalArgumentException("It's not available.");
        }
        quantity -= amount;
    }
}
