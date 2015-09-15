package com.omazan.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity

public class Customer implements Serializable {
	
	@Id @GeneratedValue
	private int ID;
	@Column( nullable = false, length = 255)
	private String Username;
	@Column( nullable = false, length = 255)
	private String Password;
	@Column( nullable = false, length = 512)
	private String FirstName;
	@Column( nullable = false, length = 512)
	private String LastName;
	@Column( nullable = false, length = 1024)
	private String Email;
	@Column( nullable = true, length = 255)
	private String Phone;
	@Column( nullable = true, length = 1024)
	private String Addr_Street1;
	@Column( nullable = true, length = 1024)
	private String Addr_Street2;
	@Column( nullable = true, length = 255)
	private String Addr_City;
	@Column( nullable = true, length = 255)
	private String Addr_State;
	@Column( nullable = true, length = 255)
	private String Addr_Zipcode;
	@Column( nullable = true, length = 255)
	private String Addr_Country;
	@Column( nullable = false)
	private boolean IsActive;
	@OneToMany(fetch = FetchType.EAGER)
	@OrderBy("MessageTime DESC")
	private List<Email> Emails;
	
	

	private static final long serialVersionUID = 1L;
	
	public Customer() {
		super();
		IsActive = true;
		Emails = new ArrayList<>();
	}   
	
	public Customer(String username, String password, String firstName,
			String lastName, String email, String phone, String addr_Street1,
			String addr_Street2, String addr_City, String addr_State,
			String addr_Zipcode, String addr_Country) {
		super();
		Username = username;
		Password = password;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Phone = phone;
		Addr_Street1 = addr_Street1;
		Addr_Street2 = addr_Street2;
		Addr_City = addr_City;
		Addr_State = addr_State;
		Addr_Zipcode = addr_Zipcode;
		Addr_Country = addr_Country;
		IsActive = true;
		Emails = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", Username=" + Username + ", Password="
				+ Password + ", FirstName=" + FirstName + ", LastName="
				+ LastName + ", Email=" + Email + ", Phone=" + Phone
				+ ", Addr_Street1=" + Addr_Street1 + ", Addr_Street2="
				+ Addr_Street2 + ", Addr_City=" + Addr_City + ", Addr_State="
				+ Addr_State + ", Addr_Zipcode=" + Addr_Zipcode
				+ ", Addr_Country=" + Addr_Country + ", IsActive=" + IsActive
				+ "]";
	}
	
	
	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}   
	
	public String getUsername() {
		return this.Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}   
	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}   

	public String getFirstName() {
		return this.FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}   
	public String getLastName() {
		return this.LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}   
	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}   
	public String getPhone() {
		return this.Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}

	public String getAddr_Street1() {
		return Addr_Street1;
	}

	public void setAddr_Street1(String addr_Street1) {
		Addr_Street1 = addr_Street1;
	}

	public String getAddr_Street2() {
		return Addr_Street2;
	}

	public void setAddr_Street2(String addr_Street2) {
		Addr_Street2 = addr_Street2;
	}

	public String getAddr_City() {
		return Addr_City;
	}

	public void setAddr_City(String addr_City) {
		Addr_City = addr_City;
	}

	public String getAddr_State() {
		return Addr_State;
	}

	public void setAddr_State(String addr_State) {
		Addr_State = addr_State;
	}

	public String getAddr_Zipcode() {
		return Addr_Zipcode;
	}

	public void setAddr_Zipcode(String addr_Zipcode) {
		Addr_Zipcode = addr_Zipcode;
	}

	public String getAddr_Country() {
		return Addr_Country;
	}

	public void setAddr_Country(String addr_Country) {
		Addr_Country = addr_Country;
	}
   
	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	
	public List<Email> getEmails() {
		return Emails;
	}
	public void setEmails(List<Email> emails) {
		Emails = emails;
	}
   
}
