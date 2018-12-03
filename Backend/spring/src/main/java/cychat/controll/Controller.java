package cychat.controll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cychat.chathistory.ChatHistory;
import cychat.course.Course;
import cychat.group.Group;
import cychat.image.Image;
import cychat.room.Room;
import cychat.userInfor.UserInfor;

@RestController
public class Controller {

	
//	
//	private List<UserInfor> users = new ArrayList<>(Arrays.asList( new UserInfor(14,"guost", "qwe", "xiuyuan" ,21),
//			new UserInfor(11,"asdf", "qwe", "Kane" ,21),
//			new UserInfor(12,"guo", "qwe", "fk" ,21),
//			new UserInfor(13,"steven", "qwe", "xwww" ,21)
//			
//			));
//	
	
	@Autowired
	private Server Userver;
	
	
	@RequestMapping("/database")
	public List<UserInfor> call()
	{
		return Userver.show();	
	}
	
	@RequestMapping("/database/find_id_{id}")
	public Optional<UserInfor> Find(@PathVariable int id)
	{
	
		return Userver.Find(id);
	}
	
	@RequestMapping("/database/{name}")
	public List<UserInfor> FindName(@PathVariable String name)
	{
		return Userver.FindbyName(name);
	}
	
	@RequestMapping("/database/delete/{id}")
	public String Delete(@PathVariable int id)
	{
		Userver.Delete(id);
		return "Delete Successfully";
		
	}
	
	@RequestMapping("/isnull")
	public String isNull()
	{
		if(Userver == null)
			return "ISNULL";
		else
			return "not null";
	}
	
	@RequestMapping(method=RequestMethod.POST , value = "/database")
	public void Add(@RequestBody UserInfor user)
	{
		Userver.addUser(user);
	}
	
	@RequestMapping("/database/search/get_{name}")
	public List<UserInfor> name(@PathVariable String name)
	{
		return Userver.getNameV(name);
	}
	
	@RequestMapping("/database/message/{id}")
	public List<ChatHistory> getSenderMess(@PathVariable int id)
	{
		return Userver.getSenderMessage(id);
	}
	
	@RequestMapping("/group/{id}/{Gid}")
	public void addGroup(@PathVariable int id , @PathVariable int Gid)
	{
		Userver.addG(id, Gid);
	}
	
//	
//	@RequestMapping("/database/Ave")
//	public int getAveAge()
//	{
//		return Userver.AveAge();
//	}
//	
//	@RequestMapping("/database/Max")
//	public int getMaxAge()
//	{
//		return Userver.getMaxAge();
//	}
//	
//	@RequestMapping("/databse/search/age_{age}")
//	public List<UserInfor> getAges(@PathVariable int age)
//	{
//		return Userver.SameAge(age);
//	}
	
	@RequestMapping("/add/{Sender}/{Reciver}")
	public String getFriend(@PathVariable int Sender , @PathVariable int Reciver)
	{
		return Userver.addFriends(Sender, Reciver);
	}
	
	@RequestMapping("/friend/{id}")
	public List<UserInfor> getFriend(@PathVariable int id)
	{
		return Userver.listFriend(id);
	}
	
	
	@RequestMapping("/chat/{User}")
	public List<ChatHistory> ShowFriend(@PathVariable String s)
	{
		String[] ss = s.split(" ");
		
		int sender = Integer.parseInt(ss[0]);
		int reciver = Integer.parseInt(ss[1]);
		
		return Userver.listHistory(reciver , sender);
	}
	
	@RequestMapping("/subject/{id}")
	public List<Course> findCourse(@PathVariable int id)
	{
		return Userver.getCourses(id);
	}
	
	@RequestMapping(method=RequestMethod.POST , value = "/subject")
	public void addCourse(@RequestBody Course temp)
	{
		Userver.addCourse(temp);
	}
	
	@RequestMapping("/rooms")
	public List<Room> showRoom()
	{
		return Userver.ShowR();
	}
	
	
	@RequestMapping("/group/{id}")
	public List<Room> findGroup(@PathVariable int id)
	{
		return Userver.findGroup(id);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/create/room")
	public void createroom(@RequestBody Room temp)
	{
		Userver.saveRoom(temp);
	}
	
	@RequestMapping("/image")
	public void saveImage() throws Exception
	{
		Userver.saveImage();
	}
	
	@RequestMapping("/show/image")
	public Image ShowImage()
	{
		return Userver.showImage();
	}
	
	@MessageMapping("/check/{id}/{idd}")
	@SendTo("/topic/greet/{id}/{idd}")
	public String showUsers(String s)
	{
		String[] ss = s.split(" ");
		
		int sender = Integer.parseInt(ss[0]);
		int reciver = Integer.parseInt(ss[1]);
		
		if(!Userver.Find(reciver).isPresent())
			return "There are no user " + reciver;
		
		if(!Userver.Find(sender).isPresent())
			return "There are no user " + sender;
		
		if(!Userver.isFriend(sender, reciver))
			return reciver + " And " + sender +" are Not Friend";
		
		
		List<ChatHistory> temp = Userver.listHistory(sender, reciver);
		String event = "";
		
		for(int i = 0 ; i <temp.size() ; i++)
		{
			if( i == temp.size()-1)
				event += temp.get(i).getSender() +" ("  + temp.get(i).getTime()
				+") : " + temp.get(i).getMessage();
			else
				event += temp.get(i).getSender() +" ("  + temp.get(i).getTime()
				+") : " + temp.get(i).getMessage() + "+";
		}
		
		
		return event;
	}
	

	@MessageMapping("/all.{id}")
	@SendTo("/topic/g.{id}")
	public String getAll(@PathVariable String id, ChatHistory s)
	{
		Userver.saveHistory(s);
		
		return s.getSender() +" (" + s.getTime()+ ") :" + s.getMessage();
	}
	
	@MessageMapping("/group.{id}")
	@SendTo("/topic/group.{id}")
	public String geton(Group g)
	{
		Userver.SaveGroupHistory(g);
		
		return g.getSender() + " : " + g.getMessage();
	}
	
	@MessageMapping("/his/{id}")
	@SendTo("/topic/chat/{id}")
	public String GroupHistory(String id)
	{
		int groupId = Integer.parseInt(id);
		
		String temp = "";	
		
		List<Group> Groupl = Userver.findG(groupId);
		
		for(int i = 0 ; i < Groupl.size() ; i++)
			temp += Groupl.get(i).getSender() +" (" 
				 +	Groupl.get(i).getTime() + ") " +" : " 
				 +	Groupl.get(i).getMessage() + "+"; 
		
		
		
		return temp;
	}
	
}
