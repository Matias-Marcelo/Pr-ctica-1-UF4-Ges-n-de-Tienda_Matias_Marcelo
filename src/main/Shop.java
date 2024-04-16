package main;

import model.Amount;
import model.Client;
import model.Employee;
import model.Product;
import model.Sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.time.format.DateTimeFormatter ;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;


// The main Shop class that represents the store
public class Shop {
	
    // Initial cash amount, inventory array, and sales array
    private double cash = 100.00;
    private ArrayList<Product>inventory;
    private int numberProducts;
    private ArrayList<Sale>sales;
    //private Sale[] sales;
    int salesIndex = 0;

    // Constant representing the tax rate
    final static double TAX_RATE = 1.04;
    

    // Constructor initializes the shop with an initial cash amount, inventory, and sales array
    public Shop() {
        cash = 100.0;
    //    sales = new Sale[10];
        sales = new ArrayList<Sale>();
        this.inventory = new ArrayList<Product>();
         
    }
    
    // Main method where the program execution begins
    public static void main(String[] args) throws IOException {
        // Create an instance of the Shop class
    	

    	
        Shop shop = new Shop();

        // Load initial inventory to the shop
        shop.loadInventory();

        // Set up a simple console menu for user interaction
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        boolean exit = false;
        //Call method initSession
        shop.initSession();
        do {
            // Displaying the main menu to the user
    		System.out.println("\n");
			System.out.println("===========================");
			System.out.println("Menu principal miTienda.com");
			System.out.println("===========================");
			System.out.println("1) Contar caja");
			System.out.println("2) Añadir producto");
			System.out.println("3) Añadir stock");
			System.out.println("4) Marcar producto proxima caducidad");
			System.out.println("5) Ver inventario");
			System.out.println("6) Venta");
			System.out.println("7) Ver ventas");
			System.out.println("8) Eliminar producto");
			System.out.println("9) Ver ventas Totales");
			System.out.println("10) Salir programa");
			System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            // Perform actions based on the user's menu choice
            switch (option) {
                case 1:
                    shop.showCash();  // Display the current amount of cash
                    break;

                case 2:
                    shop.addProduct();  // Add a new product to the inventory
                    break;

                case 3:
                    shop.addStock();  // Add stock for a specific product
                    break;

                case 4:
                    shop.setExpired();  // Mark a product as expiring soon
                    break;

                case 5:
                    shop.showInventory();  // Display the current inventory
                    break;

                case 6:
                    shop.sale();  // Make a sale to a client
                    break;

                case 7:
                    shop.showSales();  // Display the list of sales
                    break;
                    
                case 8:
                	
                	shop.deleteProduct(); //Delete product of inventory
                	break;
                
                case 9:
                    shop.totalSales();  // View total sales
                    break;

                case 10:
                    exit = true;  // Exit the program
                    break;
            }

        } while (!exit);  // Continue the loop until the user chooses to exit
    }

    /**
     * Load initial inventory to the shop with predefined product details.
     */
    
    
    public void initSession() {
        Employee employee = new Employee(); //Create object employee of class employee
        int numero;
        String password;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        //Login logic asks for password and id
        do {
        	
            System.out.println("Introduce número de empleado:");
            numero = scanner.nextInt();
            System.out.println("Introduce la contraseña:");
            password = scanner.next();
            //When the employee data is false, continue iterating. This is validated by calling the login method with the employee object
        } while (!employee.login(numero, password));
        

        System.out.println("Login correcto");
        // Aquí puedes agregar cualquier otra lógica que quieras ejecutar después de iniciar sesión correctamente
    }
    
    public void loadInventory() {
    	
    	try {
  	      File files = new File("files/inputinventory.txt"); // File object to read from inputinventory.txt file
  	      Scanner scanner = new Scanner(files); // Scanner object to read from the file
  	      while (scanner.hasNextLine()) {
  	        String data = scanner.nextLine(); // Read a line from the file
  	        String[] line1 = data.split(";"); // Split the line based on semicolon
  	        String nombre = "";
  	        double wholesalerPrice = 0.0;
  	        int stock = 0;
  	        double publicPrice = 0.0;
  	        //System.out.println(line1);
  	        for (int i = 0; i < line1.length; i++) {
  	        	String[] line2 = line1[i].split(":"); // Split each part of the line based on colon

  	        	switch(i) {
  	        	case 0:
      	        	nombre = line2[1]; // Assign name from the split line
      	        	break;
  	        	case 1:
      	        	wholesalerPrice = Double.parseDouble(line2[1]); // Assign wholesaler price from the split line
      	        	break;
  	        	case 2:
      	        	stock = Integer.parseInt(line2[1]); // Assign stock from the split line
      	        	break;
      	        		
  	        	}
				}
  	        Amount amount = new Amount(wholesalerPrice);
  	        addProduct(new Product(nombre, amount, true, stock, new Amount(amount.getValue() * 2))); // Add product to inventory

  	      }
  	      scanner.close(); // Close the scanner
  	    } catch (FileNotFoundException e) { // Catch FileNotFoundException
  	      System.out.println("An error occurred."); // Print error message
  	      e.printStackTrace(); // Print stack trace
  	    }
    	
//        addProduct(new Product("Manzana", 10.00, true, 10, 20.00));
//        addProduct(new Product("Pera", 20.00, true, 20, 40.00));
//        addProduct(new Product("Hamburguesa", 30.00, true, 30, 60.00));
//        addProduct(new Product("Fresa", 5.00, true, 20, 10.00));
    
    }											

