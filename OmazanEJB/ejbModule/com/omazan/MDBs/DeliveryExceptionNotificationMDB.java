package com.omazan.MDBs;


import java.io.StringReader;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.omazan.EJBs.CustomerEJB;
import com.omazan.EJBs.OrderEJB;
import com.omazan.EJBs.TruckEJB;
import com.omazan.Entities.Email;
import com.omazan.Entities.Notification;
import com.omazan.Entities.Order;
import com.omazan.Entities.ShipmentLog;
import com.omazan.Entities.Truck;

/**
 * Message-Driven Bean implementation class for: DeliveryExceptionEmailMDB
 */
@MessageDriven(mappedName = "jms/Omazan/DeliveryExceptions")
public class DeliveryExceptionNotificationMDB implements MessageListener {

	@EJB
	private OrderEJB orderEJB;	
	@EJB
	private TruckEJB truckEJB;	
	@EJB
	private CustomerEJB customerEJB;
	
	
    public DeliveryExceptionNotificationMDB() {
        
    }
	
	public void onMessage(Message message) {        
		try {
	        if (message instanceof javax.jms.TextMessage) {
	            String xmlMessage = ((javax.jms.TextMessage) message).getText();	            
	            SendDeliveryExceptionNotifications(xmlMessage);	            
	        }
	    } catch (JMSException ex) {
	        
	    }        
    }
	
	
	private void SendDeliveryExceptionNotifications(String XmlMessage)
	{
		InputSource source1 = new InputSource(new StringReader(XmlMessage));		
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			String deliveryException = xpath.evaluate("/exceptionEvent/exceptionDescription", source1);
			source1 = new InputSource(new StringReader(XmlMessage));	
			String truckIdStr = xpath.evaluate("/exceptionEvent/truckId", source1);			
			int truckId = Integer.parseInt(truckIdStr);
			Truck truck = truckEJB.GetTruckByNumber(truckId);
			if( truck != null && truck.getOrders() != null && truck.getOrders().size() > 0)
			{
				for(Order order: truck.getOrders())
				{
					Notification notification = new Notification();
					notification.setMessageTime(new Date());
					notification.setMessage("Order (" + order.getId() + ") has delivery exception. "+ deliveryException  + ".");
					orderEJB.AddOrderNotificationLog(order.getId(), notification);
				}
			}
			
		} catch (Exception e) {
		}
		
	}

}
