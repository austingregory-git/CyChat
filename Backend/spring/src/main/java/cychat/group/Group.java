package cychat.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grouphistory")
public class Group {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "groupid")
	private int groupid;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "sender")
	private int sender;

	public Group()
	{
		
	}
	
	public Group(int id, String name, String time, String message, int sender) {
		this.groupid = id;
		this.name = name;
		this.time = time;
		this.message = message;
		this.sender = sender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}
	
	
	

}
