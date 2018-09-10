package demo1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Server
{
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
				System.out.println("Server connected to client " + clientNum);
				
				ClientIO c = new ClientIO(clientSocket, clientNum++);
				
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
		Scanner in;
		PrintWriter out;
		
		try
		{
			BufferedInputStream input = new BufferedInputStream(s.getInputStream());
			BufferedOutputStream output = new BufferedOutputStream(s.getOutputStream());
			in = new Scanner(input);
			out = new PrintWriter(output);
			
			out.println("You are client " + n);
			out.flush();
			
			//TODO handle the inputStream and send the input to another client
			
			
		} catch (IOException e)
		{
			System.out.println("SERVER SIDE: ClientIO Exception");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}