package com.omazan.Web.Controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.omazan.EJBs.*;
import com.omazan.Entities.*;


public class CustomerController {
	@EJB
	private CustomerEJB customerEJB;
	private Customer customer = new Customer();


	public List<Customer> getCustomersList() {
		return customerEJB.ListCustomers();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String doAddCustomer() {
		if( customerEJB.GetCustomerByUsename(customer.getUsername()) != null)
		{
		    FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("A customer with same username already exists."));
			return null;
		}
		customerEJB.AddCustomer(customer);
		return "ListCustomers.xhtml";
	}
	
	public String doSaveCustomer() {
		customerEJB.UpdateCustomer(customer);
		return "ListCustomers.xhtml";
	}
	
	public void doFindCustomerById() {
		customer = customerEJB.GetCustomer(customer.getID());
		customer.setPassword("");
	}
	
	public void doLoadLoggedInCustomer() {
		customer = customerEJB.GetCustomer(251); //hard coded
		customer.setPassword("");
	}
	
	public CustomerEJB getCustomerEJB() {
		return customerEJB;
	}		
}
