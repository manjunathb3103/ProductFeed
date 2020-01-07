package com.productfeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Handlers {

    public static class Customers {
        private static HashMap<Integer,Customer> customers = new HashMap<Integer,Customer>();

        public static Customer getObject(int id) {
            return customers.get(id);
        }

        public static void createCustomer(int id) {
            Customer customer = new Customer(id);
            customers.put(id, customer);
        }
        
        public ArrayList<Integer> listCustomers()
        {
        	ArrayList<Integer> customerList = new ArrayList<Integer>();	
        	Iterator customerIterator = customers.entrySet().iterator();
        	while (customerIterator.hasNext())
        	{
        		Map.Entry customer = (Map.Entry)customerIterator.next(); 
        		customerList.add((int)customer.getKey());
        	}
        	return customerList;
        }
    }

    public static class Sellers {
        private static HashMap<Integer,Seller> sellers =new HashMap<Integer,Seller>();

        public static Seller getObject(int id) {
            return sellers.get(id);
        }

        public static void createSeller(int id) {
            Seller seller = new Seller(id);
            sellers.put(id, seller);
        }

        public static void deleteSeller(int id) {
            Seller seller = getObject(id);
            for(Customer customer : seller.getSubscribedCustomers()){
                customer.unsubscribe(seller);
            }
            for(Product product : seller.getProducts()){
                Products.delete(product.id);
            }
            sellers.remove(new Integer(id));
        }

        public static void addProduct(int sid, int pid, String name, float price) {
        	Seller seller = getObject(sid);
            Product product = new Product(pid, name, price);
            Products.add(pid, product);
            seller.addProducttoSeller(product);
        }
        
        public static ArrayList<Integer> listSellers()
        {
        	ArrayList<Integer> sellerList = new ArrayList<Integer>();	
        	Iterator sellerIterator = sellers.entrySet().iterator();
        	while (sellerIterator.hasNext())
        	{
        		Map.Entry seller = (Map.Entry)sellerIterator.next(); 
        		sellerList.add((int)seller.getKey());
        	}
        	return sellerList;
        }
    }

    public static class Products {
        private static HashMap<Integer,Product> products =new HashMap<Integer,Product>();

        public static Product getObject(int id) {
            return products.get(id);
        }

        public static void add(int id, Product product) {
            products.put(id, product);
        }

        public static void delete(int id) {
            products.remove(new Integer(id));
        }
        
        public static ArrayList<Integer> listProducts()
        {
        	ArrayList<Integer> productList = new ArrayList<Integer>();	
        	Iterator productIterator = products.entrySet().iterator();
        	while (productIterator.hasNext())
        	{
        		Map.Entry product = (Map.Entry)productIterator.next(); 
        		productList.add((int)product.getKey());
        	}
        	return productList;
        }

    }
}
