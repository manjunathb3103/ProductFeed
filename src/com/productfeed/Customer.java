package com.productfeed;

import java.util.ArrayList;

public class Customer {
    int id;
    ArrayList<Seller> subscriptions;

    public Customer(int id) {
        subscriptions = new ArrayList <Seller> ();
        this.id = id;
    }

    public void subscribe(Seller seller) {
        seller.addCustomer(this);
        subscriptions.add(seller);
    }

    public void unsubscribe(Seller seller) {
        subscriptions.remove(seller);
    }
    public ArrayList<Product> fetchFeed() {
        ArrayList<Product> allFeeds = new ArrayList<Product>();
        for(Seller seller : subscriptions)
        {
            allFeeds.addAll(seller.getProducts());
        }
        return allFeeds;
    }

}
