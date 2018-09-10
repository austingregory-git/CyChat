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
			serverSocket = new ServerSocket(4444);
			System.out.println(serverSocket);
		} catch (IOException e)
		{
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}	
		
		while(true)
		{
			Socket clientSocket = null;
			
			try
			{
				System.out.println("Waiting for client " + clientNum++ + " to connect!");
				clientSocket = serverSocket.accept();
				
				System.out.println("Server got connected to a client" + clientNum);
				ClientIO c = new ClientIO(clientSocket, clientNum);
				Thread t = new Thread(c);
				t.start();
				
			} catch (IOException e)
			{
				System.out.println("SERVER SIDE: clientSocket excpetion");
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
		}
	}
}