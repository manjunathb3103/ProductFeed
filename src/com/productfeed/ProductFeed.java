package com.productfeed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProductFeed {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		while (true)
		{
			System.out.println("Select the Operation\n"
					+ "1. Create Customer\n"
					// + "2. Delete Customer\n"
					+ "2. Create Seller\n" 
					+ "3. Delete Seller\n"
					+ "4. Post Product\n"
					+ "5. Delete Product\n"
					+ "6. Subscribe a Customer to a Seller\n"
					+ "7. Unsubscribe a Customer from a Seller\n"
					+ "8. Fetch Feed of a Customer\n"
					+ "9. exit");
			Scanner obj = new Scanner(System.in);
			int choice = obj.nextInt();
			
			// Declaring variables used in switch statement
			int cid;    // Customer ID
			int sid;    // Seller ID
			int pid;    // Product ID
			switch (choice) {
			case 1:
				System.out.println("Enter a Customer ID\n");
				cid = new Scanner(System.in).nextInt();
				createCustomer(cid);
				break;
			case 2:
				System.out.println("Enter a Seller ID\n");
				sid = new Scanner(System.in).nextInt();
				createSeller(sid);
				break;
			case 3:
				System.out.println("Enter the Seller ID to be deleted\n");
				printSellerList();
				sid = new Scanner(System.in).nextInt();
				deleteSeller(sid);
				break;
			case 4:
				System.out.println("Enter the Seller posting a Product\n");
				printSellerList();
				sid = new Scanner(System.in).nextInt();
				System.out.println("Enter a Product ID\n");
				pid = new Scanner(System.in).nextInt();
				System.out.println("Enter the Product Name\n");
				String name = new Scanner(System.in).nextLine();
				System.out.println("Enter the Product Price\n");
				int price = new Scanner(System.in).nextInt();
				addProduct(sid, pid, name, price);
				break;
			case 5:
				System.out.println("Enter the Product ID to be deleted\n");
				printProdutList();
				pid = new Scanner(System.in).nextInt();
				deleteProduct(pid);
			case 6:
				System.out.println("Enter the Customer ID\n");
				printCustomerList();
				cid = new Scanner(System.in).nextInt();
				System.out.println("Enter the Seller ID\n");
				printSellerList();
				sid = new Scanner(System.in).nextInt();
				subscribe(cid, sid);
				break;
			case 7:
				System.out.println("Enter the Customer ID\n");
				printCustomerList();
				cid = new Scanner(System.in).nextInt();
				System.out.println("Enter the Seller ID\n");
				printSellerList();
				sid = new Scanner(System.in).nextInt();
				unsubscribe(cid, sid);
				break;
			case 8:
				System.out.println("Enter the Customer ID\n");
				printCustomerList();
				cid = new Scanner(System.in).nextInt();
				printFeed(cid);
				break;
			case 9: 
				return;
			default:
				System.out.println("Invalid Option\n");
				break;
			}
		}
		//EndWhile: ;
	}

	public static ArrayList<Product> bubbleSort(ArrayList<Product> products) {
		int n = products.size();
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (products.get(j).getTimestamp() > products.get(j + 1).getTimestamp()) {
					Collections.swap(products, j, j + 1);
				}
		return products;
	}

	public static ArrayList<Product> sortProduct(ArrayList<Product> products) {
		return bubbleSort(products);

	}

	public static void createCustomer(int id) {
		Handlers.Customers.createCustomer(id);
	}

	public static ArrayList<Product> fetchFeed(int cid) {
		Customer customerObject = Handlers.Customers.getObject(cid);
		return sortProduct(customerObject.fetchFeed());
	}
	
	public static void printFeed(int cid) {
		ArrayList<Product> productList = fetchFeed(cid);
		System.out.println("ID\t"
				+ "Name\t"
				+ "Price\n");
		for (Product product : productList)
		{
			System.out.println(product.id + "\t"
					+ product.name + "\t"
					+ product.price + "\n");
		}
	}

	public static void subscribe(int cid, int sid) {
		Customer customerObject = Handlers.Customers.getObject(cid);
		Seller sellerObject = Handlers.Sellers.getObject(sid);
		customerObject.subscribe(sellerObject);
	}

	public static void unsubscribe(int cid, int sid) {
		Customer customerObject = Handlers.Customers.getObject(cid);
		Seller sellerObject = Handlers.Sellers.getObject(sid);
		customerObject.unsubscribe(sellerObject);
	}

	public static void deleteSeller(int sid) {
		Handlers.Sellers.deleteSeller(sid);
	}

	public static void addProduct(int sid, int pid, String name, float price) {
		Handlers.Sellers.addProduct(sid, pid, name, price);
	}

	public static void createSeller(int id) {
		Handlers.Sellers.createSeller(id);
	}

	public static void deleteProduct(int id) {
		Handlers.Products.delete(id);
	}
	
	public static void printCustomerList()
	{
		Handlers.Customers customersObject = new Handlers.Customers();
		ArrayList<Integer> customers = customersObject.listCustomers();
		for (int i=0;  i<customers.size(); i++)
		{
			System.out.println(customers.get(i) + "\n");
		}
	}
	
	public static void printSellerList()
	{
		Handlers.Sellers sellersObject = new Handlers.Sellers();
		ArrayList<Integer> sellers = sellersObject.listSellers();
		for (int i=0;  i<sellers.size(); i++)
		{
			System.out.println(sellers.get(i) + "\n");
		}
	}
	
	public static void printProdutList()
	{
		Handlers.Products productsObject = new Handlers.Products();
		ArrayList<Integer> products = productsObject.listProducts();
		for (int i=0;  i<products.size(); i++)
		{
			System.out.println(products.get(i) + "\n");
		}
	}
}
