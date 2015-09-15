package com.omazan.Entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrderItem
 *
 */
@Entity

public class OrderLine implements Serializable {

	
	@Id @GeneratedValue
	private int Id;
	private Product Item;
	private int Quantity;
	private double Price;
	private static final long serialVersionUID = 1L;

	public OrderLine() {
		super();
		Item = new Product();
		Quantity =0 ;
		Price = 0;
	}  
	

	public OrderLine(Product item, int quantity) {
		super();
		Item = item;
		Quantity = quantity;
		Price = quantity * Item.getPrice();
	}

	
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}   
	public Product getItem() {
		return this.Item;
	}

	public void setItem(Product Item) {
		this.Item = Item;
	}   
	public int getQuantity() {
		return this.Quantity;
	}

	public void setQuantity(int Quantity) {
		this.Quantity = Quantity;
	}   
	public double getPrice() {
		return this.Price;
	}

	public void setPrice(double Price) {
		this.Price = Price;
	}
   
}
