import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show products");
            System.out.println("2. Add product");
            System.out.println("3. Buy product");
            System.out.println("0. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 0) break;

            if (choice == 1) showProducts();
            if (choice == 2) addProduct(sc);
            if (choice == 3) buyProduct(sc);
        }
    }

    // ===== SHOW =====
    static void showProducts() throws Exception {
        Connection c = DB.connect();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getDouble("price") + " | stock=" +
                            rs.getInt("stock")
            );
        }
        c.close();
    }

    // ===== ADD =====
    static void addProduct(Scanner sc) throws Exception {
        Connection c = DB.connect();

        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();

        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO products(name, price, stock) VALUES (?, ?, ?)"
        );
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, stock);
        ps.executeUpdate();

        System.out.println("Product added");
        c.close();
    }

    // ===== BUSINESS LOGIC =====
    static void buyProduct(Scanner sc) throws Exception {
        Connection c = DB.connect();

        System.out.print("Product id: ");
        int id = sc.nextInt();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        PreparedStatement check = c.prepareStatement(
                "SELECT stock FROM products WHERE id = ?"
        );
        check.setInt(1, id);
        ResultSet rs = check.executeQuery();

        if (!rs.next()) {
            System.out.println("Product not found");
            return;
        }

        int stock = rs.getInt("stock");
        if (stock < qty) {
            System.out.println("Not enough stock");
            return;
        }

        PreparedStatement update = c.prepareStatement(
                "UPDATE products SET stock = stock - ? WHERE id = ?"
        );
        update.setInt(1, qty);
        update.setInt(2, id);
        update.executeUpdate();

        System.out.println("Purchase successful");
        c.close();
    }
}