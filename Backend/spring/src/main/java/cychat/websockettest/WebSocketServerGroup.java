package cychat.websockettest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cychat.group.Group;

@ServerEndpoint(value = "/group/{senderid}/{reciverid}")
@Component
public class WebSocketServerGroup {

	private static Map<Session, GroupReference> SGMap = new HashMap();
	private static Map<GroupReference, Session> GSMap = new HashMap();

	private Server Userver = ApplicationContextHolder.getContext().getBean(Server.class);

	// private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("senderid") String id, @PathParam("reciverid") String reciver) {

		GroupReference temp = new GroupReference(Integer.parseInt(id), Integer.parseInt(reciver));

		if (Userver.Find(Integer.parseInt(id)).isPresent()) {
			SGMap.put(session, temp);
			GSMap.put(temp, session);

			List<Group> Gmessage = Userver.findG(temp.getGroupID());
			for (int i = 0; i < Gmessage.size(); i++) {
				this.sendtoperson(temp, Gmessage.get(i).getSender() + " : " + Gmessage.get(i).getMessage());
			}

		}

	}

	@OnMessage
	public void onMessage(Session session, @PathParam("reciverid") String reciver, String message) {
		GroupReference GR = SGMap.get(session);

		DateFormat datef = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String Date = datef.format(date);
		
		broadcast(GR.getPersonID() + " : " + message, GR.getGroupID());
		
		Userver.SaveGroupHistory(new Group(Integer.parseInt(reciver),"haha" , Date , message ,GR.getPersonID()));

	}

	@OnClose
	public void onClose(Session session) {
		GroupReference username = SGMap.get(session);

		SGMap.remove(session);
		GSMap.remove(username);

		String message = username.getPersonID() + " leaved";

		broadcast(message);

	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		broadcast("Error in cychat.websockettest.WebSocketServerGroup");
	}

	private void sendtoperson(GroupReference GR, String message) {
		try {
			GSMap.get(GR).getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private static void broadcast(String message, int Groupid) {
		SGMap.forEach((session, GroupReference) -> {
			synchronized (session) {
				try {
					if (GroupReference.getGroupID() == Groupid)
						session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void broadcast(String message) {
		SGMap.forEach((session, GroupReference) -> {
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
	

	private class GroupReference {
		private int personID;
		private int groupID;

		public GroupReference(int personID, int groupID) {
			super();
			this.personID = personID;
			this.groupID = groupID;
		}

		public int getPersonID() {
			return personID;
		}

		public void setPersonID(int personID) {
			this.personID = personID;
		}

		public int getGroupID() {
			return groupID;
		}

		public void setGroupID(int groupID) {
			this.groupID = groupID;
		}

	}

}
