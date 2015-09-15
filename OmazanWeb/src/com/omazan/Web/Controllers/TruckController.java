package com.omazan.Web.Controllers;

import java.util.List;

import javax.ejb.EJB;



import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.omazan.EJBs.TruckEJB;
import com.omazan.Entities.Customer;
import com.omazan.Entities.Truck;

public class TruckController {
	@EJB
	private TruckEJB truckEJB;
	
	private Truck truck = new Truck();

	public List<Truck> getTrucksList() {
		return truckEJB.ListTrucks();
	}
	
	public String doAddTruck() {
		if( truckEJB.GetTruckByNumber(truck.getNumber()) != null)
		{
		    FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("A truck with same number already exists."));
			return null;
		}
		truckEJB.AddTruck(truck);
		return "ListTrucks.xhtml";
	}
	
	public void doFindTruckById() {
		truck = truckEJB.GetTruck(truck.getId());
	}
	
	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public TruckEJB getTruckEJB() {
		return truckEJB;
	}
}
