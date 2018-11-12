package cychat.friend;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(ComposityKey.class)
@Table(name = "Friend")
public class Friend {
	

	@Column(name = "Sender")
	@Id
	private int Sender;
	
	
	@Column(name = "Reciver")
	@Id
	private int Reciver;
	
	public Friend()
	{
		
	}
	
	public Friend(int from, int to) {
		this.Sender = from;
		this.Reciver = to;
	}
	public int getFrom() {
		return Sender;
	}
	public void setFrom(int from) {
		this.Sender = from;
	}
	public int getTo() {
		return Reciver;
	}
	public void setTo(int to) {
		this.Reciver = to;
	}
	
	

}
