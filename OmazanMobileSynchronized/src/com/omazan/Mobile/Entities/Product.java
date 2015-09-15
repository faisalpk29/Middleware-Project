package com.omazan.Mobile.Entities;

import java.io.Serializable;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {
	
	@Id  
	private int Id;
	@Column( nullable = false, length = 255)
	private String Code;
	@Column( nullable = false, length = 1024)
	private String Name;
	@Column( nullable = false, length = 5000)
	private String Description;
	@Column( nullable = false, length = 10000)
	private String Details;
	@Column( nullable = false)
	private int Quantity;
	@Column( nullable = false)
	private double Price;
	@Column( nullable = false)
	private boolean IsActive;
	
	
	private static final long serialVersionUID = 1L;
	
	public Product() {
		super();
		IsActive = true;
		
	}   

	public Product(String code, String name, String description,
			String details, int quantity, double price) {
		super();
		Code = code;
		Name = name;
		Description = description;
		Details = details;
		Quantity = quantity;
		Price = price;
		IsActive = true;
	}
	
	@Override
	public String toString() {
		return "Product [Id=" + Id + ", Code=" + Code + ", Name=" + Name
				+ ", Description=" + Description + ", Details=" + Details
				+ ", Quantity=" + Quantity + ", Price=" + Price + ", IsActive="
				+ IsActive + "]";
	}

	public int getId() {
		return this.Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}   
	public String getName() {
		return this.Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}   
	public String getDescription() {
		return this.Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
   
	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
   
}
