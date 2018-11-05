package chat;

import java.io.*;
import java.net.*;


public class Client {
	
	public static void main(String[] args)
	{
		
		try
		{
			Socket s = new Socket("localhost" , 1204);
			
			
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//			DataInputStream din = new DataInputStream(s.getInputStream());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
//			String msgin = "";
			String msgout = "";
		
			Thread r = new getMsg(s);
			r.start();
			
			while(true)
			{
				
				
				msgout = br.readLine();
				dout.writeUTF(msgout);
				dout.flush();
//				msgin = din.readUTF();
//				System.out.println(msgin);
            }
			 
			
		}
		catch(Exception e)
		{
			
		}
		
	}
	

}

class getMsg extends Thread
{
	private Socket s;
	public DataInputStream din;
	
	public getMsg(Socket as)
	{
		this.s = as;
		try
		{
			din = new DataInputStream(s.getInputStream());
		}
		catch(Exception E)
		{
			
		}
	}

	@Override
	public void run() {
		try
		{
		// TODO Auto-generated method stub
			
			
			while(true)
			{
				String ss = "";
				ss = din.readUTF();
				if(!ss.equals(""))
				System.out.println(ss);
			}
			
		}
		catch(Exception e)
		{
			
		}
		
	}
	
}

