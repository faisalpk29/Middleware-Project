package com.omazan.Misc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.omazan.Entities.*;

public class DatabaseState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Customer> customers;
	private List<Product> products;
	private List<Order> orders;
	private List<Truck> trucks;
	
	public DatabaseState()
	{
		customers = new ArrayList<>();
		products = new ArrayList<>();
		orders = new ArrayList<>();
		trucks = new ArrayList<>();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Truck> getTrucks() {
		return trucks;
	}

	public void setTrucks(List<Truck> trucks) {
		this.trucks = trucks;
	}

}
