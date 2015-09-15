package com.omazan.EJBs;

import java.util.Date;
import java.util.List;

import com.omazan.EJBs.Interfaces.OrderEJBLocal;
import com.omazan.EJBs.Interfaces.OrderEJBRemote;
import com.omazan.Entities.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class OrderEJB
 */
@Stateful
@LocalBean
public class OrderEJB implements OrderEJBRemote, OrderEJBLocal {
	
	@EJB
	private ProductEJB productEJB;	
	@EJB
	private CustomerEJB customerEJB;
	
	@Resource
	private SessionContext ctx;	
	@PersistenceContext(unitName = "OmazanPU")
	private EntityManager em;
	
	private final String ORDER_IN_PROCESS = "In Process";
	private final String ORDER_DELIVERD = "Delivered";	
	
    public OrderEJB() {
        
    }
    
    public List<Order> ListOrdersByCustomer(int CustomerId) {
		Query query = em.createQuery("SELECT c FROM Order c WHERE c.OrderedBy.ID = " + CustomerId + " ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();
		return orders;
	}
    
    public List<Order> ListInProcessOrders() {
		Query query = em.createQuery("SELECT c FROM Order c WHERE c.DeleiveryStatus = '" + ORDER_IN_PROCESS + "' ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();
		
		
		return orders;
	}
    
    public List<Order> ListDeleiveredOrders() {
		Query query = em.createQuery("SELECT c FROM Order c WHERE c.DeleiveryStatus = '" + ORDER_DELIVERD + "' ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();
		return orders;   
	}
    
    public List<Order> ListOrders() {
		Query query = em.createQuery("SELECT c FROM Order c ORDER BY c.OrderDate DESC");
		List<Order> orders = query.getResultList();
		return orders;   
	}
    
    public Order GetOrder(int OrderID) {
		Order order = em.find(Order.class, OrderID);
		return order;
	}
    
    
    
    public void MarkProductAsDeleivered(int OrderID)
    {
    	Order order = em.find(Order.class, OrderID);
    	if( order != null)
    	{
    		order.setDeleiveryStatus(ORDER_DELIVERD);
    		em.persist(order);
    	}    	
    }
    
    public void AddOrderShipmentLog(int OrderID, ShipmentLog log)
    {
    	Order order = em.find(Order.class, OrderID);
    	if( order != null)
    	{
    		order.getShipmentLog().add(log);
    		em.persist(log);
    		em.persist(order);
    	}    	
    }
    
    public void AddOrderNotificationLog(int OrderID, Notification notification)
    {
    	Order order = em.find(Order.class, OrderID);
    	if( order != null)
    	{
    		order.getNotifications().add(notification);
    		em.persist(notification);
    		em.persist(order);
    	}    	
    }
    
    public String ProcessOrder(Order order)
    {
    	double TotalPrice = 0;
    	if( order != null && order.getItems() != null && order.getItems().size() > 0)
    	{
    		for( OrderLine orderline : order.getItems())
    		{
    			if(ValidateOrderLine(orderline))
    			{
    				if(orderline.getItem().getQuantity() >= orderline.getQuantity() )
    				{
    					productEJB.DecreaseQuantity(orderline.getItem().getId(), orderline.getQuantity());
    					TotalPrice += orderline.getPrice();
    				}
    				else
    				{
    					ctx.setRollbackOnly();
    					return "Order Canceled - Product " + orderline.getItem().getCode() + " is out of stock.";
    				}
    			}
    			else
    			{
    				ctx.setRollbackOnly();
    				return "Order Canceled - Invalid product in order";
    			}
    		}    		
    		order.setPrice(TotalPrice);
    		order.setOrderDate(new Date());
    		order.setDeleiveryStatus(ORDER_IN_PROCESS);
    		for( OrderLine orderline : order.getItems())
    		{
    			em.persist(orderline);
    		}
    		em.persist(order);    
    	}
    	return "";
    }
    
    private boolean ValidateOrderLine(OrderLine orderline)
    {    	
    	if( orderline != null && orderline.getItem() != null && orderline.getItem().getId() > 0 )
    	{
    		Product product = productEJB.GetProduct(orderline.getItem().getId());    		
    		if( product != null)
    		{
    			orderline.setItem(product);
    			orderline.setPrice(orderline.getItem().getPrice() * orderline.getQuantity());
    			return true;
    		}
    	}
    	return false;
    }
    
    
}
