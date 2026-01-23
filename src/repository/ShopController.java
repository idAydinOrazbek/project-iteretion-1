package repository;

import model.Product;
import java.util.List;

public class ShopController {

    private ProductRepository repo = new ProductRepository();

    public void showProducts() {
        for (Product p : repo.findAll()) {
            System.out.println(
                    p.getId() + ". " + p.getName() +
                            " | price: " + p.getPrice() +
                            " | stock: " + p.getStock()
            );
        }
    }

    public void buyProduct(int id, int quantity) {
        Product p = repo.findById(id);

        if (p == null) {
            System.out.println("Product not found");
            return;
        }

        if (p.getStock() < quantity) {
            System.out.println("Not enough stock");
            return;
        }

        p.decreaseStock(quantity);
        System.out.println("You bought " + quantity + " " + p.getName());
    }
}