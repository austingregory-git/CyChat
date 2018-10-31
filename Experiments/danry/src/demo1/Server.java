package demo1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server
{
	static ArrayList<ServerIO> clients = new ArrayList<ServerIO>();
	private static Scanner scan;

	public static void main(String[] args)
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(4040);
			System.out.println(serverSocket);
			String username = null;

			while (true)
			{
				Socket clientSocket = serverSocket.accept(); //waiting for client to connect
				System.out.print("Enter Username:  ");
				
				scan = new Scanner(System.in);
				username = scan.nextLine();
				System.out.println(username + " has connected to server");
				
				ServerIO s_io = new ServerIO(clientSocket, username);
				clients.add(s_io);//add to client list
				
				Thread t = new Thread(s_io);
				t.start();
			}
		} catch (IOException e)
		{
			System.out.println("SERVER SIDE: clientSocket excpetion");
		}
	}
}

class ServerIO implements Runnable
{
	Socket socket;
	String name;

	ServerIO(Socket socket, String username)
	{
		this.socket = socket;
		name = username;
	}

	@Override
	public void run()
	{
		try
		{
			DataInputStream inStream = new DataInputStream(this.socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(this.socket.getOutputStream());
			outStream.writeUTF("Welcom to chatroom!");
			outStream.writeUTF("You are Client " + name);
			outStream.flush();
			
			
			while(true)
			{
				String input = inStream.readUTF();
				System.out.println(name + ": " + input);
				for(ServerIO t : Server.clients)
				{
					if(!t.socket.equals(this.socket))
					{
						DataOutputStream output = new DataOutputStream(t.socket.getOutputStream());
						output.writeUTF(name + ":  " + input);
						output.flush();
					}
				}
			}
		} catch (IOException e)
		{
			System.out.println("SERVER SIDE: ServerIO Exception");
		}
	}
}