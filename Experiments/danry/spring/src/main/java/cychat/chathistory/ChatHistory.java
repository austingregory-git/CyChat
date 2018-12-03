package cychat.chathistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chathistory")
public class ChatHistory {
	
	@Id @GeneratedValue
	int id;
	
	@Column(name = "message")
	String message;
	
	@Column(name = "sender")
	int sender;
	
	@Column(name = "reciver")
	int reciver;
	
	@Column(name = "time")
	String time;
	
	public ChatHistory()
	{
		
	}
	
	public ChatHistory(String message, int sender, int reciver, String time) {
		super();
		this.message = message;
		this.sender = sender;
		this.reciver = reciver;
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

	public int getReciver() {
		return reciver;
	}

	public void setReciver(int reciver) {
		this.reciver = reciver;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
