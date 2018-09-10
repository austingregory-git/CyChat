package demo1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client
{
	public Client()
	{
		try
		{
			Socket serverSocket = new Socket("localhost", 4040);
			
			DataOutputStream outStream = new DataOutputStream(serverSocket.getOutputStream());
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			
			ClientIO cio = new ClientIO(serverSocket);
			new Thread(cio).start();
			
			while(true)
			{
				String output = read.readLine();
				outStream.writeUTF(output);
				outStream.flush();
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new Client();
	}
}

class ClientIO implements Runnable
{
	Client c;
	Socket socket;
	DataInputStream input;

	public ClientIO(Socket s)
	{
		try
		{
			input = new DataInputStream(s.getInputStream());
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
			String str = null;
			try
			{
				str = input.readUTF();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			if(!str.equals(null))
				System.out.println(str);
		}
	}
}