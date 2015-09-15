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

public class pnlProduct extends JOmazanPanel {
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

	public void RefreshContent()
	{
		setProductID(ProductID);
	}
	
	public void setProductID(int productID) {
		ProductID = productID;
		lblAddProduct.setText("Edit Product");
		Product product = ProductBL.GetProduct(GetPU(),ProductID);
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
	/**
	 * Create the panel.
	 */
	public pnlProduct() {
		setLayout(null);
		
		 lblAddProduct = new JLabel("Product Details");
		lblAddProduct.setBounds(10, 11, 150, 22);
		lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddProduct);
		
		JLabel lblNewLabel = new JLabel("Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(15, 44, 165, 22);
		add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(398, 44, 165, 22);
		add(lblName);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDetail.setBounds(15, 83, 81, 22);
		add(lblDetail);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(15, 143, 133, 22);
		add(lblDescription);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(15, 281, 165, 22);
		add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(321, 281, 124, 22);
		add(lblQuantity);
		
		txtCode = new JTextField();
		txtCode.setBounds(140, 43, 252, 29);
		add(txtCode);
		txtCode.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(544, 43, 252, 29);
		add(txtName);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(150, 280, 112, 29);
		add(txtPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(438, 280, 112, 29);
		add(txtQuantity);
		
		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActive.setBounds(586, 281, 124, 22);
		add(lblActive);
		
		chkActive = new JCheckBox("");
		chkActive.setSelected(true);
		chkActive.setBounds(699, 281, 97, 23);
		add(chkActive);
		
		 txtDetail = new JTextArea();
		 txtDetail.setLineWrap(true);
		txtDetail.setBounds(150, 77, 685, 55);
		add(txtDetail);
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(153, 144, 682, 126);
		add(txtDescription);

	}
}
