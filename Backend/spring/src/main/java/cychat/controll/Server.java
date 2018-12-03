package cychat.controll;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cychat.chathistory.ChatHistory;
import cychat.chathistory.History;
import cychat.course.Course;
import cychat.course.CourseList;
import cychat.friend.Friend;
import cychat.friend.FriendList;
import cychat.group.Group;
import cychat.group.Groupchat;
import cychat.image.Image;
import cychat.image.ImageSave;
import cychat.room.Room;
import cychat.room.RoomList;
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
	
	@Autowired
	private CourseList C;
	
	@Autowired
	private RoomList R; 
	
	@Autowired
	private ImageSave IS;
	
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
			if((data.findById(Sender).get() != null && data.findById(Reciver).isPresent()) || R.findById(Reciver).get() !=null)
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
			
			if(temp.get(i).getTo() != id && data.findById(temp.get(i).getTo()).isPresent())
				fds.add(data.findById(temp.get(i).getTo()).get());
			else if(data.findById(temp.get(i).getFrom()).isPresent())
				fds.add(data.findById(temp.get(i).getFrom()).get());

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
	
	public List<Course> getCourses(int student)
	{
		return C.findBystudentID(student);
	}
	
	public void addCourse(Course temp)
	{
		C.save(temp);
	}
	
	public void addG(int person , int group)
	{
		FL.save(new Friend(person , group));
	}

	public List<Room> findGroup(int id)
	{
		List<Friend> temp = new ArrayList<>();

		temp = FL.findFriend(id);

		List<Room> fds = new ArrayList<>();

		
		for (int i = 0; i < temp.size(); i++) {

			if (R.findById(temp.get(i).getTo()).isPresent())
				fds.add(R.findById(temp.get(i).getTo()).get());
			else if (R.findById(temp.get(i).getFrom()).isPresent())
				fds.add(R.findById(temp.get(i).getFrom()).get());

		}

		return fds;

	}
	
	public List<Room> ShowR()
	{
		List<Room> temp = new ArrayList<>();
		R.findAll().forEach(temp :: add);
		return temp;
	}
	
	public void SaveI(Image temp)
	{
		IS.save(temp);
	}
	
	public void saveImage() throws Exception
	{
		ClassPathResource backImgFile = new ClassPathResource("image/chart1.png");
		byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
		backImgFile.getInputStream().read(arrayPic);
		Image blackImage = new Image(1, "chart1", "png", arrayPic);
		
		// image 2
		ClassPathResource blueImgFile = new ClassPathResource("image/chart2.png");
		arrayPic = new byte[(int) blueImgFile.contentLength()];
		blueImgFile.getInputStream().read(arrayPic);
		Image blueImage = new Image(2, "char2", "png", arrayPic);
		
		// store image to MySQL via SpringJPA
		IS.save(blackImage);
		IS.save(blueImage);
		
		 //retrieve image from MySQL via SpringJPA
		for(Image imageModel : IS.findAll()){
			Files.write(Paths.get("save/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
		}
	}
	
	public Image showImage()
	{
		return IS.findById(1).get();
	}
	
	public void saveRoom(Room temp)
	{
		R.save(temp);
	}
}
	
	









