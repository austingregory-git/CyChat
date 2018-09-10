package chat;

//GOALS
// 1. to show client code that connects to the server and sends it a message
//

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyCLient {

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		// 1. CONNECT TO THE SERVER AT PORT 4444 
		Socket socket = new Socket("localhost", 4444);
		printSocketInfo(socket);
		
		// 2. WRITE A MESSAGE TO THE SOCKET TO SEND TO THE SERVER
//		PrintWriter out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
		
		DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
		String msgout = "";
		msgout = br.readLine();
		
		dout.writeUTF(msgout);
		dout.flush();
		}
		
//		out.println("Client socket Local Address: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + "Motherfucker");
//		out.println("  Client socket Remote Address: " + socket.getRemoteSocketAddress());
//		out.println("MotherFukcer");
		
//		out.flush(); // forces data from buffer to be sent to server
//		
//		out.close();
		// client dies here

	}

	static void printSocketInfo(Socket s) {
		System.out.print("Socket on Client Side: ");
		System.out.print("Local Address: " + s.getLocalAddress() + ":"
				+ s.getLocalPort());
		System.out.println("  Remote Address: " + s.getRemoteSocketAddress());
	}

}
