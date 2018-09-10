package demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	private static ArrayList<Socket> clients = new ArrayList<Socket>();
	
	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;
		int clientNum = 0;
		
		try
		{
			serverSocket = new ServerSocket(4040);
			System.out.println(serverSocket);
		} catch (IOException e)
		{
			System.out.println("Could not listen on port: 4040");
			e.printStackTrace();
			System.exit(-1);
		}
		
		while(true)
		{
			Socket clientSocket = null;
			
			try
			{
				clientSocket = serverSocket.accept(); // waiting for client
				clients.add(clientSocket);
				
				System.out.println("Server connected to client " + clientNum);
				
				ClientIO c = new ClientIO(clientSocket, clientNum);
				
				System.out.println("40");
				Thread t = new Thread(c);
				t.start();
				System.out.println("43");
				
			} catch (IOException e)
			{
				System.out.println("SERVER SIDE: clientSocket excpetion");
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}

class ClientIO implements Runnable
{
	Socket s;
	int n;
	
	ClientIO(Socket socket, int num)
	{
		s = socket;
		n = num;
	}
	
	@Override
	public void run()
	{		
		try
		{
			//TODO handle the inputStream and send the input to another client
			
			String str = null;
			while(true)
			{
				str = "" + n;
				s.getOutputStream().write(str.getBytes("UTF-8"));
			}
			
		} catch (IOException e)
		{
			System.out.println("SERVER SIDE: ClientIO Exception");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}