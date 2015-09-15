package com.omazan.Mobile.BL;

import java.util.List;








import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;





import com.omazan.Mobile.Entities.*;


public class CustomerBL  {

	public CustomerBL() {

	}

	public static void AddCustomer(EntityManager em,Customer customer) {
		em.persist(customer);
	}
	
	public static boolean UpdateCustomer(String PersistaenceUnit,Customer ncustomer) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
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
		
		 tx.commit();
		em.close();
		emf.close();
		
		return result;
	}

	public static Customer GetCustomer(String PersistaenceUnit, int CustomerID) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Customer customer = em.find(Customer.class, CustomerID);
		
		em.close();
		emf.close();
		
		return customer;
	}

	public static Customer GetCustomerByUsename(String PersistaenceUnit,String Username) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
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

	public static List<Customer> ListCustomers(String PersistaenceUnit) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Customer c ");
		List<Customer> customers = query.getResultList();

		em.close();
		emf.close();
		
		return customers;
	}

	public static List<Customer> SearchCustomer(String PersistaenceUnit,String FirstName, String LastName,String Username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
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
