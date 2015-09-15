package com.omazan.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Truck
 *
 */
@Entity

public class Truck implements Serializable {

	@Id @GeneratedValue
	private int Id;
	private int Number;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Order> Orders;
	
	private static final long serialVersionUID = 1L;

	public Truck() {
		super();
		Orders = new ArrayList<>();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Order> getOrders() {
		return Orders;
	}

	public void setOrders(List<Order> orders) {
		Orders = orders;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}
	
	
   
}
