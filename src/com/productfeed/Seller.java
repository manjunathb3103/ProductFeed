package com.productfeed;

import java.util.ArrayList;

public class Seller {
    int id;
    ArrayList<Product> products;
    ArrayList<Customer> customers;

    public Seller(int id) {
        products = new ArrayList <Product> ();
        customers = new ArrayList<Customer>();
        this.id = id;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Customer> getSubscribedCustomers() {
        return customers;
    }
    
    public void addProducttoSeller(Product product)
    {
    	this.products.add(product);
    }
}
