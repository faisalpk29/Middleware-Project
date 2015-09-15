package com.omazan.EJBs.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.omazan.Entities.Order;
import com.omazan.Entities.Truck;

@Remote
public interface TruckEJBRemote {
	public void AddTruck(Truck truck);
	public Truck GetTruck(int TruckID);
	public Truck GetTruckByNumber(int Number);
	public List<Truck> ListTrucks();
	public void AddOrderToTruck(int TruckNumber, Order order);
	public void RemoveOrderFromTruck( int orderId);
	public Truck GetTruckByOrderId(int OrderId);
}
