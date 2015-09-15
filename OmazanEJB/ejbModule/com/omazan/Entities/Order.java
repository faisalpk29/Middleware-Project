package com.omazan.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "C_Order")
public class Order implements Serializable {

	
	@Id @GeneratedValue
	private int Id;
	@OneToMany(fetch = FetchType.EAGER)
	private List<OrderLine> Items;
	@OneToMany(fetch = FetchType.EAGER)
	@OrderBy("LogTime DESC")
	private List<ShipmentLog> ShipmentLog;
	private Customer OrderedBy;
	private double Price;
	private String DeleiveryStatus;
	@Temporal(TemporalType.TIMESTAMP)
	private Date OrderDate ;
	@Temporal(TemporalType.TIMESTAMP)
	private Date DeliveryDate ;
	@OneToMany(fetch = FetchType.EAGER)
	@OrderBy("MessageTime DESC")
	private List<Notification> Notifications;

	private static final long serialVersionUID = 1L;

	public Order() {
		super();
		Items = new ArrayList<>();
		ShipmentLog = new ArrayList<>();
		Notifications = new ArrayList<>();
	}   
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}   
	public List<OrderLine> getItems() {
		return this.Items;
	}

	public void setItems(List<OrderLine> Items) {
		this.Items = Items;
	}   
	public List<ShipmentLog> getShipmentLog() {
		return this.ShipmentLog;
	}

	public void setShipmentLog(List<ShipmentLog> ShipmentLog) {
		this.ShipmentLog = ShipmentLog;
	}   
	public double getPrice() {
		return this.Price;
	}

	public void setPrice(double Price) {
		this.Price = Price;
	}
	
	public Customer getOrderedBy() {
		return OrderedBy;
	}
	public void setOrderedBy(Customer orderedBy) {
		OrderedBy = orderedBy;
	}
	public String getDeleiveryStatus() {
		return DeleiveryStatus;
	}
	public void setDeleiveryStatus(String deleiveryStatus) {
		DeleiveryStatus = deleiveryStatus;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return DeliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
	}
	
	public List<Notification> getNotifications() {
		return Notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		Notifications = notifications;
	}
	
}
