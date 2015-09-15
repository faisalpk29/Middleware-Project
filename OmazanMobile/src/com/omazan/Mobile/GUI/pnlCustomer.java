package com.omazan.Mobile.GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.omazan.Mobile.Entities.*;
import com.omazan.Mobile.BL.*;

import java.awt.Color;

import javax.swing.JCheckBox;

public class pnlCustomer extends JPanel {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtStreet1;
	private JTextField txtStreet2;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZipcode;
	private JTextField txtCountry;
	private JCheckBox chkActive;
	private JLabel lblAddCustomer;
	
	private int CustomerID = -1;

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
		lblAddCustomer.setText("Edit Customer");
		Customer customer = CustomerBL.GetCustomer(customerID);
		if( customer != null )
		{
			txtFirstName.setText(customer.getFirstName());
			txtLastName.setText(customer.getLastName());
			txtUsername.setText(customer.getUsername());
			txtUsername.setEditable(false);
			txtPassword.setText("");
			txtEmail.setText(customer.getEmail());
			txtPhone.setText(customer.getPhone());
			txtStreet1.setText(customer.getAddr_Street1());
			txtStreet2.setText(customer.getAddr_Street2());
			txtCity.setText(customer.getAddr_City());
			txtState.setText(customer.getAddr_State());
			txtZipcode.setText(customer.getAddr_Zipcode());
			txtCountry.setText(customer.getAddr_Country());
			chkActive.setSelected(customer.isIsActive());
		}
	}

	public void AddCustomer() {
		
	}

	public void ClearData() {
		com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowListCustomerDialog();
	}

	/**
	 * Create the panel.
	 */
	public pnlCustomer() {
		setLayout(null);

		 lblAddCustomer = new JLabel("Add Customer");
		lblAddCustomer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddCustomer.setBounds(10, 11, 170, 29);
		add(lblAddCustomer);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 81, 157, 23);
		add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(47, 115, 157, 23);
		add(lblLastName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(47, 149, 157, 23);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(47, 183, 157, 23);
		add(lblPassword);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(47, 217, 157, 23);
		add(lblEmail);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(47, 251, 157, 23);
		add(lblPhone);

		JLabel lblStreet = new JLabel("Street 1");
		lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStreet.setBounds(47, 285, 157, 23);
		add(lblStreet);

		JLabel lblStreet_1 = new JLabel("Street 2");
		lblStreet_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStreet_1.setBounds(47, 319, 157, 23);
		add(lblStreet_1);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCity.setBounds(47, 353, 157, 23);
		add(lblCity);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(47, 387, 157, 23);
		add(lblState);

		JLabel lblZipcode = new JLabel("Zipcode");
		lblZipcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZipcode.setBounds(47, 421, 157, 23);
		add(lblZipcode);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCountry.setBounds(47, 455, 157, 23);
		add(lblCountry);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(206, 84, 212, 20);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(206, 115, 212, 20);
		add(txtLastName);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(206, 149, 212, 20);
		add(txtUsername);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(206, 183, 212, 20);
		add(txtPassword);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(206, 220, 212, 20);
		add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(206, 254, 212, 20);
		add(txtPhone);

		txtStreet1 = new JTextField();
		txtStreet1.setColumns(10);
		txtStreet1.setBounds(206, 288, 418, 20);
		add(txtStreet1);

		txtStreet2 = new JTextField();
		txtStreet2.setColumns(10);
		txtStreet2.setBounds(206, 319, 418, 20);
		add(txtStreet2);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(206, 353, 212, 20);
		add(txtCity);

		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(206, 387, 212, 20);
		add(txtState);

		txtZipcode = new JTextField();
		txtZipcode.setColumns(10);
		txtZipcode.setBounds(206, 421, 212, 20);
		add(txtZipcode);

		txtCountry = new JTextField();
		txtCountry.setColumns(10);
		txtCountry.setBounds(206, 455, 212, 20);
		add(txtCountry);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCustomer();
			}
		});
		btnSave.setBounds(206, 513, 89, 23);
		add(btnSave);

		JButton btnClear = new JButton("Cancel");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearData();
			}
		});
		btnClear.setBounds(305, 513, 89, 23);
		add(btnClear);

		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(231, 40, 297, 23);
		add(lblMessage);
		
		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActive.setBounds(47, 489, 46, 14);
		add(lblActive);
		
		chkActive = new JCheckBox("");
		chkActive.setSelected(true);
		chkActive.setBounds(206, 485, 97, 23);
		add(chkActive);

	}
}
