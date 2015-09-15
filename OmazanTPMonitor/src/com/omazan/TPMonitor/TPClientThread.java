package com.omazan.TPMonitor;

import java.io.*;
import java.net.*;
import java.util.*;

public class TPClientThread extends Thread {

	private BufferedReader inputStream = null;
	private PrintStream outputStream = null;
	private Socket clientSocket = null;
	
	public int clientIdentity = -1;
	private TPServer tpServer = null;
	private boolean closed = false;
	
	public boolean HeartBeatFound = false;
	public boolean isInTransaction = false;
	public int PrepareResult = 0; 
	
	
	public TPClientThread(int id) {
		clientIdentity = id;
	}

	public boolean SetupThread(TPServer ser, Socket cSocket) {
		try {
			tpServer = ser;
			clientSocket = cSocket;
			inputStream = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
			outputStream = new PrintStream(clientSocket.getOutputStream());
			return true;
		} catch (Exception exc) {
			tpServer = null;
			clientSocket = null;
			inputStream = null;
			outputStream = null;
			return false;
		}
	}

	public void RequestHeartBeat()
	{
		HeartBeatFound = false;
		outputStream.println("HB");
		WriteMessage("HB Request sent to Client " + clientIdentity);
	}
	
	public void RequestPrepare()
	{
		isInTransaction = true;
		PrepareResult = 0;
		outputStream.println("PP");
		WriteMessage("Prepare Request sent to Client " + clientIdentity);
	}
	
	public void SendDecision(String decision)
	{
		outputStream.println("DN:" + decision);
		WriteMessage("Decision "+ decision +" sent to Client " + clientIdentity);
	}
	
	public void CloseConnection()
	{
		closed = true;
		WriteMessage("Connection closed for Client " + clientIdentity);
		clientIdentity = -1;
	}
	
	public void run() 
    {       
		String responseLine;
		outputStream.println("ID:" + clientIdentity);
           

       while(!closed)
       {
    	   try
    	   {
    		   responseLine = inputStream.readLine();
    		   if( responseLine != null )
    		   {
	    		   if(responseLine.startsWith("RT"))
	    		   {
	    			   if(!tpServer.isBusy() && tpServer.setBusyIfAvailable(clientIdentity))
	    			   {
	    				   WriteMessage("Client with ID " + clientIdentity + " has initiated a transaction, Server accepted transaction request");
	    				   outputStream.println("RC:ACCEPTED");
	    				   Initiate2PC();
	    			   }
	    			   else
	    			   {
	    				   WriteMessage("Client with ID " + clientIdentity + " has requested a transaction, server rejecting with busy signal");
	    				   outputStream.println("RC:BUSY");
	    			   }
	    		   }
	    		   else if(responseLine.startsWith("FN"))
	    		   {
	    			   CloseConnection();
	    		   }
	    		   else if(responseLine.startsWith("HB"))
	    		   {
	    			   HeartBeatFound = true;
	    			   WriteMessage("Client with ID " + clientIdentity + " has responded to HeartBeat");
	    		   }
	    		   else if(responseLine.startsWith("PP"))
	    		   {
	    			   String result= responseLine.substring(3);
	    			   if( result.startsWith("C"))
	    				   PrepareResult = 1;
	    			   else 
	    				   PrepareResult = 2;	  
	    			   
	    			   WriteMessage("Client with ID " + clientIdentity + " has responded to Prepare with " + result);
	    		   }
    		   }
    		   else
    			   CloseConnection();
    	   }
    	   catch(Exception exc)
    	   {
    		   
    	   }
    	   
       }
    }
	
	
	public void Initiate2PC()
	{
		try {

			SendHeartBeatToAll(); //1

			Thread.sleep(200);
			
			SendPrepareToAliveUsers(); //2
			
			WaitForAtmost10Seconds();
			
			String Decision = GetTransactionDecision(); //3
			
			for (int i = 0; i < tpServer.ThreadListSize; i++) {
				if (tpServer.tpClientThreads[i] != null
						&& tpServer.tpClientThreads[i].clientIdentity > -1 ) {
					
					if( tpServer.tpClientThreads[i].clientIdentity == tpServer.TransactionOwner
							|| tpServer.tpClientThreads[i].isInTransaction)
						tpServer.tpClientThreads[i].SendDecision(Decision);    //4
					
					tpServer.tpClientThreads[i].isInTransaction = false;
					tpServer.tpClientThreads[i].HeartBeatFound = false;
					tpServer.tpClientThreads[i].PrepareResult = 0;
					
				}
			}
			
		} catch (InterruptedException e) {

		}
		
		tpServer.setAvailable(); //5
	}
	
	public void WaitForAtmost10Seconds()
	{
		try {
			int WaitedSoFarMS =0;
			boolean allResponded = false;
			
			do
			{
				Thread.sleep(200);
				
				allResponded = true;
				for (int i = 0; i < tpServer.ThreadListSize; i++) {
					if (tpServer.tpClientThreads[i] != null
							&& tpServer.tpClientThreads[i].isInTransaction) {
						
						if(tpServer.tpClientThreads[i].PrepareResult == 0)
						{
							allResponded = false;
						}
					}
				}
				
				WaitedSoFarMS += 200;
			}while(!allResponded && WaitedSoFarMS < 10000);
			
		} catch (InterruptedException e) {

		}
	}

	private String GetTransactionDecision() {
		String failedClients = "";
		boolean globalCommit = true;
		
		for (int i = 0; i < tpServer.ThreadListSize; i++) {
			if (tpServer.tpClientThreads[i] != null
					&& tpServer.tpClientThreads[i].isInTransaction) {
				
				if(tpServer.tpClientThreads[i].PrepareResult != 1)
				{
					globalCommit = false;
					failedClients += "" + tpServer.tpClientThreads[i].clientIdentity;
				}
			}
		}
		String Decision = "ABORT";
		if( globalCommit)
		{
			
			Decision = "COMMIT";
			 WriteMessage("Transaction completed with COMMIT");
		}
		else
		{
			Decision = "ABORT";
			WriteMessage("Transaction completed with ABORT, following clients failed " + failedClients);
		}
		return Decision;
	}

	private void SendPrepareToAliveUsers() {
		for (int i = 0; i < tpServer.ThreadListSize; i++) {
			if (tpServer.tpClientThreads[i] != null
					&& tpServer.tpClientThreads[i].clientIdentity > -1
					&& tpServer.tpClientThreads[i].clientIdentity != tpServer.TransactionOwner) {
				
				if(tpServer.tpClientThreads[i].HeartBeatFound)
					tpServer.tpClientThreads[i].RequestPrepare();
				else
				{
					WriteMessage("Client " + tpServer.tpClientThreads[i].clientIdentity + " is removed because heartbeat not found.");
					tpServer.tpClientThreads[i].isInTransaction = false;
					tpServer.tpClientThreads[i].clientIdentity = -1;
					
				}
			}
		}
	}

	private void SendHeartBeatToAll() {
		for (int i = 0; i < tpServer.ThreadListSize; i++) {
			if (tpServer.tpClientThreads[i] != null
					&& tpServer.tpClientThreads[i].clientIdentity > -1
					&& tpServer.tpClientThreads[i].clientIdentity != tpServer.TransactionOwner) {
				tpServer.tpClientThreads[i].RequestHeartBeat();
			}
		}
	}
	
	public void WriteMessage(String Msg)
	{
		frmMain.ApplicationForm.WriteTPStatus(Msg);
	}
}
