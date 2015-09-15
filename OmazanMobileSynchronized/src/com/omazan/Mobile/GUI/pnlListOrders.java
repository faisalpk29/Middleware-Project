package com.omazan.Mobile.GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.util.List;

import javax.swing.ListSelectionModel;

import com.omazan.Mobile.BL.*;
import com.omazan.Mobile.Entities.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pnlListOrders extends JOmazanPanel {
	private JTable tblProducts;
	
	public void LoadProducts()
	{
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = {"Id","Customer Name","Order Date", "Deleivery Status",  "Total Amount"};
		model.setColumnIdentifiers(columnNames);
		tblProducts.setModel(model);
		
		List<Order> orders = OrderBL.ListOrders(GetPU());
		if( orders != null && orders.size() > 0)
		{
			for(Order order : orders)
			{
				model.addRow(new Object[]{ order.getId(), order.getOrderedBy().getUsername(), order.getOrderDate(), order.getDeleiveryStatus(), order.getPrice()});
			}			
		}
		
	}
	
	public void RefreshContent()
	{
		LoadProducts();
	}
	
	public void EditProduct()
	{
		int selectedRowindex = tblProducts.getSelectedRow();
		if( selectedRowindex >= 0)
		{
			int pID = Integer.parseInt( tblProducts.getModel().getValueAt(selectedRowindex, 0).toString());
			com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowEditOrderDialog(pID);
		}
	}
	
	/**
	 * Create the panel.
	 */
	public pnlListOrders() {
		setLayout(null);
		
		JLabel lblProducts = new JLabel("Orders");
		lblProducts.setBounds(10, 11, 94, 25);
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblProducts);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadProducts();
			}
		});
		btnRefresh.setBounds(536, 44, 89, 23);
		add(btnRefresh);
		
		JButton btnEdit = new JButton("View Details");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProduct();
			}
		});
		btnEdit.setBounds(635, 44, 137, 23);
		add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 845, 246);
		add(scrollPane);
		
		tblProducts = new JTable();
		tblProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProducts.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tblProducts);

	}

}
