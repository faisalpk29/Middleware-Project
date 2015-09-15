package com.omazan.Client;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.omazan.EJBs.Interfaces.*;
import com.omazan.Entities.*;

public class Business {
	private static CustomerEJBRemote customerEJB;
	private static ProductEJBRemote productEJB;
	static
	{
		Context ic;
		try {
			ic = new InitialContext();
			customerEJB = (CustomerEJBRemote) ic.lookup("java:global/OmazanEAR/OmazanEJB/CustomerEJB!com.omazan.EJBs.Interfaces.CustomerEJBRemote");
			productEJB = (ProductEJBRemote) ic.lookup("java:global/OmazanEAR/OmazanEJB/ProductEJB!com.omazan.EJBs.Interfaces.ProductEJBRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	public static boolean AddCustomer(Customer customer) {
		try {			
			customerEJB.AddCustomer(customer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	public static boolean UpdateCustomer(Customer ncustomer)
	{
		try {
			return customerEJB.UpdateCustomer(ncustomer);			 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	public static Customer  GetCustomer(int CustomerID)
	{
		try {
			return customerEJB.GetCustomer(CustomerID);			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Customer GetCustomerByUsename(String Username)
	{
		try {
			return customerEJB.GetCustomerByUsename(Username);			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<Customer> ListCustomers()
	{
		try {
			return customerEJB.ListCustomers();			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<Customer> SearchCustomer(String FirstName,String LastName,String Username)
	{
		try {
			return customerEJB.SearchCustomer(FirstName, LastName, Username);			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean AddProduct(Product Product)
	{
		try {			
			productEJB.AddProduct(Product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean UpdateProduct(Product nProduct)
	{
		try {
			return productEJB.UpdateProduct(nProduct);			 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	public static Product GetProduct(int ProductID)
	{
		try {
			return productEJB.GetProduct(ProductID);			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public static List<Product> ListProducts()
	{
		try {
			return productEJB.ListProducts();			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public static List<Product> SearchProduct(String Code,String Name)
	{
		try {
			return productEJB.SearchProduct(Code, Name);			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public static boolean IncreaseQuantity(int ProductId, int Quantity)
	{
		try {
			return productEJB.IncreaseQuantity(ProductId, Quantity);			 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	public static Product GetProductByCode(String Code)
	{
		try {
			return productEJB.GetProductByCode(Code);			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
