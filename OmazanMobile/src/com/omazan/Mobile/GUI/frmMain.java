package com.omazan.Mobile.GUI;

import java.awt.BorderLayout;
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

public class frmMain extends JFrame {

	private JPanel contentPane;
	
	public void ShowEditCustomerDialog(int customerID)
	{
		contentPane.removeAll();
		pnlCustomer customerPanel = new pnlCustomer();
		customerPanel.setCustomerID(customerID);
		contentPane.add( customerPanel);
		invalidate();
		validate();
		repaint();		
	}
	
	public void ShowListCustomerDialog()
	{
		pnlListCustomers customersPnl = new pnlListCustomers();
		customersPnl.LoadCustomers();
		
		contentPane.removeAll();
		contentPane.add( customersPnl);
		invalidate();
		validate();
		repaint();
		
	}
	
	public void ShowEditProductDialog(int ProdcutID)
	{
		contentPane.removeAll();
		pnlProduct ProductPanel = new pnlProduct();
		ProductPanel.setProductID(ProdcutID);
		contentPane.add( ProductPanel);
		invalidate();
		validate();
		repaint();	
	}
	
	public void ShowEditOrderDialog(int OrderID)
	{
		contentPane.removeAll();
		pnlOrder OrderPanel = new pnlOrder();
		OrderPanel.setOrderID(OrderID);
		contentPane.add( OrderPanel);
		invalidate();
		validate();
		repaint();	
	}
	
	public void ShowListProductDialog()
	{
		pnlListProducts prodcutsPnl = new pnlListProducts();
		prodcutsPnl.LoadProducts();
		
		contentPane.removeAll();
		contentPane.add( prodcutsPnl);
		invalidate();
		validate();
		repaint();
	}
	
	public void ShowListOrderDialog()
	{
		pnlListOrders ordersPnl = new pnlListOrders();
		ordersPnl.LoadProducts();
		
		contentPane.removeAll();
		contentPane.add( ordersPnl);
		invalidate();
		validate();
		repaint();
	}
	
	
	public void SendServerRefreshRequest()
	{
		DatabaseState snapshot = DBSnapshotBL.GetDBSnapshot();
		DBSnapshotBL.LoadDBSnapshot(snapshot);
		
		contentPane.removeAll();
		
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
		setBounds(100, 100, 884, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRefresh = new JMenu("Refresh");
		menuBar.add(mnRefresh);
		
		JMenuItem mntmServerRefresh = new JMenuItem("Server Refresh");
		mntmServerRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendServerRefreshRequest();
			}
		});
		mnRefresh.add(mntmServerRefresh);
		
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
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
