package cychat.websockettest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
import cychat.image.Image;

@ServerEndpoint(value = "/websocket/{senderid}/{reciverid}")
@Component
public class WebSocketServer {

	private static Map<Session, String> SUMap = new HashMap();
	private static Map<String, Session> USMap = new HashMap();

	private Server Userver = ApplicationContextHolder.getContext().getBean(Server.class);

	// private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("senderid") String id, @PathParam("reciverid") String reciver) {

			String message = "";

			SUMap.put(session, id);
			USMap.put(id, session);

		
			message = check(Integer.parseInt(id), Integer.parseInt(reciver));

			if (message != null) {
				broadcast(message);

				List<ChatHistory> CH = new ArrayList<>();

				CH = Userver.listHistory(Integer.parseInt(id), Integer.parseInt(reciver));

				for (int i = 0; i < CH.size(); i++)
				{
					if(CH.get(i).getMessage().compareTo("@picture") ==0)
					{
						//Image itemp = Userver.showImage();
						this.sendImage(id,Userver.showImage().getPic());
					}
					else
						this.sendMessageToperson(id, CH.get(i).getSender() + " : " + CH.get(i).getMessage());
				}
			} else
				sendMessageToperson(id, "No such user or " + id + " And " + reciver + " is not friend");

	}

	
	private String check(int id, int reciver) {
		if (Userver.isFriend(id, reciver))
			return id + "connected";
		else
			return "NULL";
	}

	@OnMessage
	public void onMessage(Session session, @PathParam("reciverid") String reciver, String message) {
		String username = SUMap.get(session).toString();

		DateFormat datef = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String Date = datef.format(date);

		if(message.startsWith("@image@"))
		{
			String temp = message.substring(7);
			byte[] pic = this.GetBytes(temp);
			Userver.SaveI(new Image(3,"test","png",pic));
			Userver.saveHistory(new ChatHistory("@picture" , Integer.parseInt(username),Integer.parseInt(reciver),Date));
		}
		else
		{
			Userver.saveHistory(new ChatHistory(message, Integer.parseInt(username), Integer.parseInt(reciver), Date));
		}
		
		

		sendMessageToperson(username, username + ": " + message + "  TIME:" + Date);

		if (USMap.containsKey(reciver))
			sendMessageToperson(reciver, username + ": " + message);
		
		
	}
	
	public byte[] GetBytes(String str)
    {
		try {
            byte[] name = Base64.getEncoder().encode(str.getBytes());
            byte[] decodedString = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
            System.out.println(new String(decodedString));
            return decodedString;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

	@OnClose
	public void onClose(Session session) {
		String username = SUMap.get(session);

		SUMap.remove(session);
		USMap.remove(username);

		String message = username + " leaved";

		broadcast(message);

	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		broadcast("Error in cychat.websockettest.WebSocketServer");
	}

	private void sendMessageToperson(String username, String message) {
		try {
			USMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendImage(String username,byte[] temp)
	{
		try {
			USMap.get(username).getBasicRemote().sendObject(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void broadcast(String message) {
		SUMap.forEach((session, id) -> {
			synchronized (session) {
				try {
					session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
