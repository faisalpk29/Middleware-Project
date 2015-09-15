package com.omazan.Mobile.GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.omazan.Mobile.Entities.*;
import com.omazan.Mobile.BL.CustomerBL;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JScrollBar;
import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class pnlListCustomers extends JPanel {
	private JTable tblCustomers;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;

	public void LoadCustomers()
	{
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = {"ID","First Name", "Last Name", "Username", "Active ?"};
		model.setColumnIdentifiers(columnNames);
		tblCustomers.setModel(model);
		
		List<Customer> customers = CustomerBL.ListCustomers();
		if( customers != null && customers.size() > 0)
		{
			for(Customer customer : customers)
			{
				model.addRow(new Object[]{ customer.getID(), customer.getFirstName(), customer.getLastName(), customer.getUsername(), customer.isIsActive()});
			}			
		}
		
	}
	
	public void SearchCustomers()
	{
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = {"ID","First Name", "Last Name", "Username", "Active ?"};
		model.setColumnIdentifiers(columnNames);
		tblCustomers.setModel(model);
		
		List<Customer> customers = CustomerBL.SearchCustomer(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText());
		if( customers != null && customers.size() > 0)
		{
			for(Customer customer : customers)
			{
				model.addRow(new Object[]{ customer.getID(), customer.getFirstName(), customer.getLastName(), customer.getUsername(), customer.isIsActive()});
			}			
		}
	}
	
	
	
	public void EditCustomer()
	{
		int selectedRowindex = tblCustomers.getSelectedRow();
		if( selectedRowindex >= 0)
		{
			int CustomerID = Integer.parseInt( tblCustomers.getModel().getValueAt(selectedRowindex, 0).toString());
			com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowEditCustomerDialog(CustomerID);
		}
	}
	
	public void CreateNewCustomer()
	{
		//com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowAddCustomerDialog();
	}
	
	/**
	 * Create the panel.
	 */
	public pnlListCustomers() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 136, 32);
		add(lblNewLabel);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCustomer();
			}
		});
		btnEdit.setBounds(678, 62, 89, 23);
		add(btnEdit);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadCustomers();
			}
		});
		btnRefresh.setBounds(579, 62, 89, 23);
		add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 757, 319);
		add(scrollPane);
		
		tblCustomers = new JTable();
		scrollPane.setViewportView(tblCustomers);
		tblCustomers.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnCreateNewCustomer = new JButton("Create New Customer");
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateNewCustomer();
			}
		});
		btnCreateNewCustomer.setBounds(10, 62, 169, 23);
		add(btnCreateNewCustomer);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 96, 747, 74);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 11, 149, 24);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 39, 149, 24);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(333, 11, 149, 24);
		panel.add(lblUsername);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchCustomers();
			}
		});
		btnSearch.setBounds(569, 40, 89, 23);
		panel.add(btnSearch);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(73, 13, 241, 20);
		panel.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(73, 41, 241, 20);
		panel.add(txtLastName);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(417, 13, 241, 20);
		panel.add(txtUsername);

	}
}
