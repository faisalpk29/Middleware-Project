package com.omazan.TPClient;

import java.io.*;
import java.net.*;

public class TPClient implements Runnable {

	private Socket clientSocket = null;
	private PrintStream os = null;
	private BufferedReader is = null;
	private String clientIdentity = "";
	private int ServerResponse = -1;
	private boolean closed;
	public TPClient() {

	}

	public boolean SetupThread(Socket cSocket) {
		try {
			clientSocket = cSocket;
			
			os = new PrintStream(clientSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
	
	public void RequestTransaction()
	{
		os.println("RT");
		com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Request signal sent to TP Monitor");
	}
	
	public void CloseConnection()
	{
		os.println("FN");
		com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Finish Signal sent to TP Monitor");
		
		closed = true;
	}

	public static TPClient ConnectToTPMontior() {
		int port_number = 1111;
		String host = "localhost";

		try {
			Socket cSocket = new Socket(host, port_number);
			TPClient tpClient = new TPClient();
			if (tpClient.SetupThread(cSocket)) {
				new Thread(tpClient).start();
				return tpClient;
			} 
			
		} catch (Exception e) {
			
		}

		return null;

	}


	public void run() {
		String responseLine;
		try {
			while (!closed) {
				responseLine = is.readLine();
				if(responseLine.startsWith("ID"))
				{
					clientIdentity = responseLine.substring(2);
					com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("TP Client Identity is " + clientIdentity);
				}
				else if(responseLine.startsWith("HB"))
				{
					os.println("HB");
					com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Heartbeat request received, acknowledged" );					
				}
				else if(responseLine.startsWith("PP"))
				{
					String pResult = "ABORT" ;//( Math.random() > 0.5d ? "COMMIT" : "ABORT");					
					os.println("PP:" + pResult);
					com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Prepare request received, acknowledged with " + pResult );	
				}
				else if(responseLine.startsWith("DN"))
				{
					com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Final Action received, " + responseLine.substring(3));
				}
				else if(responseLine.startsWith("RC"))
				{
					//String responseCode = Integer.parseInt(responseLine.substring(3));
					com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Transaction request decision, " + responseLine.substring(3));
				}
			}
			
			os.close();
			is.close();
			clientSocket.close();
			com.omazan.TPClient.frmMain.ApplicationForm.WriteTPStatus("Connection closed.");
			
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
	}
	

	public synchronized int getServerResponse() {
		return ServerResponse;
	}

	public synchronized void setServerResponse(int serverResponse) {
		ServerResponse = serverResponse;
	}

}
