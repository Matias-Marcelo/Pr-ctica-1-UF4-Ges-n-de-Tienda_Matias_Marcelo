// The Product class represents a product in the store
package model;

// Product class with properties id, name, publicPrice, wholesalerPrice, available, stock, and totalProducts
public class Product {
    private int id;
    private String name;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available;
    private int stock;
    private static int totalProducts;

    // Constant representing the expiration rate for products
    static double EXPIRATION_RATE = 0.40;

    // Constructor to initialize a Product object with name, wholesalerPrice, availability, stock, and publicPrice
    public Product(String name, Amount wholesalerPrice, boolean available, int stock, Amount publicPrice) {
        super();
        this.id = totalProducts + 1;
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.available = available;
        this.stock = stock;
        this.publicPrice = publicPrice;
        totalProducts++;
    }

    // Getter for the id property
    public int getId() {
        return id;
    }

    // Setter for the id property
    public void setId(int id) {
        this.id = id;
    }

    // Getter for the name property
    public String getName() {
        return name;
    }

    // Setter for the name property
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the publicPrice property
    public Amount getPublicPrice() {
        return publicPrice;
    }

    // Setter for the publicPrice property
    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }

    // Getter for the wholesalerPrice property
    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    // Setter for the wholesalerPrice property
    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
    }

    // Getter for the available property
    public boolean isAvailable() {
        return available;
    }

    // Setter for the available property
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Getter for the stock property
    public int getStock() {
        return stock;
    }

    // Setter for the stock property
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter for the totalProducts property
    public static int getTotalProducts() {
        return totalProducts;
    }

    // Setter for the totalProducts property
    public static void setTotalProducts(int totalProducts) {
        Product.totalProducts = totalProducts;
    }

    // Method to simulate expiration and reduce the publicPrice
    public double expire() {
        
    	this.publicPrice.setValue(this.getPublicPrice().getValue() * EXPIRATION_RATE);
    	return this.getPublicPrice().getValue();
    }

    // Override toString method to provide a string representation of the Product object
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice="
                + wholesalerPrice + ", available=" + available + ", stock=" + stock + "\n]";
    }

	
}