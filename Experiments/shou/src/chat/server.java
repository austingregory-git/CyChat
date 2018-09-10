package chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class server {
	
	
	
	public static void main (String [] args)
	{
		try 
		{
			ServerSocket ss = new ServerSocket(1204);
			

			ArrayList<ClientThread> ar = new ArrayList<>();
			String name = "";
			
			
			while(true)
			{
				

				Socket socket = ss.accept();
				System.out.println("Please type your name");
				Scanner scan = new Scanner(System.in);
				name = scan.next();
				System.out.println("connected to Client: " + name);
				Thread t= new ClientThread(socket , name ,ar);
				ar.add((ClientThread) t);
				t.start();
				
			}
			
			
		}
		catch(Exception e)
		{
			
		}
	}
	
}

class ClientThread extends Thread
{

	private Socket s;
	public String name;
	ArrayList<ClientThread> arr;
	
	public DataInputStream din;
	public DataOutputStream dout;
	
	
	ClientThread(Socket s , String name , ArrayList<ClientThread> ar)
	{
		this.s = s;
		this.name = name;
		arr = ar;
		try {
			din = new DataInputStream(this.s.getInputStream());
			dout = new DataOutputStream(this.s.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@Override
	public void run() {
		 
		try
		{
			String msgin ="" , msgout = "";
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			dout.writeUTF("this is client " + name);
			dout.flush();
		while(true)
		{
			
			
			msgin = din.readUTF();
			System.out.println( name + ": " +msgin);
			
//			msgout = br.readLine();
//			dout.writeUTF(msgout); 
//			dout.flush();
////			
			for(ClientThread t : arr)
			{
				if(!t.s.equals(this.s))
				{
				DataOutputStream temp = new DataOutputStream(t.s.getOutputStream());
				temp.writeUTF("client " + name + "  " + msgin);
				temp.flush();
				}
			}
		}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
//		
		
	}
	
	
	
}