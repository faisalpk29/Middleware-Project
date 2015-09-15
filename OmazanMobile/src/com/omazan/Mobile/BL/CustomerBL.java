package com.omazan.Mobile.BL;

import java.util.List;






import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



import com.omazan.Mobile.Entities.*;


public class CustomerBL  {

	public CustomerBL() {

	}

	public static void AddCustomer(EntityManager em,Customer customer) {
		em.persist(customer);
	}

	public static Customer GetCustomer(int CustomerID) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OmazanMobilePU");
		EntityManager em = emf.createEntityManager();
		
		Customer customer = em.find(Customer.class, CustomerID);
		
		em.close();
		emf.close();
		
		return customer;
	}

	public static Customer GetCustomerByUsename(String Username) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OmazanMobilePU");
		EntityManager em = emf.createEntityManager();
		
		Query query = em
				.createQuery("SELECT c FROM Customer c where LOWER(c.Username) = :uname ");
		query.setParameter("uname", Username.toLowerCase());
		List<Customer> customers = query.getResultList();
		
		em.close();
		emf.close();
		
		if (customers != null && !customers.isEmpty())
			return customers.get(0);
		return null;
	}

	public static List<Customer> ListCustomers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OmazanMobilePU");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Customer c ");
		List<Customer> customers = query.getResultList();

		em.close();
		emf.close();
		
		return customers;
	}

	public static List<Customer> SearchCustomer(String FirstName, String LastName,String Username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OmazanMobilePU");
		EntityManager em = emf.createEntityManager();
		
		Query query = em
				.createQuery("SELECT c FROM Customer c where LOWER(c.FirstName) = :fname OR LOWER(c.LastName) = :lname OR LOWER(c.Username) = :uname ");
		query.setParameter("fname",  FirstName.toLowerCase() );
		query.setParameter("lname",  LastName.toLowerCase() );
		query.setParameter("uname",  Username.toLowerCase() );
		List<Customer> customers = query.getResultList();

		em.close();
		emf.close();
		
		return customers;
	}

	

}
