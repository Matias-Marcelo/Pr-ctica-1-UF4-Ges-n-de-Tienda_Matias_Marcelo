// The Sale class represents a transaction where a client purchases one or more products
package model;

import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Sale class with properties client, products (arrayList of products), and amount
public class Sale {
    Client client;
    ArrayList<Product> products = new ArrayList<Product>();
    LocalDateTime date;
    Amount amount;
    

    // Constructor to initialize a Sale object with client, ArrayList products, and amount
    public Sale(Client client, ArrayList<Product> products, Amount amount, LocalDateTime date) {
        super();
        this.client = client;
        this.products = products;
        this.amount = amount;
        this.date = date;
    }

    // Getter for the client property
    public Client getClient() {
        return client;
    }

    // Setter for the client property
    public void setClient(Client client) {
        this.client = client;
    }

    // Getter for the products property
    public ArrayList<Product> getProducts() {
        return products;
    }

    // Setter for the products property
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    // Getter for the amount property
    public Amount getAmount() {
        return amount;
    }

    // Setter for the amount property
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    

    // Override toString method to provide a string representation of the Sale object
    @Override
    public String toString() {
    	 DateTimeFormatter FormatData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
         String formattedDate = date.format(FormatData);
        return "Sale [client=" + client + ", products=" + products + ", amount=" + amount + ",Fecha y Hora "+formattedDate+ "]";
    }
}