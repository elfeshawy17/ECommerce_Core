package models.customer;

public class Customer {

    private final String id;
    private String name;
    private double balance;

    private static int counter = 1;

    public Customer(String name, double balance) {
        this.id = "C-" + counter++;
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void reduceBalance(double paidAmount) throws IllegalArgumentException{
        if ( !checkPaidAmount(paidAmount) ) {
            throw new IllegalArgumentException("Insufficient Balance.");
        }
        balance -= paidAmount;
    }

    private boolean checkPaidAmount(double paidAmount) {
        return paidAmount <= balance;
    }
}
