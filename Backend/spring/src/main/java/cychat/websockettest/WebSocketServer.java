package cychat.websockettest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import cychat.chathistory.ChatHistory;
import cychat.controll.Server;

@ServerEndpoint(value = "/websocket/{senderid}/{reciverid}")
@Component
public class WebSocketServer {
	
	private static Map<Session, String> SUMap = new HashMap();
	private static Map<String, Session> USMap = new HashMap();
	
	private Server Userver = ApplicationContextHolder.getContext().getBean(Server.class);
	
	//private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("senderid") String id , @PathParam("reciverid") String reciver)
	{
//		String[] temp = id.split(" ");
		String message = "";
//		
//		int senderID = Integer.parseInt(temp[0]);
//		int reciverID = Integer.parseInt(temp[1]);
		
		SUMap.put(session, id);
		USMap.put(id, session);
		
		message = check(Integer.parseInt(id),Integer.parseInt(reciver));
		
		if(message != null)
		{
			broadcast(message);
			
			List<ChatHistory> CH = new ArrayList<>();
			
			CH = Userver.listHistory(Integer.parseInt(id), Integer.parseInt(reciver));
			
			for(int i = 0 ; i < CH.size() ; i ++)
				this.sendMessageToperson(id,CH.get(i).getSender() + " : " + CH.get(i).getMessage());
			
		}
		else
			sendMessageToperson(id,"No such user or " + id + " And " + reciver +" is not friend");
		
		
	}
	
	private String check(int id , int reciver)
	{	
			if(Userver.isFriend(id, reciver))
				return id + "connected";
			else
				return "NULL"; 
	}
	
	@OnMessage
	public void onMessage(Session session , @PathParam("reciverid") String reciver , String message)
	{
		String username = SUMap.get(session).toString();
		
		Userver.saveHistory(new ChatHistory(message,Integer.parseInt(username),Integer.parseInt(reciver),"11:00"));
		
		sendMessageToperson(username, username+ ": " + message);
		sendMessageToperson(reciver , username + ": " + message);
	}
	
	@OnClose
	public void onClose(Session session)
	{
		String username = SUMap.get(session);
		
		SUMap.remove(session);
		USMap.remove(username);
		
		String message = username + " leaved";
		
		broadcast(message);
		
	}
	
	@OnError
	public void onError(Session session, Throwable throwable)
	{
		broadcast("Something is wrong");
	}
	
	private void sendMessageToperson(String username , String message)
	{
		try {
			USMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void broadcast(String message)
	{
		SUMap.forEach((session,id)->
		{
			synchronized(session)
			{
				try {
					session.getBasicRemote().sendText(message);;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}
