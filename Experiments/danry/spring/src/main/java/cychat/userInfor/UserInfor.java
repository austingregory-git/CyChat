package cychat.userInfor;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserInfor {

	@Column(name = "id")
	@Id
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "Name")
	private String Name;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Type")
	private String Type;
	
	
	//private ArrayList<Integer> friends = new ArrayList<>();
	
	
	public UserInfor() {
	
	}
	
	public UserInfor(int id, String username, String password, String name, String age, String Type) {
		this.id = id;
		this.username = username;
		this.password = password;
		Name = name;
		email = age;
		this.Type = Type;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setAge(String Email) {
		this.email = Email;
	}



	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}

//	public ArrayList<Integer> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(ArrayList<Integer> friends) {
//		this.friends = friends;
//	}
//	
//	public void addFriend(int FriendId)
//	{
//		friends.add(FriendId);
//	}
//	
	
	
}
