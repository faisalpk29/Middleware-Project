package com.omazan.TPMonitor;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TPServer  implements Runnable {
    
    
	public final int ThreadListSize = 20;
	public TPClientThread[] tpClientThreads;
	public boolean closed = false;
	
	private ServerSocket serverSocket = null;
	
	private boolean busy = false;
	public int TransactionOwner = -1;
	
	public void run() 
	{		
		Socket clientSocket = null;		
		int port_number = 1111;
		

		try {
			serverSocket = new ServerSocket(port_number);
			WriteMsg("Server started on port " + port_number);
		} catch (IOException e) {
			System.out.println(e);
		}

		while (!closed) {
			try {
				clientSocket = serverSocket.accept();
				int clientID = AddTPClientThread(this, clientSocket);
				if (clientID > -1) {
					WriteMsg("New client connected with ID " + clientID);
				}
				else
					WriteMsg("New client with ID " + clientID + " was unable to connect.");

			} catch (IOException e) {
			}
		}
		
		
	}

	public int AddTPClientThread(TPServer server, Socket cSocket) {
		for (int i = 0; i < ThreadListSize; i++) {
			
			if (tpClientThreads[i] != null)
			{
				if( tpClientThreads[i].clientIdentity == -1)
					tpClientThreads[i] = null;
			}
				
				
			if (tpClientThreads[i] == null) {
				TPClientThread tpClientThread = new TPClientThread(i);
				if (tpClientThread.SetupThread(server, cSocket)) {
					tpClientThreads[i] = tpClientThread;
					tpClientThread.start();
					return i;
				} else
					return -1;

			}
			
		}
		return -1;
	}
	
	public void CloseTPServer()
	{
		try
	       {
	         serverSocket.close();
	         
	       }catch(Exception e1){}
		closed = true;
	}

	public void WriteMsg(String Msg)
	{
		frmMain.ApplicationForm.WriteTPStatus(Msg);
	}
		
	public TPServer() {
		tpClientThreads = new TPClientThread[ThreadListSize];
		for (int i = 0; i < ThreadListSize; i++) {
			tpClientThreads[i] = null;
		}
		
	}

	public synchronized boolean isBusy() {
		return busy;
	}
	
	public synchronized void setAvailable() {
		busy=false;
		TransactionOwner = -1;
	}

	public synchronized boolean setBusyIfAvailable(int owner) {
		if( !busy)
		{
			this.busy = true;
			TransactionOwner = owner;
			return true;
		}
		return false;
	}

	
	
}
