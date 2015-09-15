package com.omazan.Mobile.BL;


import java.util.Date;
import java.util.List;

import com.omazan.Mobile.Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class OrderBL  {
	
	private  static String ORDER_IN_PROCESS = "In Process";
	private  static String ORDER_DELIVERD = "Delivered";	
	
    public OrderBL() {
        
    }
    
    public static void AddOrder(EntityManager em,Order order)
    {
    	for( ShipmentLog log : order.getShipmentLog())
		{
			em.persist(log);
		}
    	for( Notification notification : order.getNotifications())
		{
			em.persist(notification);
		}
    		for( OrderLine orderline : order.getItems())
    		{
    			em.persist(orderline);
    		}
    		em.persist(order);    
    	
    }
    
    
    public static List<Order> ListOrdersByCustomer(String PersistaenceUnit,int CustomerId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Order c WHERE c.OrderedBy.ID = " + CustomerId + " ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();

		em.close();
		emf.close();
		
		return orders;
	}
    
    public static List<Order> ListInProcessOrders(String PersistaenceUnit) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Order c WHERE c.DeleiveryStatus = '" + ORDER_IN_PROCESS + "' ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();
		

		em.close();
		emf.close();		
		
		return orders;
	}
    
    public static List<Order> ListDeleiveredOrders(String PersistaenceUnit) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Order c WHERE c.DeleiveryStatus = '" + ORDER_DELIVERD + "' ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();

		em.close();
		emf.close();
		
		return orders;   
	}
    
    public static List<Order> ListOrders(String PersistaenceUnit) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Order c ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();

		em.close();
		emf.close();
		
		return orders;   
	}
    
    public static Order GetOrder(String PersistaenceUnit,int OrderID) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Order order = em.find(Order.class, OrderID);

		em.close();
		emf.close();
		
		return order;
	}
    
    
    
   
    
    
}
