package com.omazan.TruckSimulator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;



public class frmMain extends JFrame {

	private JPanel contentPane;
	
	
	
	
	
	public static frmMain ApplicationForm = null;
	private JTextField txtOrderId;
	private JTextField txtLong;
	private JTextField txtLat;
	private JTextField txtTruck;
	private JTextField txtException;
	private JTextField txtTruckId2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					ApplicationForm = frame;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmMain() {
		setTitle("Omazan Inc.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 283);
		getContentPane().setLayout(null);
		
		JLabel lblOrderId = new JLabel("Order Id");
		lblOrderId.setBounds(25, 38, 46, 14);
		getContentPane().add(lblOrderId);
		
		txtOrderId = new JTextField();
		txtOrderId.setBounds(80, 35, 175, 20);
		getContentPane().add(txtOrderId);
		txtOrderId.setColumns(10);
		
		JButton btnSendDeliveredEvent = new JButton("Send Delivered Event");
		btnSendDeliveredEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendDeliveredEvent();
			}
		});
		btnSendDeliveredEvent.setBounds(277, 34, 175, 23);
		getContentPane().add(btnSendDeliveredEvent);
		
		JLabel lblLong = new JLabel("longitude");
		lblLong.setBounds(25, 109, 46, 14);
		getContentPane().add(lblLong);
		
		txtLong = new JTextField();
		txtLong.setColumns(10);
		txtLong.setBounds(80, 103, 175, 20);
		getContentPane().add(txtLong);
		
		JButton btnSendPositionEvent = new JButton("Send Position Event");
		btnSendPositionEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendPositionEvent();
			}
		});
		btnSendPositionEvent.setBounds(277, 136, 175, 23);
		getContentPane().add(btnSendPositionEvent);
		
		JLabel lblLatitude = new JLabel("latitude");
		lblLatitude.setBounds(25, 140, 46, 14);
		getContentPane().add(lblLatitude);
		
		txtLat = new JTextField();
		txtLat.setColumns(10);
		txtLat.setBounds(80, 134, 175, 20);
		getContentPane().add(txtLat);
		
		JLabel lblTruckId = new JLabel("Truck id");
		lblTruckId.setBounds(25, 75, 46, 14);
		getContentPane().add(lblTruckId);
		
		txtTruck = new JTextField();
		txtTruck.setColumns(10);
		txtTruck.setBounds(80, 72, 175, 20);
		getContentPane().add(txtTruck);
		
		JLabel lblException = new JLabel("Exception");
		lblException.setBounds(10, 207, 61, 14);
		getContentPane().add(lblException);
		
		txtException = new JTextField();
		txtException.setColumns(10);
		txtException.setBounds(80, 201, 175, 20);
		getContentPane().add(txtException);
		
		JButton btnSendDeliveryException = new JButton("Send Delivery Exception Event");
		btnSendDeliveryException.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendDeliveryExceptionEvent();
			}
		});
		btnSendDeliveryException.setBounds(265, 200, 212, 23);
		getContentPane().add(btnSendDeliveryException);
		
		JLabel label_2 = new JLabel("Truck id");
		label_2.setBounds(25, 173, 46, 14);
		getContentPane().add(label_2);
		
		txtTruckId2 = new JTextField();
		txtTruckId2.setColumns(10);
		txtTruckId2.setBounds(80, 170, 175, 20);
		getContentPane().add(txtTruckId2);
		
		
	}
	
	private void SendDeliveredEvent() {
		// Gets the JNDI context
		Context jndiContext;
		try {
			jndiContext = new InitialContext();

			// Looks up the administered objects
			ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
					.lookup("jms/Omazan/ConnectionFactory");
			Destination queue = (Destination) jndiContext
					.lookup("jms/Omazan/TruckEvents");

			try (JMSContext jmsContext = connectionFactory.createContext()) {
				// Sends an object message to the topic
				jmsContext.createProducer().send(
						queue,
						"<deliveryevent><shipmentId>" + txtOrderId.getText()
								+ "</shipmentId></deliveryevent>");
			}
		} catch (Exception e) {
			txtOrderId.setText("Error");
		}
	}
	
	private void SendPositionEvent() {
		// Gets the JNDI context
		Context jndiContext;
		try {
			jndiContext = new InitialContext();

			// Looks up the administered objects
			ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
					.lookup("jms/Omazan/ConnectionFactory");
			Destination queue = (Destination) jndiContext
					.lookup("jms/Omazan/TruckEvents");

			String txtMessage = "<positionEvent><truckId>" + txtTruck.getText() + "</truckId><long>" + txtLong.getText() + "</long><lat>" + txtLat.getText() + "</lat></positionEvent>";
			try (JMSContext jmsContext = connectionFactory.createContext()) {
				
				// Sends an object message to the topic
				jmsContext.createProducer().send(
						queue,txtMessage);
			}
		} catch (Exception e) {
			txtOrderId.setText("Error");
		}
	}
	
	private void SendDeliveryExceptionEvent() {
		// Gets the JNDI context
		Context jndiContext;
		try {
			jndiContext = new InitialContext();

			// Looks up the administered objects
			ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
					.lookup("jms/Omazan/ConnectionFactory");
			Destination queue = (Destination) jndiContext
					.lookup("jms/Omazan/DeliveryExceptions");

			String txtMessage = "<exceptionEvent><truckId>" + txtTruckId2.getText() + "</truckId><exceptionDescription>" + txtException.getText() + "</exceptionDescription></exceptionEvent>";
			try (JMSContext jmsContext = connectionFactory.createContext()) {
				
				// Sends an object message to the topic
				jmsContext.createProducer().send(
						queue,txtMessage);
			}
		} catch (Exception e) {
			txtOrderId.setText("Error");
		}
	}
}
