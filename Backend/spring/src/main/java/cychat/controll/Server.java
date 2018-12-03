package cychat.controll;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cychat.chathistory.ChatHistory;
import cychat.chathistory.History;
import cychat.friend.Friend;
import cychat.friend.FriendList;
import cychat.group.Group;
import cychat.group.Groupchat;
import cychat.userInfor.UserInfor;
import cychat.userInfor.User_data;

@Service
@Configurable
public class Server {
	
	@Autowired
	private User_data data;
	
	@Autowired
	private FriendList FL;
	
	@Autowired
	private History chat;
	
	@Autowired 
	private Groupchat G;
	
	
	public List<UserInfor> show()
	{
		List<UserInfor> temp = new ArrayList<>();
		data.findAll().forEach(temp::add);
		return temp;
		
	}
	
	public Optional<UserInfor> Find(int username)
	{
		return data.findById(username);
	}
	
	public void addUser(UserInfor user)
	{
		data.save(user);
	}

	public void Delete(int id) {
		data.deleteById(id);
		}
	
//	public int AveAge()
//	{
//		return data.AveageAge();
//	}
//	
//	public int getMaxAge()
//	{
//		return data.maxAge();
//	}
	
	public List<UserInfor> getNameV(String name)
	{
		return data.getnameV(name);
	}
	
//	public List<UserInfor> SameAge(int Age)
//	{
//		return data.getSameAge(Age);
//	}
//	
	public String addFriends(int Sender, int Reciver)
	{
//		List<Friend> temp = new ArrayList<>();
//		FL.findAll().forEach(temp::add);
//		return temp;
		
		String temp = "Successful added";
		try
		{
			if(data.findById(Sender).get() != null && data.findById(Reciver).get()!=null)
			{
				Friend f = new Friend(Sender,Reciver);
				FL.save(f);
				
			}
		}
		catch (Exception e)
		{
			return "error";
		}

		return temp;
		
	}
	
//	public void HandAdd()
//	{
//		UserInfor temp = new UserInfor(12,"x","x","x",12,"s");
//		data.save(temp);
//	}

	public List<UserInfor> ShowFriends(int Sender) {
		
		List<UserInfor> temp = new ArrayList<>();
		Optional<Friend> f;
		
		
		return temp;
		
		//return null;
	}
	
	public List<UserInfor> listFriend(int id)
	{
		List<Friend> temp = new ArrayList<>();
		
		temp =FL.findFriend(id);
		
		List<UserInfor> fds = new ArrayList<>();
		
		for(int i = 0 ; i < temp.size() ; i++)
		{
			fds.add(data.findById(temp.get(i).getTo()).get());
		}
		
		return fds;
	}
	
	public boolean isFriend(int sender , int reciver)
	{
		List<Friend> temp = FL.isfriend(sender , reciver);
		return temp.size() >= 1;
		
	}
	
	public void saveHistory(ChatHistory temp)
	{
		chat.save(temp);
	}
	
	public List<ChatHistory> listHistory(int sender , int reciver)
	{
		List<ChatHistory> temp = new ArrayList<>();
		//chat.findAll().forEach(temp::add);
		
		temp = chat.findHistory(sender, reciver);
		
		return temp;
	}
	
	public void testsave(String temp)
	{
		ChatHistory ch = new ChatHistory(temp,820,22,"11");
		chat.save(ch);
	}
	
	public List<Group> findG(int Groupid)
	{
		return G.findGroupid(Groupid);
	}
	
	public void SaveGroupHistory(Group g)
	{
		G.save(g);
	}

	public List<UserInfor> FindbyName(String name) {
		
		return null;
	}
	
	public List<ChatHistory> getSenderMessage(int id)
	{
		return chat.getmessage(id);
	}

}








