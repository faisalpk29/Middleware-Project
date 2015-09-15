package com.omazan.TPClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.Socket;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class frmMain extends JFrame {

	private JPanel contentPane;

	public static frmMain ApplicationForm = null;
	private TPClient tpCLient;
	public void ConnectToTPMonitor()
	{
		tpCLient = TPClient.ConnectToTPMontior();
		if(tpCLient == null)
		{
			WriteTPStatus("Unable to Connect to TPMonitor.");
		}
		else
		{
			WriteTPStatus("Connected to TPMonitor Successfully.");
		}

	}
	
	public void CloseConnection()
	{
		if(tpCLient != null)
		{
			tpCLient.CloseConnection();
			tpCLient = null;
		}
	}
	
	public void RequestTransaction()
	{
		if(tpCLient != null)
			tpCLient.RequestTransaction();
		else
			WriteTPStatus("Not connected to TP Monitor");
	}
	
	public synchronized void WriteTPStatus(String Msg)
	{
		textArea.setText(   textArea.getText() + "\n" + Msg );
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					ApplicationForm = frame;
					ApplicationForm.ConnectToTPMonitor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	/**
	 * Create the frame.
	 */
	public frmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 textArea = new JTextArea();
		textArea.setBounds(10, 11, 387, 152);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequestTransaction();
			}
		});
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 300, 200);
		contentPane.add(scrollPane);
		btnNewButton.setBounds(67, 203, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CloseConnection();
			}
		});
		btnNewButton_1.setBounds(178, 203, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
