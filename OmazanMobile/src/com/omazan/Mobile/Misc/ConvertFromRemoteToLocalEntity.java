package com.omazan.Mobile.Misc;

import com.omazan.Mobile.Entities.*;

public  class ConvertFromRemoteToLocalEntity {

	public static Email ConvertEmail(com.omazan.Entities.Email remoteObj)
	{
		Email localObj = new Email();
		localObj.setId(remoteObj.getId());
		localObj.setMessage(remoteObj.getMessage());
		localObj.setMessageTime(remoteObj.getMessageTime());
		return localObj;
	}
	
	public static Customer ConvertCustomer(com.omazan.Entities.Customer remoteObj)
	{
		Customer localObj = new Customer();
		localObj.setID(remoteObj.getID());
		localObj.setUsername(remoteObj.getUsername());
		localObj.setPassword(remoteObj.getPassword());
		localObj.setFirstName(remoteObj.getFirstName());
		localObj.setLastName(remoteObj.getLastName());
		localObj.setEmail(remoteObj.getEmail());
		localObj.setPhone(remoteObj.getPhone());
		localObj.setAddr_Street1(remoteObj.getAddr_Street1());
		localObj.setAddr_Street2(remoteObj.getAddr_Street2());
		localObj.setAddr_City(remoteObj.getAddr_City());
		localObj.setAddr_State(remoteObj.getAddr_State());
		localObj.setAddr_Zipcode(remoteObj.getAddr_Zipcode());
		localObj.setAddr_Country(remoteObj.getAddr_Country());
		localObj.setIsActive(remoteObj.isIsActive());
		
//		if( remoteObj.getEmails() != null)
//		{
//			for(int i=0; i <remoteObj.getEmails().size() ; i++ )
//			{
//				localObj.getEmails().add( ConvertEmail(remoteObj.getEmails().get(i)));
//			}
//		}
		
		return localObj;
	}
	
	public static Product ConvertProduct(com.omazan.Entities.Product remoteObj)
	{
		Product localObj = new Product();
		localObj.setId(remoteObj.getId());
		localObj.setName(remoteObj.getName());
		localObj.setDescription(remoteObj.getDescription());
		localObj.setCode(remoteObj.getCode());
		localObj.setQuantity(remoteObj.getQuantity());
		localObj.setPrice(remoteObj.getPrice());
		localObj.setDetails(remoteObj.getDetails());
		localObj.setIsActive(remoteObj.isIsActive());
		return localObj;
	}
	
	public static OrderLine ConvertOrderLine(com.omazan.Entities.OrderLine remoteObj)
	{
		OrderLine localObj = new OrderLine();
		localObj.setId(remoteObj.getId());
		localObj.setQuantity(remoteObj.getQuantity());
		localObj.setPrice(remoteObj.getPrice());
		
		localObj.setItem( ConvertProduct( remoteObj.getItem()));
		return localObj;
	}
	
	public static ShipmentLog ConvertShipmentLog(com.omazan.Entities.ShipmentLog remoteObj)
	{
		ShipmentLog localObj = new ShipmentLog();
		localObj.setId(remoteObj.getId());
		localObj.setStatus(remoteObj.getStatus());
		localObj.setLogTime(remoteObj.getLogTime());
		return localObj;
	}
	
	public static Notification ConvertNotification(com.omazan.Entities.Notification remoteObj)
	{
		Notification localObj = new Notification();
		localObj.setId(remoteObj.getId());
		localObj.setMessage(remoteObj.getMessage());
		localObj.setMessageTime(remoteObj.getMessageTime());
		return localObj;
	}
	
	public static Order ConvertOrder(com.omazan.Entities.Order remoteObj)
	{
		Order localObj = new Order();
		localObj.setId(remoteObj.getId());
		localObj.setPrice(remoteObj.getPrice());
		localObj.setDeleiveryStatus(remoteObj.getDeleiveryStatus());
		localObj.setOrderDate(remoteObj.getOrderDate());
		localObj.setDeliveryDate(remoteObj.getDeliveryDate());
		
		localObj.setOrderedBy( ConvertCustomer( remoteObj.getOrderedBy()));
		
		if( remoteObj.getItems() != null)
		{
			for(int i=0; i <remoteObj.getItems().size() ; i++ )
			{
				localObj.getItems().add( ConvertOrderLine(remoteObj.getItems().get(i)));
			}
		}
		
		if( remoteObj.getShipmentLog() != null)
		{
			for(int i=0; i <remoteObj.getShipmentLog().size() ; i++ )
			{
				localObj.getShipmentLog().add( ConvertShipmentLog(remoteObj.getShipmentLog().get(i)));
			}
		}
		
		if( remoteObj.getNotifications() != null)
		{
			for(int i=0; i <remoteObj.getNotifications().size() ; i++ )
			{
				localObj.getNotifications().add( ConvertNotification(remoteObj.getNotifications().get(i)));
			}
		}
		
		return localObj;
	}
	
	public static DatabaseState ConvertDatabaseState(com.omazan.Misc.DatabaseState remoteObj)
	{
		DatabaseState localObj = new DatabaseState();
		
		
		if( remoteObj.getCustomers() != null)
		{
			for(int i=0; i <remoteObj.getCustomers().size() ; i++ )
			{
				localObj.getCustomers().add( ConvertCustomer(remoteObj.getCustomers().get(i)));
			}
		}
		
		if( remoteObj.getProducts() != null)
		{
			for(int i=0; i <remoteObj.getProducts().size() ; i++ )
			{
				localObj.getProducts().add( ConvertProduct(remoteObj.getProducts().get(i)));
			}
		}
		
		if( remoteObj.getOrders() != null)
		{
			for(int i=0; i <remoteObj.getOrders().size() ; i++ )
			{
				localObj.getOrders().add( ConvertOrder(remoteObj.getOrders().get(i)));
			}
		}
		
		return localObj;
	}
	
	
}
