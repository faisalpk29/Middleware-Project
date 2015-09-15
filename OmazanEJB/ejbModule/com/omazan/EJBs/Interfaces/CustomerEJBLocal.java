package com.omazan.EJBs.Interfaces;

import java.util.List;
import javax.ejb.Local;
import com.omazan.Entities.Customer;

@Local
public interface CustomerEJBLocal {
	public void AddCustomer(Customer customer);	
	public boolean UpdateCustomer(Customer ncustomer);	
	public boolean DisableCustomer(int CustomerID);	
	public boolean EnableCustomer(int CustomerID);	
	public Customer  GetCustomer(int CustomerID);	
	public Customer GetCustomerByUsename(String Username);	
	public List<Customer> ListCustomers();	
	public List<Customer> SearchCustomer(String FirstName,String LastName,String Username);	
	public boolean ChangeCustomerPassword(String Username,String Password);
	public boolean ChangeCustomerPassword(int CustomerID,String Password);
	
}
