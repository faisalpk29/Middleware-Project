package com.omazan.EJBs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.omazan.EJBs.Interfaces.*;
import com.omazan.Entities.Customer;
import com.omazan.Entities.Email;
import com.omazan.Entities.Order;
import com.omazan.Entities.ShipmentLog;

/**
 * Session Bean implementation class CustomerEJB
 */
@Stateless
@LocalBean
public class CustomerEJB implements CustomerEJBRemote, CustomerEJBLocal {

	/**
	 * Default constructor.
	 */
	public CustomerEJB() {

	}

	@PersistenceContext(unitName = "OmazanPU")
	private EntityManager em;

	public void AddCustomer(Customer customer) {
		em.persist(customer);
	}

	public boolean UpdateCustomer(Customer ncustomer) {
		boolean result = false;
		Customer ocustomer = em.find(Customer.class, ncustomer.getID());
		if (ocustomer != null) {
			ocustomer.setAddr_City(ncustomer.getAddr_City());
			ocustomer.setAddr_Country(ncustomer.getAddr_Country());
			ocustomer.setAddr_State(ncustomer.getAddr_State());
			ocustomer.setAddr_Street1(ncustomer.getAddr_Street1());
			ocustomer.setAddr_Street2(ncustomer.getAddr_Street2());
			ocustomer.setAddr_Zipcode(ncustomer.getAddr_Zipcode());
			ocustomer.setEmail(ncustomer.getEmail());
			ocustomer.setFirstName(ncustomer.getFirstName());
			ocustomer.setLastName(ncustomer.getLastName());
			ocustomer.setPhone(ncustomer.getPhone());
			ocustomer.setIsActive(ncustomer.isIsActive());
			if( ncustomer.getPassword().length() > 0)
				ocustomer.setPassword(ncustomer.getPassword());
			em.persist(ocustomer);
			result = true;
		}
		return result;
	}

	public boolean DisableCustomer(int CustomerID) {
		boolean result = false;
		Customer customer = em.find(Customer.class, CustomerID);
		if (customer != null) {
			customer.setIsActive(false);
			em.persist(customer);
			result = true;
		}
		return result;
	}

	public boolean EnableCustomer(int CustomerID) {
		boolean result = false;
		Customer customer = em.find(Customer.class, CustomerID);
		if (customer != null) {
			customer.setIsActive(true);
			em.persist(customer);
			result = true;
		}
		return result;
	}

	public Customer GetCustomer(int CustomerID) {
		Customer customer = em.find(Customer.class, CustomerID);
		return customer;
	}

	public Customer GetCustomerByUsename(String Username) {
		Query query = em
				.createQuery("SELECT c FROM Customer c where LOWER(c.Username) = :uname ");
		query.setParameter("uname", Username.toLowerCase());
		List<Customer> customers = query.getResultList();
		if (customers != null && !customers.isEmpty())
			return customers.get(0);
		return null;
	}

	public List<Customer> ListCustomers() {
		Query query = em.createQuery("SELECT c FROM Customer c ");
		List<Customer> customers = query.getResultList();
		return customers;
	}

	public List<Customer> SearchCustomer(String FirstName, String LastName,String Username) {
		Query query = em
				.createQuery("SELECT c FROM Customer c where LOWER(c.FirstName) = :fname OR LOWER(c.LastName) = :lname OR LOWER(c.Username) = :uname ");
		query.setParameter("fname",  FirstName.toLowerCase() );
		query.setParameter("lname",  LastName.toLowerCase() );
		query.setParameter("uname",  Username.toLowerCase() );
		List<Customer> customers = query.getResultList();
		return customers;
	}

	public boolean ChangeCustomerPassword(String Username, String Password) {
		boolean result = false;
		Query query = em
				.createQuery("SELECT c FROM Customer c where LOWER(c.Username) = :uname ");
		query.setParameter("uname", Username.toLowerCase());
		List<Customer> customers = query.getResultList();
		if (customers != null && !customers.isEmpty()) {
			customers.get(0).setPassword(Password);
			em.persist(customers.get(0));
			result = true;
		}
		return result;
	}
	
	public boolean ChangeCustomerPassword(int CustomerID, String Password) {
		boolean result = false;
		Customer customer = em.find(Customer.class, CustomerID);
		if (customer != null ) {
			customer.setPassword(Password);
			em.persist(customer);
			result = true;
		}
		return result;
	}
	
	 public void SendEmailToCustomer(int CustomerId, Email email)
	    {
		 Customer ocustomer = em.find(Customer.class, CustomerId);
	    	if( ocustomer != null)
	    	{
	    		ocustomer.getEmails().add(email);
	    		em.persist(email);
	    		em.persist(ocustomer);
	    	}    	
	    }

}
