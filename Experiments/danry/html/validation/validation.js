function validation()
{
	var id = document.getElementById("userid").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var age = document.getElementById("age").value;
	var name = document.getElementById("name").value;
	
	post(id, username, password, age, name);
	document.getElementById("post").onclick = window.open("http://proj309-ds-01.misc.iastate.edu:8080/user");
}

function post(id, username, password, age, name)
{
	
	var xhr = new XMLHttpRequest();
	var url = "http://proj309-ds-01.misc.iastate.edu:8080/user";
	
//	var method = "POST";
//	if ("withCredentials" in xhr)
//	{
//	    // Check if the XMLHttpRequest object has a "withCredentials" property.
//	    // "withCredentials" only exists on XMLHTTPRequest2 objects.
//	    xhr.open(method, url, true);
//	  }
//	else if (typeof XDomainRequest != "undefined") {
//		alert(2);
//	    // Otherwise, check if XDomainRequest.
//	    // XDomainRequest only exists in IE, and is IE's way of making CORS
//		// requests.
//	    xhr = new XDomainRequest();
//	    xhr.open(method, url);
//
//	  } else {
//	    // Otherwise, CORS is not supported by the browser.
//	    xhr = null;
//	  }
//	if (!xhr) {
//		  throw new Error('CORS not supported');
//		}

	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function ()
	{
	    if (xhr.readyState === 4 && xhr.status === 200)
		{
	        var json = JSON.parse(xhr.responseText);
	    }
	};

	var data = JSON.stringify
	({
		"id"       : id,
		"username" : String(username),
		"password" : String(password),
		"age"      : age,
		"name"     : String(name)
	});
	xhr.send(data);
}

function get(id, username, password, age, name)
{
	// Sending a receiving data in JSON format using GET method
	var xhr = new XMLHttpRequest();
	var url = "url?data=" + encodeURIComponent(JSON.stringify(
	{
		"id"       : id,
		"username" : username,
		"password" : password,
		"age"      : age,
		"name"     : name
	}));
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function()
	{
		if (xhr.readyState === 4 && xhr.status === 200)
		{
			var json = JSON.parse(xhr.responseText);
			console.log(json.username + ", " + json.userid + ", " +
					    json.password + ", " + json.age + ", " +
					    json.name);
		}
	};
	xhr.send();
}

function feedback()
{
	alert("you have successfully sign up");
}

function check()
{
	var pass = true;

	var username = document.getElementById("username").value;
	if (alphanumeric(username))
	{
		showCorrect("username");
		pass = pass && true;
	} else
	{
		pass = pass && false;
	}
	var userid = document.getElementById("userid").value;
	if (alphanumeric(userid))
	{
		showCorrect("userid");
		pass = pass && true;
	} else
	{
		pass = pass && false;
	}
	var gender = document.getElementById("gender").value;
	if (notEmpty(gender))
	{
		showCorrect("genderimg");
		pass = pass && true;
	} else
	{
		showWrong("genderimg");
		pass = pass && false;
	}
	var email = document.getElementById("email").value;
	if (emailcheck(email))
	{
		showCorrect("emailimg");
		pass = pass && true;
	} else
	{
		showWrong("emailimg");
		pass = pass && false;
	}

	return pass;
}

function alphanumeric(str)
{
	return /^[A-z0-9]+$/.test(str);
}

function numeric(str)
{
	return /^[0-9]+$/.test(str);
}

function notEmpty(str)
{
	return !str == "";
}

function emailcheck(str)
{
	// return false;
	return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/.test(str);
}

function telcheck(str)
{
	var digits = /^\d{10}$/.test(str);
	var dash = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/.test(str);
	return digits || dash;
	// return /^[0-9]{3}-[0-9]{3}-[0-9]{4}|\d{10}$/.test(str);
}

function addcheck(str)
{
	if (!/^[A-Za-z,]+$/.test(str))
		return false;
	if (str[0] == "," || str[str.length - 1] == ",")
		return false;
	var i;
	var count = 0;
	for (i = 0; i < str.length; i++)
	{
		if (str[i] == ",")
		{
			count++;
		}
	}

	return count == 1;
}

function showCorrect(id)
{
	document.getElementById(id).hidden = false;
	document.getElementById(id).src = "correct.png";
}

function showWrong(id)
{
	document.getElementById(id).hidden = false;
	document.getElementById(id).src = "wrong.png";
}
