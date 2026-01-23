package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // ✅ ВОТ ОН — ВНУТРИ КЛАССА
    public void decreaseStock(int amount) {
        if (amount <= 0) return;
        if (stock >= amount) {
            stock -= amount;
        } else {
            stock = 0;
        }
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + price + " | stock=" + stock;
    }
}