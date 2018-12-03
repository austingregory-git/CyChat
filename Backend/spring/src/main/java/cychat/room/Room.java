package cychat.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "room")
@Entity
public class Room {
	
	@Column(name = "id")
	@Id
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "creater")
	private int creater;

	@Column(name = "manager")
	private String manager;
	
	Room()
	{}
	
	public Room(int id, String name, int creater, String manager) {
		super();
		this.id = id;
		this.name = name;
		this.creater = creater;
		this.manager = manager;
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

	public int getCreater() {
		return creater;
	}

	public void setCreater(int creater) {
		this.creater = creater;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	

}
