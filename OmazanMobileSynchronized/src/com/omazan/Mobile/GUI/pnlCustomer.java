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

public class pnlCustomer extends JOmazanPanel {
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
	
	public void RefreshContent()
	{
		setCustomerID( CustomerID);
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
		lblAddCustomer.setText("Edit Customer");
		Customer customer = CustomerBL.GetCustomer(GetPU(),customerID);
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
		Customer customer = new Customer(txtUsername.getText(),
				txtPassword.getText(), txtFirstName.getText(),
				txtLastName.getText(), txtEmail.getText(), txtPhone.getText(),
				txtStreet1.getText(), txtStreet2.getText(), txtCity.getText(),
				txtState.getText(), txtZipcode.getText(), txtCountry.getText());
				customer.setIsActive(chkActive.isSelected());
		
			customer.setID(CustomerID);
			if (CustomerBL.UpdateCustomer(GetPU(),customer)) {
				JOptionPane.showMessageDialog(null,
						"Customer updated successfully.");
				com.omazan.Mobile.GUI.frmMain.ApplicationForm.ShowListCustomerDialog();
			} else {
				JOptionPane.showMessageDialog(null, "Could not update customer.");
			}
		
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
		lblNewLabel.setBounds(47, 40, 157, 23);
		add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(452, 40, 157, 23);
		add(lblLastName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(47, 66, 157, 23);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(452, 63, 157, 23);
		add(lblPassword);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(47, 89, 157, 23);
		add(lblEmail);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(452, 86, 157, 23);
		add(lblPhone);

		JLabel lblStreet = new JLabel("Street 1");
		lblStreet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStreet.setBounds(47, 136, 157, 23);
		add(lblStreet);

		JLabel lblStreet_1 = new JLabel("Street 2");
		lblStreet_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStreet_1.setBounds(47, 167, 157, 23);
		add(lblStreet_1);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCity.setBounds(47, 201, 157, 23);
		add(lblCity);

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(452, 201, 157, 23);
		add(lblState);

		JLabel lblZipcode = new JLabel("Zipcode");
		lblZipcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZipcode.setBounds(47, 226, 157, 23);
		add(lblZipcode);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCountry.setBounds(452, 226, 157, 23);
		add(lblCountry);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(206, 43, 212, 20);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(611, 40, 212, 20);
		add(txtLastName);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(206, 66, 212, 20);
		add(txtUsername);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(611, 63, 212, 20);
		add(txtPassword);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(206, 92, 212, 20);
		add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(611, 89, 212, 20);
		add(txtPhone);

		txtStreet1 = new JTextField();
		txtStreet1.setColumns(10);
		txtStreet1.setBounds(206, 139, 418, 20);
		add(txtStreet1);

		txtStreet2 = new JTextField();
		txtStreet2.setColumns(10);
		txtStreet2.setBounds(206, 167, 418, 20);
		add(txtStreet2);

		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(206, 201, 212, 20);
		add(txtCity);

		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(611, 201, 212, 20);
		add(txtState);

		txtZipcode = new JTextField();
		txtZipcode.setColumns(10);
		txtZipcode.setBounds(206, 226, 212, 20);
		add(txtZipcode);

		txtCountry = new JTextField();
		txtCountry.setColumns(10);
		txtCountry.setBounds(611, 226, 212, 20);
		add(txtCountry);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCustomer();
			}
		});
		btnSave.setBounds(341, 296, 89, 23);
		add(btnSave);

		JButton btnClear = new JButton("Cancel");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearData();
			}
		});
		btnClear.setBounds(439, 296, 89, 23);
		add(btnClear);

		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(256, 11, 297, 23);
		add(lblMessage);
		
		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActive.setBounds(47, 268, 46, 14);
		add(lblActive);
		
		chkActive = new JCheckBox("");
		chkActive.setSelected(true);
		chkActive.setBounds(197, 268, 97, 23);
		add(chkActive);

	}
}
