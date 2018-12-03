//package cychat.websockettest;
//
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.websocket.EncodeException;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//
//import org.springframework.stereotype.Component;
//
//import cychat.chathistory.ChatHistory;
//import cychat.controll.Server;
//
//@ServerEndpoint(value = "/websocket/{id}")
//@Component
//public class WebSocketServerlog {
//
//	private static Map<Session, String> SUMap = new HashMap();
//	private static Map<String, Session> USMap = new HashMap();
//
//	private Server Userver = ApplicationContextHolder.getContext().getBean(Server.class);
//
//	// private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
//
//	@OnOpen
//	public void onOpen(Session session, @PathParam("id") String id) {
//
//		SUMap.put(session, id);
//		USMap.put(id, session);
//	}
//
//	@OnMessage
//	public void onMessage(Session session) {
//		sendNotification(session.getId() + "" , "whatever");
//	}
//
//	@OnClose
//	public void onClose(Session session) {
//		String username = SUMap.get(session);
//
//		SUMap.remove(session);
//		USMap.remove(username);
//
//	}
//
//	@OnError
//	public void onError(Session session, Throwable throwable) {
//		//broadcast("Error in cychat.websockettest.WebSocketServerlog");
//		sendNotification(session.getId()+"" , "something is wrong");
//	}
//
//	public void sendNotification(String username, String message) {
//		try {
//			if (USMap.containsKey(username))
//				USMap.get(username).getBasicRemote().sendText(message);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
