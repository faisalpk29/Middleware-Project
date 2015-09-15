package com.omazan.TPMonitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmMain extends JFrame {

	private JPanel contentPane;

	public static frmMain ApplicationForm = null;
	TPServer tpServer = new  TPServer();
	
	public synchronized void WriteTPStatus(String Msg)
	{
		textArea.setText(   textArea.getText() + "\n" + Msg );
	}
	
	public void StartTPServer()
	{
		new Thread(tpServer).start();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					ApplicationForm = frame;
					ApplicationForm.StartTPServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void CloseTPServer()
	{
		tpServer.CloseTPServer();
		WriteTPStatus("Server stopped.");
	}
	
	private JTextArea textArea;
	private JButton btnNewButton;
	public frmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 708, 351);
		getContentPane().add(scrollPane);
		
		 textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnNewButton = new JButton("Stop Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CloseTPServer();
			}
		});
		btnNewButton.setBounds(20, 362, 107, 23);
		contentPane.add(btnNewButton);
	}

}
