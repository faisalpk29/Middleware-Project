package com.omazan.Web.Controllers;

import java.util.List;

import javax.ejb.EJB;

import com.omazan.EJBs.CustomerEJB;
import com.omazan.EJBs.OrderEJB;
import com.omazan.EJBs.ProductEJB;
import com.omazan.Entities.Customer;
import com.omazan.Entities.Product;

public class SearchController {
	@EJB
	private CustomerEJB customerEJB;
	@EJB
	private ProductEJB productEJB;
	
	private String param1 = "";
	private String param2 = "";
	private String param3 = "";
	private List<Customer> customersResult;
	private List<Product>  productResult;
	
	public String doSearchProducts() {
		productResult = productEJB.SearchProduct(param1, param2);
		return null;
	}
	
	public String doSearchActiveProducts() {
		productResult = productEJB.SearchActiveProduct(param1, param2);
		return null;
	}
	
	public String doListActiveProducts() {
		productResult = productEJB.ListActiveProducts();
		return null;
	}
	
	public String doSearchCustomers() {
		customersResult = customerEJB.SearchCustomer(param1, param2, param3);
		return null;
	}
	
	
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	
	public List<Customer> getCustomersResult() {
		
		return customersResult;
	}
	public void setCustomersResult(List<Customer> customersResult) {
		this.customersResult = customersResult;
	}
	public List<Product> getProductResult() {
		return productResult;
	}
	public void setProductResult(List<Product> productResult) {
		this.productResult = productResult;
	}

	
	
}
