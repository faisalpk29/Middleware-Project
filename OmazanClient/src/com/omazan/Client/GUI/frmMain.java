package com.omazan.Client.GUI;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmMain extends JFrame {

	private JPanel contentPane;
	
	public void ShowAddCustomerDialog()
	{
		contentPane.removeAll();
		contentPane.add( new pnlCustomer());
		invalidate();
		validate();
		repaint();		
	}
	
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
	
	public void ShowAddProductDialog()
	{
		contentPane.removeAll();
		contentPane.add( new pnlProduct());
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
		
		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);
		
		JMenuItem mntmListCustomers = new JMenuItem("List Customers");
		mntmListCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowListCustomerDialog();
			}
		});
		mnCustomers.add(mntmListCustomers);
		
		JMenuItem mntmAddCustomer = new JMenuItem("Add Customer");
		mntmAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAddCustomerDialog();
			}
		});
		mnCustomers.add(mntmAddCustomer);
		
		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);
		
		JMenuItem mntmListProducts = new JMenuItem("List Products");
		mntmListProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowListProductDialog();
			}
		});
		mnProducts.add(mntmListProducts);
		
		JMenuItem mntmAddProduct = new JMenuItem("Add Product");
		mntmAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowAddProductDialog();
			}
		});
		mnProducts.add(mntmAddProduct);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