    /**
     * Display the current total cash of the shop.
     */
    private void showCash() {
        System.out.println("Current cash: " + cash);
    }

    /**
     * Add a new product to the inventory, getting data from the console.
     */
    public void addProduct() {
        // Check if the inventory is full before adding a new product
        if (isInventoryFull()) {
            System.out.println("Cannot add more products. Maximum capacity reached.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Wholesaler Price: ");
        double wholesalerPrice = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        Amount amount = new Amount(wholesalerPrice);
        addProduct(new Product(name, amount, true, stock, new Amount(amount.getValue() * 2)));
    }

    /**
     * Add stock for a specific product.
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a product name: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            // Ask for the stock to be added
            System.out.print("Enter the quantity to add: ");
            int stock = scanner.nextInt();
            
            // Update the stock of the product
            int updatedStock = product.getStock() + stock;
            product.setStock(updatedStock);
            System.out.println("The stock of the product " + name + " has been updated to " + product.getStock());
        } else {
            System.out.println("Product with name " + name + " not found.");
        }
    }

    /**
     * Set a product as expired, reducing its public price.
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a product name: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            // Calculate the new public price after marking as expired
            double result = product.getPublicPrice().getValue() - product.expire();
            Amount amount = new Amount(result);
            product.setPublicPrice(amount);
            System.out.println("The public price of " + name + " has been modified to " + amount);
            
        }
    }

    /**
     * Display all products in the inventory.
     */
    public void showInventory() {
        System.out.println("Current content of the store:"); // Print message
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product.toString()); // Print product details
                
            }
        }
    }

    /**
     * Make a sale of products to a client.
     */
    public void sale() {
        // Ask for client name
        Scanner sc = new Scanner(System.in);
        System.out.println("Make a sale, enter client name");
        String client_sale = sc.nextLine();
        ArrayList<Product> products = new ArrayList<Product>();
        Client client = new Client(client_sale);
        
        int counter = 0;
        double totalAmount = 0.0;
        String name = "";
        String Data = "";
        
        // Loop until the user enters "0"
        while (!name.equals("0")) {
            System.out.println("Enter the product name, type 0 to finish:");
            name = sc.nextLine();

            // Display the list of available products at the public price
            System.out.println("List of available products at the public price: ");
            for (Product product : inventory) {
                if (product != null) {
                    System.out.println(product.getName());
                }
            }

            if (name.equals("0")) {
                break;
            }

            Product product = findProduct(name);
            boolean productAvailable = false;

            // Check if the product is available and has stock
            if (product != null && product.isAvailable()) {
                productAvailable = true;
                totalAmount += product.getPublicPrice().getValue();
                product.setStock(product.getStock() - 1);

                if (product.getStock() == 0) {
                    product.setAvailable(false);
                }
                System.out.println("Product added successfully");
                
                products.add(product);
                
               // products[counter] = product;
               // counter++;
            }

            if (!productAvailable) {
                System.out.println("Product not found or out of stock");
            }
        }

        
        // Show total cost and update cash
        totalAmount = totalAmount * TAX_RATE;
        cash += totalAmount;
        System.out.println("Venta realizada con éxito, total: " + totalAmount);
        //Createan object amount with the value of totalAmount
        Amount amount = new Amount(totalAmount);
       
        //Declare a boolean variabe
        boolean test;
        //Give the boolean variable the result of method pay using the object client.¡
        test = client.pay(amount);

        //IF boolean test is false then declare variable deber (double) and give the value of what the client owes.
        if(!test) {
        	double deber = client.getBalance().getValue() - amount.getValue();
        	System.out.println("Debes: "+deber);
        	
        }

        LocalDateTime date = LocalDateTime.now(); // Create a date object
        // Record the sale
		sales.add(new Sale(client, products, new Amount(amount.getValue()), date));
        //sales[salesIndex] = new Sale(client, products, totalAmount);
        //salesIndex++;
    }

    /**
     * Display all sales.
     */
    private void showSales() throws IOException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedData = date.format(formatData);
        Scanner scanner = new Scanner(System.in);
        LocalDate data = LocalDate.now();
        
        // Prompt the user to choose whether to create a file or not
        System.out.println("Do you want to create a file?\n" +
        "1) Yes\n"+
                "2) No\n");
        int options = scanner.nextInt();
        switch(options) {
            case 1:
                try {
                    // Create a new file with the current date in the filename
                    File files = new File("files/sales_"+data+".txt");
                    FileWriter write = new FileWriter(files,true);
                    PrintWriter print = new PrintWriter(write);
                    int counterSale = 1;
                    // Iterate through each sale and write its details to the file
                    for (Sale sale : sales) {
                        // Write the client's name and the date and time of the sale
                        StringBuilder line = new StringBuilder(counterSale+";Client: "+sale.getClient()+"; Date="+formattedData+"\n");
                        print.write(line.toString());
                        StringBuilder product = new StringBuilder();
                        
                        // Write the list of products purchased with their prices
                        for (Product products : sale.getProducts()) {
                            product.append(";"+products.getName()+","+products.getPublicPrice());

                        }
                        StringBuilder secondLine = new StringBuilder(counterSale+";"+"Products= "+product+"€");


                        print.write(secondLine.toString());
                        print.write("\n");
                        
                        // Write the total amount of the sale
                        StringBuilder ThirdLine = new StringBuilder(counterSale+";Amount: "+sale.getAmount()+"€");

                        print.write(ThirdLine.toString());
                        print.write("\n");

                    }

                    print.close();
                    write.close();
                }catch(IOException e) {
                    // Handle errors if file creation or writing fails
                    System.out.println("ERROR");
                    e.printStackTrace();
                }
                break;
            case 2:
                // Display the list of sales on the console
                System.out.println("List of sales:");
                for (Sale sale : sales) {
                    if (sale != null) {
                        System.out.println(sale.toString());
                        System.out.println(" ");
                    }
                }
                break;
            default:
                // Handle invalid input
                System.out.println("Invalid option");
                break;
        }
    }
    	

 
    
    
    private void deleteProduct() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Productos para borrar: ");
        String name = scanner.next();
        
        // Display the list of available products for reference
        System.out.println("Lista de productos disponibles:");
        for (Product product : inventory) {
            if (product != null) { 
                System.out.println(product.getName());
            }
        }

        // Find the product in the inventory
        Product product = findProduct(name);

        // Check if the product exists and is available
        if (product != null && product.isAvailable()) {
            // Set its availability to false, effectively removing it from the inventory
        	boolean productAvaible = true;
        	inventory.remove(product);
        	 // If no more stock, set as not available to sale
            if (product.getStock() == 0) {
                product.setAvailable(false);
            }
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Producto no encontrado o no disponible.");
        }
    }

    /**
     * Calculate and display total sales.
     */
    private void totalSales() {
        double totalAmount = 0.0;
        System.out.println("View total sales: ");
       Amount amount = new Amount(totalAmount);
        for (Sale sale : sales) {
            if (sale != null) {
                totalAmount = totalAmount + sale.getAmount().getValue();
            }
        }
        System.out.println("Total sales: " + totalAmount);
    }

    /**
     * Add a product to the inventory.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        if (isInventoryFull()) {
            System.out.println("Cannot add more products. Maximum capacity reached.");
            return;
        }

        inventory.add(product);
        
        
    }

    /**
     * Check if the inventory is full.
     *
     * @return True if the inventory is full, false otherwise.
     */
    public boolean isInventoryFull() {
        return numberProducts == 10;
    }

    /**
     * Find a product by name in the inventory.
     *
     * @param name The name of the product to find.
     * @return The found product or null if not found.
     */
    public Product findProduct(String name) {
    	
    	
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i)!= null && inventory.get(i).getName().equalsIgnoreCase(name)) {
                return inventory.get(i);
            }
        }
    	
//    	for (Iterator iterator = inventory.iterator();iterator.hasNext();) {
//    		
//    		if(inventory.iterator() != null && inventory.iterator().equals(name)) {
//    			
//    			return (Product) inventory.iterator();
//    			
//    			
//    			
//    			
//    		}
//    		
//    	}
    	
    	
        return null;
    }
    
    
    
}