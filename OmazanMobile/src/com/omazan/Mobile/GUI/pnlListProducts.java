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

public class pnlListProducts extends JPanel {
	private JTextField txtCode;
	private JTextField txtName;
	private JTable tblProducts;
	
	public void LoadProducts()
	{
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = {"ID","Code", "Name", "Quantity", "Active ?"};
		model.setColumnIdentifiers(columnNames);
		tblProducts.setModel(model);
		
		List<Product> products = ProductBL.ListProducts();
		if( products != null && products.size() > 0)
		{
			for(Product product : products)
			{
				model.addRow(new Object[]{ product.getId(), product.getCode(), product.getName(), product.getQuantity(), product.isIsActive()});
			}			
		}
		
	}
	
	public void SearchProducts()
	{
		DefaultTableModel model = new DefaultTableModel();
		String[] columnNames = {"ID","Code", "Name", "Quantity", "Active ?"};
		model.setColumnIdentifiers(columnNames);
		tblProducts.setModel(model);
		
		List<Product> products = ProductBL.SearchProduct(txtCode.getText(), txtName.getText());
		if( products != null && products.size() > 0)
		{
			for(Product product : products)
			{
				model.addRow(new Object[]{ product.getId(), product.getCode(), product.getName(), product.getQuantity(), product.isIsActive()});
			}				
		}
	}
	
	public void EditProduct()
	{
		int selectedRowindex = tblProducts.getSelectedRow();
		if( selectedRowindex >= 0)
		{
			int pID = Integer.parseInt( tblProducts.getModel().getValueAt(selectedRowindex, 0).toString());
			com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowEditProductDialog(pID);
		}
	}

	public void CreateNewProduct()
	{
		//com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowAddProductDialog();
	}
	
	/**
	 * Create the panel.
	 */
	public pnlListProducts() {
		setLayout(null);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setBounds(10, 11, 94, 25);
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblProducts);
		
		JButton btnAdd = new JButton("Create New Product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewProduct();
			}
		});
		btnAdd.setBounds(10, 74, 169, 23);
		add(btnAdd);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadProducts();
			}
		});
		btnRefresh.setBounds(538, 74, 89, 23);
		add(btnRefresh);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProduct();
			}
		});
		btnEdit.setBounds(647, 74, 89, 23);
		add(btnEdit);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 108, 747, 50);
		add(panel);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(10, 11, 149, 24);
		panel.add(lblCode);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(303, 11, 149, 24);
		panel.add(lblName);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchProducts();
			}
		});
		btnSearch.setBounds(629, 12, 89, 23);
		panel.add(btnSearch);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(73, 13, 220, 20);
		panel.add(txtCode);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(378, 13, 241, 20);
		panel.add(txtName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 757, 319);
		add(scrollPane);
		
		tblProducts = new JTable();
		tblProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProducts.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tblProducts);

	}

}
