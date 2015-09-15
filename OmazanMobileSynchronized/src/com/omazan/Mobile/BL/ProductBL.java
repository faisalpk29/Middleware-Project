package com.omazan.Mobile.BL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;









import com.omazan.Mobile.Entities.*;


public class ProductBL {

	public ProductBL() {		
	}	

	public static void AddProduct(EntityManager em,Product Product) {				
		em.persist(Product);
	}
	
	public static Product GetProduct(String PersistaenceUnit,int ProductID) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Product product = em.find(Product.class, ProductID);

		em.close();
		emf.close();
		
		return product;
	}
	
	public static Product GetProductByCode(String PersistaenceUnit,String Code)
	{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em
				.createQuery("SELECT p FROM Product p where LOWER(p.Code) = :cname ");
		query.setParameter("cname", Code.toLowerCase());				
		List<Product> products = query.getResultList();
		
		em.close();
		emf.close();
		
		if (products != null && !products.isEmpty())
			return products.get(0);

		return null;
	}

	public static List<Product> ListProducts(String PersistaenceUnit) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Product c ");
		List<Product> product = query.getResultList();

		em.close();
		emf.close();
		
		return product;
	}
	
	public static List<Product> ListActiveProducts(String PersistaenceUnit) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT c FROM Product c where c.IsActive = true");
		List<Product> product = query.getResultList();

		em.close();
		emf.close();
		
		return product;
	}

	public static List<Product> SearchProduct(String PersistaenceUnit,String Code, String Name) {


		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em
				.createQuery("SELECT p FROM Product p where LOWER(p.Code) = :cname OR LOWER(p.Name) = :nname ");
		query.setParameter("cname",  Code.toLowerCase()  );
		query.setParameter("nname", Name.toLowerCase()  );		
		List<Product> products = query.getResultList();

		em.close();
		emf.close();
		
		return products;
	}
	
	public static List<Product> SearchActiveProduct(String PersistaenceUnit,String Code, String Name) {


		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		Query query = em
				.createQuery("SELECT p FROM Product p where (LOWER(p.Code) = :cname OR LOWER(p.Name) = :nname) and  p.IsActive = true");
		query.setParameter("cname",  Code.toLowerCase()  );
		query.setParameter("nname", Name.toLowerCase()  );		
		List<Product> products = query.getResultList();

		em.close();
		emf.close();
		
		return products;
	}

	
}
