package com.omazan.EJBs.Interfaces;

import java.util.List;
import javax.ejb.Local;
import com.omazan.Entities.Product;

@Local
public interface ProductEJBLocal {

	public void AddProduct(Product Product);	
	public boolean UpdateProduct(Product nProduct);	
	public boolean EnableProduct(int ProductId);	
	public boolean DisableProduct(int ProductId);	
	public Product GetProduct(int ProductID);	
	public Product GetProductByCode(String Code);
	public List<Product> ListProducts();	
	public List<Product> ListActiveProducts();
	public List<Product> SearchProduct(String Code,String Name);	
	public List<Product> SearchActiveProduct(String Code,String Name);
	public boolean IncreaseQuantity(int ProductId, int Quantity);	
	public boolean DecreaseQuantity(int ProductId, int Quantity);	
}
