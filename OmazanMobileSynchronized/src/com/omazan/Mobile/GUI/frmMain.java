package com.omazan.Mobile.GUI;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.derby.tools.sysinfo;

import com.omazan.Mobile.BL.DBSnapshotBL;
import com.omazan.Mobile.Misc.DatabaseState;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import com.omazan.Mobile.TP.*;

public class frmMain extends JFrame {

	private JPanel contentPane;
	private JPanel pnlBody;
	private JTextArea txtTPStatus;
	private JComboBox cmbPU;
	private DatabaseState snapshot=null;
	

	private TPClient tpCLient;
	public void ConnectToTPMonitor()
	{
		tpCLient = TPClient.ConnectToTPMontior();
		if(tpCLient == null)
		{
			WriteTPStatus("Unable to Connect to TPMonitor.");
		}
		else
		{
			WriteTPStatus("Connected to TPMonitor Successfully.");
		}

	}
	
	public void CloseConnection()
	{
		if(tpCLient != null)
		{
			tpCLient.CloseConnection();
			tpCLient = null;
		}
	}
	
	public void SendServerRefreshRequest()
	{
		if(tpCLient != null)
		{
			GetLatestDBSnapshot();
			tpCLient.RequestTransaction();
		}
		else
			WriteTPStatus("Not connected to TP Monitor");
		
		
	}
	
	public boolean GetLatestDBSnapshot()
	{
		 snapshot = DBSnapshotBL.GetDBSnapshot();
		 return (snapshot != null);
	}
	
	public void RemoveDBSnapshot()
	{
		snapshot = null;
	}
	
	public void LoadLatestDBSnapshot()
	{
		if (snapshot != null)
		{
			DBSnapshotBL.LoadDBSnapshot(GetPU(), snapshot);
			
			
		    Component[] components = pnlBody.getComponents();
		    if(components != null &&  components.length > 0 && components[0] instanceof JOmazanPanel)
		    {
		    	((JOmazanPanel)components[0]).RefreshContent();
		    }
			
			invalidate();
			validate();
			repaint();	
			snapshot = null;
		}
	}
	
	public synchronized void WriteTPStatus(String Msg)
	{
		txtTPStatus.setText(   txtTPStatus.getText() + "\n" + Msg );
	}
	
	
	
	public String GetPU()
	{
		return String.valueOf(cmbPU.getSelectedItem());
	}
	
	
	public void ShowEditCustomerDialog(int customerID)
	{
		pnlBody.removeAll();
		pnlCustomer customerPanel = new pnlCustomer();
		customerPanel.setCustomerID(customerID);
		pnlBody.add( customerPanel);
		invalidate();
		validate();
		repaint();		
	}
	
	public void ShowListCustomerDialog()
	{
		pnlListCustomers customersPnl = new pnlListCustomers();
		customersPnl.LoadCustomers();
		
		pnlBody.removeAll();
		pnlBody.add( customersPnl);
		invalidate();
		validate();
		repaint();
		
	}
	
	public void ShowEditProductDialog(int ProdcutID)
	{
		pnlBody.removeAll();
		pnlProduct ProductPanel = new pnlProduct();
		ProductPanel.setProductID(ProdcutID);
		pnlBody.add( ProductPanel);
		invalidate();
		validate();
		repaint();	
	}
	
	public void ShowEditOrderDialog(int OrderID)
	{
		pnlBody.removeAll();
		pnlOrder OrderPanel = new pnlOrder();
		OrderPanel.setOrderID(OrderID);
		pnlBody.add( OrderPanel);
		invalidate();
		validate();
		repaint();	
	}
	
	public void ShowListProductDialog()
	{
		pnlListProducts prodcutsPnl = new pnlListProducts();
		prodcutsPnl.LoadProducts();
		
		pnlBody.removeAll();
		pnlBody.add( prodcutsPnl);
		invalidate();
		validate();
		repaint();
	}
	
	public void ShowListOrderDialog()
	{
		pnlListOrders ordersPnl = new pnlListOrders();
		ordersPnl.LoadProducts();
		
		pnlBody.removeAll();
		pnlBody.add( ordersPnl);
		invalidate();
		validate();
		repaint();
	}
	
	
	
	public static frmMain ApplicationForm = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					ApplicationForm = frame;
					ApplicationForm.ConnectToTPMonitor();
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
		setBounds(100, 100, 919, 520);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRefresh = new JMenu("Refresh");
		menuBar.add(mnRefresh);
		
		JMenuItem mntmServerRefresh = new JMenuItem("Synchronize with Server");
		mntmServerRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendServerRefreshRequest();
			}
		});
		mnRefresh.add(mntmServerRefresh);
		
		JMenuItem mntmCloseConnection = new JMenuItem("Close Connection");
		mntmCloseConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CloseConnection();
			}
		});
		mnRefresh.add(mntmCloseConnection);
		
		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);
		
		JMenuItem mntmListCustomers = new JMenuItem("List Customers");
		mntmListCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowListCustomerDialog();
			}
		});
		mnCustomers.add(mntmListCustomers);
		
		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);
		
		JMenuItem mntmListProducts = new JMenuItem("List Products");
		mntmListProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowListProductDialog();
			}
		});
		mnProducts.add(mntmListProducts);
		
		JMenu mnOrders = new JMenu("Orders");
		menuBar.add(mnOrders);
		
		JMenuItem mntmListOrders = new JMenuItem("List Orders");
		mntmListOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowListOrderDialog();
			}
		});
		mnOrders.add(mntmListOrders);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbPU = new JComboBox();
		cmbPU.setModel(new DefaultComboBoxModel(new String[] {"OmazanMobilePU", "OmazanMobilePU1", "OmazanMobilePU2", "OmazanMobilePU3"}));
		cmbPU.setBounds(742, 0, 161, 20);
		contentPane.add(cmbPU);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 883, 88);
		contentPane.add(scrollPane);
		
		txtTPStatus = new JTextArea();
		scrollPane.setViewportView(txtTPStatus);
		
		
		
		pnlBody = new JPanel();
		
		JScrollPane scrollPane_1 = new JScrollPane(pnlBody);
		pnlBody.setLayout(new BorderLayout(0, 0));
		scrollPane_1.setBounds(0, 121, 893, 339);
		contentPane.add(scrollPane_1);
		
		
		
		
	
	}
}
