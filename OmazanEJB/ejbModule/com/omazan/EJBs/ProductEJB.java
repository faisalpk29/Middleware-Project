package com.omazan.EJBs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.omazan.EJBs.Interfaces.*;
import com.omazan.Entities.Product;

/**
 * Session Bean implementation class ProductEJB
 */
@Stateless
@LocalBean
public class ProductEJB implements ProductEJBRemote, ProductEJBLocal {

	/**
	 * Default constructor.
	 */
	public ProductEJB() {

		
	}

	@PersistenceContext(unitName = "OmazanPU")
	private EntityManager em;

	public void AddProduct(Product Product) {				
		em.persist(Product);
	}

	public boolean UpdateProduct(Product nProduct) {
		boolean result = false;
		Product oProduct = em.find(Product.class, nProduct.getId());
		if (oProduct != null) {
			oProduct.setDescription(nProduct.getDescription());
			oProduct.setDetails(nProduct.getDetails());
			oProduct.setName(nProduct.getName());
			oProduct.setPrice(nProduct.getPrice());		
			oProduct.setQuantity(nProduct.getQuantity());
			oProduct.setIsActive(nProduct.isIsActive());
			em.persist(oProduct);			
			result = true;
		}
		return result;
	}

	public boolean EnableProduct(int ProductId) {
		boolean result = false;
		Product product = em.find(Product.class, ProductId);
		if (product != null) {
			
			product.setIsActive(true);			
			em.persist(product);			
			result = true;
		}
		return result;
	}

	public boolean DisableProduct(int ProductId) {
		boolean result = false;
		Product product = em.find(Product.class, ProductId);
		if (product != null) {
			
			product.setIsActive(false);			
			em.persist(product);			
			result = true;
		}
		return result;
	}

	public Product GetProduct(int ProductID) {
		Product product = em.find(Product.class, ProductID);
		return product;
	}
	
	public Product GetProductByCode(String Code)
	{
		Query query = em
				.createQuery("SELECT p FROM Product p where LOWER(p.Code) = :cname ");
		query.setParameter("cname", Code.toLowerCase());				
		List<Product> products = query.getResultList();
		if (products != null && !products.isEmpty())
			return products.get(0);
		return null;
	}

	public List<Product> ListProducts() {
		Query query = em.createQuery("SELECT c FROM Product c ");
		List<Product> product = query.getResultList();
		return product;
	}
	
	public List<Product> ListActiveProducts() {
		Query query = em.createQuery("SELECT c FROM Product c where c.IsActive = true");
		List<Product> product = query.getResultList();
		return product;
	}

	public List<Product> SearchProduct(String Code, String Name) {

		Query query = em
				.createQuery("SELECT p FROM Product p where LOWER(p.Code) = :cname OR LOWER(p.Name) = :nname ");
		query.setParameter("cname",  Code.toLowerCase()  );
		query.setParameter("nname", Name.toLowerCase()  );		
		List<Product> products = query.getResultList();
		return products;
	}
	
	public List<Product> SearchActiveProduct(String Code, String Name) {

		Query query = em
				.createQuery("SELECT p FROM Product p where (LOWER(p.Code) = :cname OR LOWER(p.Name) = :nname) and  p.IsActive = true");
		query.setParameter("cname",  Code.toLowerCase()  );
		query.setParameter("nname", Name.toLowerCase()  );		
		List<Product> products = query.getResultList();
		return products;
	}

	public boolean IncreaseQuantity(int ProductId, int Quantity) {
		boolean result = false;
		Product product = em.find(Product.class, ProductId);
		if (product != null) {			
			product.setQuantity(product.getQuantity() + Quantity);			
			em.persist(product);			
			result = true;
		}
		return result;
	}

	public boolean DecreaseQuantity(int ProductId, int Quantity) {
		boolean result = false;
		Product product = em.find(Product.class, ProductId);
		if (product != null && product.getQuantity() >= Quantity) {			
			product.setQuantity(product.getQuantity() - Quantity);			
			em.persist(product);			
			result = true;
		}
		return result;
	}
}
