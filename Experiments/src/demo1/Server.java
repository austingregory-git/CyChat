package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	static ArrayList<Socket> clients = new ArrayList<Socket>();
	
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
		}
		
		while(true)
		{
			Socket clientSocket = null;
			
			try
			{
				clientSocket = serverSocket.accept(); // waiting for client
				clients.add(clientSocket);
				
				System.out.println("Server connected to client " + clientNum);
				
				Thread t = new Thread(new ClientIO(clientSocket, clientNum));
				t.start();
				
			} catch (IOException e)
			{
				System.out.println("SERVER SIDE: clientSocket excpetion");
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
			InputStreamReader in = new InputStreamReader(s.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(in);
			String line = br.readLine();
			while(line != null)
			{
				for(int i=0; i<Server.clients.size(); i++)
				{
					Socket chat = Server.clients.get(i);
					if(!chat.equals(s))
					{
						chat.getOutputStream().write(line.getBytes("UTF-8"));
					}
				}
				br.close();
			}
			
		} catch (IOException e)
		{
			System.out.println("SERVER SIDE: ClientIO Exception");
		}
	}
}