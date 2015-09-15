package com.omazan.Mobile.BL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;




import com.omazan.EJBs.Interfaces.DBSnapshotEJBRemote;
import com.omazan.Mobile.Misc.*;

public class DBSnapshotBL {
	private static DBSnapshotEJBRemote dbSnapshotEJBRemote;
	
	static
	{
		Context ic;
		try {
			ic = new InitialContext();
			dbSnapshotEJBRemote = (DBSnapshotEJBRemote) ic.lookup("java:global/OmazanEAR/OmazanEJB/DBSnapshotEJB!com.omazan.EJBs.Interfaces.DBSnapshotEJBRemote");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	
	public static DatabaseState GetDBSnapshot() {
		try {			
			com.omazan.Misc.DatabaseState remoteDBState = dbSnapshotEJBRemote.SynchronizeDBwithMobileClients();
			if(remoteDBState != null )
			{
				return com.omazan.Mobile.Misc.ConvertFromRemoteToLocalEntity.ConvertDatabaseState(remoteDBState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static void LoadDBSnapshot(String PersistaenceUnit,DatabaseState snapshot) {
		try {			
			ResetLocalDatabase(PersistaenceUnit);
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
			EntityManager em = emf.createEntityManager();
			
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			if(snapshot != null )
			{
				if( snapshot.getCustomers() != null)
				{
					for(int i=0; i <snapshot.getCustomers().size() ; i++ )
					{
						CustomerBL.AddCustomer(em, snapshot.getCustomers().get(i));
						
					}
				}
				
				if( snapshot.getProducts() != null)
				{
					for(int i=0; i <snapshot.getProducts().size() ; i++ )
					{
						ProductBL.AddProduct(em, snapshot.getProducts().get(i));
					}
				}
				
				if( snapshot.getOrders() != null)
				{
					for(int i=0; i <snapshot.getOrders().size() ; i++ )
					{
						OrderBL.AddOrder(em, snapshot.getOrders().get(i));
					}
				}
			}
			
			 tx.commit();
			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void ResetLocalDatabase(String PersistaenceUnit)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistaenceUnit);
		EntityManager em = emf.createEntityManager();
		
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();
		   
			javax.persistence.Query q1 = em.createNativeQuery("DELETE FROM CUSTOMER");
			javax.persistence.Query q2 = em.createNativeQuery("DELETE FROM PRODUCT");
			javax.persistence.Query q3 = em.createNativeQuery("DELETE FROM C_Order");
			javax.persistence.Query q4 = em.createNativeQuery("DELETE FROM ORDERLINE");
			javax.persistence.Query q5 = em.createNativeQuery("DELETE FROM SHIPMENTLOG");
			javax.persistence.Query q6 = em.createNativeQuery("DELETE FROM EMAIL");
			javax.persistence.Query q7 = em.createNativeQuery("DELETE FROM NOTIFICATION");
			javax.persistence.Query q8 = em.createNativeQuery("DELETE FROM CUSTOMER_EMAIL");
			javax.persistence.Query q9 = em.createNativeQuery("DELETE FROM C_Order_ORDERLINE");
			javax.persistence.Query q10 = em.createNativeQuery("DELETE FROM C_Order_NOTIFICATION");
			javax.persistence.Query q11 = em.createNativeQuery("DELETE FROM C_Order_SHIPMENTLOG");
		    
		    q11.executeUpdate();
		    q10.executeUpdate();
		    q9.executeUpdate();
		    q8.executeUpdate();
		    q7.executeUpdate();
		    q6.executeUpdate();
		    q5.executeUpdate();
		    q4.executeUpdate();
		    q3.executeUpdate();
		    q2.executeUpdate();
		    q1.executeUpdate();

		    tx.commit();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		em.close();
		emf.close();
	}
}

 