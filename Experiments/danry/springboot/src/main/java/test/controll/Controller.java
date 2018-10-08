package test.controll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{

	@Autowired
	private Server Userver;

	@RequestMapping("/user")
	public List<UserInfor> call()
	{
		return Userver.findAll();
	}

	@RequestMapping("/user/{id}")
	public Optional<UserInfor> Find(@PathVariable int id)
	{
		return Userver.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public void Add(@RequestBody UserInfor user)
	{
		Userver.save(user);
	}

}
