package com.omazan.MDBs;

import java.io.StringReader;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.omazan.EJBs.OrderEJB;
import com.omazan.EJBs.TruckEJB;
import com.omazan.Entities.Order;
import com.omazan.Entities.ShipmentLog;
import com.omazan.Entities.Truck;

/**
 * Message-Driven Bean implementation class for: TruckEventsEJB
 */
@MessageDriven(mappedName = "jms/Omazan/TruckEvents")
public class TruckEventsMDB implements MessageListener {

	@EJB
	private OrderEJB orderEJB;
	
	@EJB
	private TruckEJB truckEJB;
	
	
    public TruckEventsMDB() {
    }
	
	public void onMessage(Message message) {
		try {
	        if (message instanceof javax.jms.TextMessage) {
	            String xmlMessage = ((javax.jms.TextMessage) message).getText();
	            if( xmlMessage.contains("<deliveryevent>"))
	            {	            	
	            	ProcessDeliveryEvent(xmlMessage);					
	            }
	            else if( xmlMessage.contains("<positionEvent>"))
	            {
	            	ProcessPositionEvent(xmlMessage);
	            }
	        }
	    } catch (JMSException ex) {
	        
	    }
        
    }
	
	
	private void ProcessDeliveryEvent(String XmlMessage)
	{		
		InputSource source1 = new InputSource(new StringReader(XmlMessage));		
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			String msg = xpath.evaluate("/deliveryevent/shipmentId", source1);
			int OrderId = Integer.parseInt(msg);
			if (OrderId > 0) {
				truckEJB.RemoveOrderFromTruck(OrderId);
				orderEJB.MarkProductAsDeleivered(OrderId);						
        	}
		} catch (Exception e) {
		}	

	}
	
	private void ProcessPositionEvent(String XmlMessage)
	{
		InputSource source1 = new InputSource(new StringReader(XmlMessage));		
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			String longitutde = xpath.evaluate("/positionEvent/long", source1);
			source1 = new InputSource(new StringReader(XmlMessage));	
			String latitude = xpath.evaluate("/positionEvent/lat", source1);
			source1 = new InputSource(new StringReader(XmlMessage));	
			String truckIdStr = xpath.evaluate("/positionEvent/truckId", source1);
			
			int truckId = Integer.parseInt(truckIdStr);
			Truck truck = truckEJB.GetTruckByNumber(truckId);
			if( truck != null && truck.getOrders() != null && truck.getOrders().size() > 0)
			{
				for(Order order: truck.getOrders())
				{
					ShipmentLog log = new ShipmentLog();
					log.setLogTime(new Date());
					log.setStatus("Order reached " + longitutde + "," 	+ latitude + ".");
					orderEJB.AddOrderShipmentLog(order.getId(), log);
				}
			}
			
		} catch (Exception e) {
		}
		
	}

}
