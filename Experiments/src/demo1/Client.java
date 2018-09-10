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
	int serverPortNumber = 4444;
	ServerListener sl;

	Client()
	{
		try
		{
			serverSocket = new Socket(serverHostName, serverPortNumber);
			
			sl = new ServerListener(this, serverSocket);
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

	public void handleMessage(String cmd, String s)
	{
		switch (cmd)
		{
		case "print":
			System.out.println("client side: " + s);
			break;
		case "exit":
			System.exit(-1);
			break;
		default:
			System.out.println("client side: unknown command received:" + cmd);
		}
	}

	public static void main(String[] args)
	{
		Client client = new Client();
	}
}


class ServerListener implements Runnable
{
	Client c;
	Scanner in; // this is used to read which is a blocking call

	ServerListener(Client c, Socket s)
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
			String cmd = in.next();
			String s = in.nextLine();
			c.handleMessage(cmd, s);
		}
	}
}
