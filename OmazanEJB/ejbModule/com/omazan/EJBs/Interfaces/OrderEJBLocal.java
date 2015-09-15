package com.omazan.EJBs.Interfaces;

import java.util.List;

import javax.ejb.Local;

import com.omazan.Entities.Order;
import com.omazan.Entities.Truck;

@Local
public interface OrderEJBLocal {
	public String ProcessOrder(Order order);
	public Order GetOrder(int OrderID);
	public List<Order> ListOrdersByCustomer(int CustomerId) ;
	public List<Order> ListInProcessOrders();
	public List<Order> ListDeleiveredOrders();
	public void MarkProductAsDeleivered(int OrderID);
	public List<Order> ListOrders();
	

}
