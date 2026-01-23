package controller;

import repository.ShopController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ShopController shop = new ShopController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Show products");
            System.out.println("2. Buy product");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            if (choice == 0) break;

            if (choice == 1) {
                shop.showProducts();
            }

            if (choice == 2) {
                System.out.print("Enter product id: ");
                int id = sc.nextInt();
                System.out.print("Enter quantity: ");
                int q = sc.nextInt();
                shop.buyProduct(id, q);
            }
        }
    }
}