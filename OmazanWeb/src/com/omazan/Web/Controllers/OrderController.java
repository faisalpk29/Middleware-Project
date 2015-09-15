package com.omazan.Web.Controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.omazan.EJBs.OrderEJB;
import com.omazan.EJBs.TruckEJB;
import com.omazan.Entities.Order;
import com.omazan.Entities.Product;
import com.omazan.Entities.Truck;

public class OrderController {
	@EJB
	private OrderEJB orderEJB;
	@EJB
	private TruckEJB truckEJB;
	
	private Order order = new Order();	
	private Truck truck = new Truck();
	
	private List<Order>  orderResult;
	
	public void doFindOrderById() {
		order = orderEJB.GetOrder(order.getId());
	}
	
	public void doMarkOrderAsDeleivered() {
		 orderEJB.MarkProductAsDeleivered(order.getId());
		 FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("Order " + order.getId() + " is marked as delivered"));
		 
	}
	
	public String doAddOrderToTruck() {
		try
		{
		order = orderEJB.GetOrder(order.getId());
		truckEJB.AddOrderToTruck(truck.getNumber(), order);
		}
		catch(Exception exc)
		{
			
		}
		return "ListTrucks.xhtml";
	}
	
	public void doGetCustomerOrders() {
		 orderResult = orderEJB.ListOrdersByCustomer(251);	// Customer Hard coded.
	}
	
	public OrderEJB getOrderEJB() {
		return orderEJB;
	}
	
	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {		
		this.order = order;
	}

	public List<Order> getOrderResult() {
		return orderResult;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

		
}
