package com.omazan.Web.Controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.omazan.EJBs.CustomerEJB;
import com.omazan.EJBs.OrderEJB;
import com.omazan.EJBs.ProductEJB;
import com.omazan.Entities.Order;
import com.omazan.Entities.OrderLine;
import com.omazan.Entities.Product;

public class ShoppingCartController {
	@EJB
	private OrderEJB orderEJB;
	@EJB
	private ProductEJB productEJB;
	@EJB
	private CustomerEJB customerEJB;
	
	private Order order = new Order();
	private OrderLine orderline = new OrderLine();
	private int customerId = 251; //Customer Hard coded
	
	public String doAddOrderLine()
	{		
        
		Product selectedProduct = orderline.getItem();
		if( orderline.getQuantity() <= 0)
		{
			FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("Invalid quantity."));
			return null;
		}
		else if( selectedProduct == null || selectedProduct.getId() <= 0 )
		{
		    FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("Invalid product."));
			return null;
		}
		else if( selectedProduct.getQuantity() < orderline.getQuantity())
		{
			FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("Ordered quantity is greater that available stock."));
			return null;
		}
		orderline.setPrice( orderline.getItem().getPrice() * orderline.getQuantity());
		OrderLine existingItem = GetExistingOrderLine(orderline.getItem().getId());
		if( existingItem != null)
			existingItem = orderline;
		else
			order.getItems().add(orderline);
		orderline = new OrderLine();
		return "CustomerShoppingCart.xhtml";
	}
	
	
	
	
	public String doCheckOut()
	{
		if( order.getItems() == null || order.getItems().size() <= 0 )
		{
			FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("No products selected yet."));
			return null;
		}
		
		order.setOrderedBy(customerEJB.GetCustomer(customerId));
		String msgResult = orderEJB.ProcessOrder(order);
		if( msgResult != "" )
		{
		    FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(msgResult));
			return null;
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("Order processed successfully. Your order id is " + order.getId()));
			order = new Order();
			return null;
		}
	}


	public void doInitializeOrderLine()
	{		
		if( orderline != null && orderline.getItem() != null && orderline.getItem().getId() > 0)
		{
			OrderLine existingItem = GetExistingOrderLine(orderline.getItem().getId());
			if( existingItem != null)
				orderline = existingItem;
			else
				orderline.setItem( productEJB.GetProduct(orderline.getItem().getId()) );
		}
	}
	
	private OrderLine GetExistingOrderLine(int ProductId)
	{
		if( order != null && order.getItems() != null && order.getItems().size() > 0)
		{
			for (OrderLine i : order.getItems()) {
				if( i.getItem().getId() == ProductId)
					return i;
			}
		}
		return null;
	}
	
	public OrderLine getOrderline() {
		return orderline;
	}

	public void setOrderline(OrderLine orderline) {
		this.orderline = orderline;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}

	public int getCustomerId() {
		return customerId;
	}		
}
