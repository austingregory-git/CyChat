package demo1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client
{
	Socket serverSocket;
	String serverHostName = "localhost";
	int serverPortNumber = 4040;
	ServerIO sl;

	Client()
	{
		try
		{
			serverSocket = new Socket(serverHostName, serverPortNumber);
			
			sl = new ServerIO(this, serverSocket);
			new Thread(sl).start();
			PrintWriter out;
			
			out = new PrintWriter(new BufferedOutputStream(serverSocket.getOutputStream()));
			
			
			out.println("This is message from client");
			out.flush();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Client client = new Client();
		client.getClass();
	}
}


class ServerIO implements Runnable
{
	Client c;
	Scanner in;

	ServerIO(Client c, Socket s)
	{
		try
		{
			this.c = c;
			in = new Scanner(new BufferedInputStream(s.getInputStream()));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		while (true)
		{
			System.out.println("Client - waiting to read");
			String s = in.nextLine();
		}
	}
}
