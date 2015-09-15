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

public class pnlOrder extends JOmazanPanel {
	private JTable tblProducts;
	private int OrderID = -1;
	
	public void setOrderID(int orderID) {
		OrderID = orderID;
		Order order = OrderBL.GetOrder(GetPU(),orderID);
		if( order != null )
		{
			txtOrderId.setText("" + order.getId());
			txtOrderDate.setText("" + order.getOrderDate());
			txtCost.setText("" + order.getPrice());
			txtStatus.setText(order.getDeleiveryStatus());
			LoadProducts(order.getItems());
		}
	}
	
	
	public void RefreshContent()
	{
		setOrderID(OrderID);
	}
	
	private void LoadProducts(List<OrderLine> items)
	{
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = {"Product Code","Name","Quantity", "Cost"};
		model.setColumnIdentifiers(columnNames);
		tblProducts.setModel(model);
		
		if( items != null && items.size() > 0)
		{
			for(OrderLine item : items)
			{
				model.addRow(new Object[]{ item.getItem().getCode(), item.getItem().getName(), item.getQuantity(), item.getPrice()});
			}			
		}
		
	}
	
	
	private JLabel txtOrderId;
	private JLabel txtOrderDate;
	private JLabel txtCost;
	private JLabel txtStatus;
	/**
	 * Create the panel.
	 */
	public pnlOrder() {
		setLayout(null);
		
		JLabel lblProducts = new JLabel("Order Details");
		lblProducts.setBounds(10, 11, 159, 25);
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblProducts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 32, 631, 283);
		add(scrollPane);
		
		tblProducts = new JTable();
		tblProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProducts.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tblProducts);
		
		JLabel lblOrderId = new JLabel("Order Id");
		lblOrderId.setBounds(10, 47, 46, 14);
		add(lblOrderId);
		
		JLabel lblOrderDate = new JLabel("Order Date");
		lblOrderDate.setBounds(10, 72, 74, 14);
		add(lblOrderDate);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(10, 97, 46, 14);
		add(lblCost);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 122, 46, 14);
		add(lblStatus);
		
		 txtOrderId = new JLabel("New label");
		txtOrderId.setBounds(88, 47, 125, 14);
		add(txtOrderId);
		
		 txtOrderDate = new JLabel("New label");
		txtOrderDate.setBounds(88, 72, 125, 14);
		add(txtOrderDate);
		
		 txtCost = new JLabel("New label");
		txtCost.setBounds(88, 97, 125, 14);
		add(txtCost);
		
		 txtStatus = new JLabel("New label");
		txtStatus.setBounds(88, 122, 125, 14);
		add(txtStatus);

	}
}
