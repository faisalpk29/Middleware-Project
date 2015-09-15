package com.omazan.Web.Controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.omazan.EJBs.ProductEJB;
import com.omazan.Entities.Product;

public class ProductController {
	@EJB
	private ProductEJB productEJB;
	private Product product = new Product();
	
	
	public List<Product> getProductsList() {
		return productEJB.ListProducts();
	}

	public Product getProduct() {
		return product;
		
		
		
	}

	public void setProduct(Product product) {
		this.product = product;   
	}

	public String doAddProduct() {
		if( productEJB.GetProductByCode(product.getCode()) != null)
		{
		    FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("A product with same code already exists."));
			return null;
		}
		productEJB.AddProduct(product);
		return "ListProducts.xhtml";
	}
	
	public String doSaveProduct() {
		productEJB.UpdateProduct(product);
		return "ListProducts.xhtml";
	}
	
	public void doFindProductById() {
		product = productEJB.GetProduct(product.getId());
	}
	
	public ProductEJB getProductEJB() {
		return productEJB;
	}
}
