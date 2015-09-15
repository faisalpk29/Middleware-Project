package com.omazan.EJBs;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.omazan.EJBs.Interfaces.DBSnapshotEJBLocal;
import com.omazan.EJBs.Interfaces.DBSnapshotEJBRemote;
import com.omazan.Misc.DatabaseState;


@Stateless
@LocalBean
public class DBSnapshotEJB implements DBSnapshotEJBRemote, DBSnapshotEJBLocal {

    public DBSnapshotEJB() {
        
    }
    
    @EJB
	private OrderEJB orderEJB;
	@EJB
	private TruckEJB truckEJB;
	@EJB
	private CustomerEJB customerEJB;
	@EJB
	private ProductEJB productEJB;
    
    public DatabaseState SynchronizeDBwithMobileClients() {
		DatabaseState dbState = new DatabaseState();
		dbState.setCustomers(customerEJB.ListCustomers());
		dbState.setProducts(productEJB.ListProducts());
		dbState.setOrders(orderEJB.ListOrders());
		return dbState;
	}

}
