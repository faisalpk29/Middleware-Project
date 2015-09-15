package com.omazan.Mobile.GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.omazan.Mobile.BL.*;
import com.omazan.Mobile.Entities.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

public class pnlProduct extends JPanel {
	private JTextField txtCode;
	private JTextField txtName;
	private JTextArea txtDetail;
	private JTextArea txtDescription;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JCheckBox chkActive;
	private JLabel lblAddProduct;

	private int ProductID = -1;

	
	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
		lblAddProduct.setText("Edit Product");
		Product product = ProductBL.GetProduct(ProductID);
		if( product != null )
		{
			txtCode.setText(product.getCode());
			txtName.setText(product.getName());
			txtDetail.setText(product.getDetails());
			txtDescription.setText(product.getDescription());
			txtPrice.setText(String.valueOf(product.getPrice()));
			txtQuantity.setText(String.valueOf(product.getQuantity()));
			txtCode.setEditable(false);
			chkActive.setSelected(product.isIsActive());
		}
	}
	
	public void AddProduct() {
		
	}

	public void ClearData() {
		com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowListProductDialog();
	}
	/**
	 * Create the panel.
	 */
	public pnlProduct() {
		setLayout(null);
		
		 lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setBounds(10, 11, 112, 22);
		lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddProduct);
		
		JLabel lblNewLabel = new JLabel("Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(41, 62, 165, 22);
		add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(41, 104, 165, 22);
		add(lblName);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDetail.setBounds(41, 144, 165, 22);
		add(lblDetail);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(41, 236, 165, 22);
		add(lblDescription);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(41, 419, 165, 22);
		add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(41, 462, 124, 22);
		add(lblQuantity);
		
		txtCode = new JTextField();
		txtCode.setBounds(182, 61, 252, 29);
		add(txtCode);
		txtCode.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(182, 103, 252, 29);
		add(txtName);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(182, 419, 112, 29);
		add(txtPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(182, 461, 112, 29);
		add(txtQuantity);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct();
			}
		});
		btnSave.setBounds(182, 548, 89, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearData();
			}
		});
		btnCancel.setBounds(283, 548, 89, 23);
		add(btnCancel);
		
		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActive.setBounds(41, 498, 124, 22);
		add(lblActive);
		
		chkActive = new JCheckBox("");
		chkActive.setSelected(true);
		chkActive.setBounds(182, 497, 97, 23);
		add(chkActive);
		
		 txtDetail = new JTextArea();
		 txtDetail.setLineWrap(true);
		txtDetail.setBounds(182, 145, 580, 70);
		add(txtDetail);
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(182, 237, 580, 161);
		add(txtDescription);

	}
}
