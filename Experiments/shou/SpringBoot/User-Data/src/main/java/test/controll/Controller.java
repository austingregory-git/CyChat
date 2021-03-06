package test.controll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	
	
	private List<UserInfor> users = new ArrayList<>(Arrays.asList( new UserInfor(14,"guost", "qwe", "xiuyuan" ,21),
			new UserInfor(11,"asdf", "qwe", "Kane" ,21),
			new UserInfor(12,"guo", "qwe", "fk" ,21),
			new UserInfor(13,"steven", "qwe", "xwww" ,21)
			
			));
	
	
	@Autowired
	private Server Userver;
	
	
	@RequestMapping("/user")
	public List<UserInfor> call()
	{
		return Userver.show();	
	}
	
	@RequestMapping("/user/{id}")
	public Optional<UserInfor> Find(@PathVariable int username)
	{
		return Userver.Find(username);
	}
	
	@RequestMapping(method=RequestMethod.POST , value = "/user")
	public void Add(@RequestBody UserInfor user)
	{
		Userver.addUser(user);
	}
	
}
