package com.omazan.EJBs;

import java.util.List;

import com.omazan.EJBs.Interfaces.TruckEJBLocal;
import com.omazan.EJBs.Interfaces.TruckEJBRemote;
import com.omazan.Entities.Order;
import com.omazan.Entities.Product;
import com.omazan.Entities.Truck;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class TruckEJB
 */
@Stateless
@LocalBean
public class TruckEJB implements TruckEJBRemote, TruckEJBLocal {

	@PersistenceContext(unitName = "OmazanPU")
	private EntityManager em;
	
    public TruckEJB() {
       
    }

    public void AddTruck(Truck truck)
    {
    	em.persist(truck);
    }
    
    public void AddOrderToTruck(int TruckNumber, Order order)
    {
    	Truck truck = GetTruckByNumber(TruckNumber);
    	truck.getOrders().add(order);
    	em.persist(truck);
    }
    
    public void RemoveOrderFromTruck( int orderId)
    {
		Truck truck = GetTruckByOrderId(orderId);
		if (truck.getOrders() != null && truck.getOrders().size() > 0) {
			
			int index = -1;
			List<Order> orders = truck.getOrders();
			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getId() == orderId) {
					index = i;
					break;
				}
			}
			if (index >= 0) {
				orders.remove(index);
				em.persist(truck);
			}
		}
    }
    
    public Truck GetTruck(int TruckID) {
    	Truck truck = em.find(Truck.class, TruckID);
		return truck;
	}
    
    public Truck GetTruckByNumber(int Number)
	{
		Query query = em
				.createQuery("SELECT p FROM Truck p where p.Number = :cnumber ");
		query.setParameter("cnumber", Number);				
		List<Truck> trucks = query.getResultList();
		if (trucks != null && !trucks.isEmpty())
			return trucks.get(0);
		return null;
	}
    
    public Truck GetTruckByOrderId(int OrderId)
	{
		Query query = em
				.createQuery("SELECT t FROM Truck t JOIN t.Orders b WHERE b.Id = :oid ");
		query.setParameter("oid", OrderId);				
		List<Truck> trucks = query.getResultList();
		if (trucks != null && !trucks.isEmpty())
			return trucks.get(0);
		return null;
	}

	public List<Truck> ListTrucks() {
		Query query = em.createQuery("SELECT c FROM Truck c ");
		List<Truck> trucks = query.getResultList();
		return trucks;
	}
}
