var stomp = null;
var sender;
var reciver;
var ur;


function setup(check)
{
	$("#connect").prop("disabled",check);
	$("#disconnect").prop("disabled",!check);
	$("#senderid").prop("disabled",check);
	$("#reciverid").prop("disabled",check);
	
	if(check)
	{
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();	
	}
	$("#greetings").html("");
}

function connect()
{
	sender = document.getElementById("senderid").value;
	//alert(sender);
	
	reciver = document.getElementById("reciverid").value;
	//alert(reciver);
	
	
	var socket = new SockJS('/ws');
	stomp = Stomp.over(socket);
	stomp.connect(
	{},onConnect);
	
}

function onConnect()
{	
	setup(true);
	
	ur = make(sender,reciver);
	
	//alert(temp);
	
	ur = ur.toString();
	
	//alert(temp);

	//var temp = sender.toString() + "/" + reciver.toString();
	
	//alert(temp);
	
	stomp.subscribe("/topic/greet/" + sender + "/" + reciver ,function (name) {
		
		if((name.body).indexOf("There are no user") != -1)
		{
			alert(name.body);
			disconnect();
		}
	
		if((name.body).indexOf("are Not Friend") != -1)
		{
			alert(name.body);
			disconnect();
		}
		
		var temp = (name.body).split("+");
		
		for(var i in temp)
		{
			showName(temp[i]);	
		}
		
	});
	
	stomp.subscribe("/topic/g." + ur,function (name) {
		showName(name.body);
	});	
	
	
	stomp.send("/app/check/" + sender +"/" +reciver,{} , sender.toString() + " " + reciver.toString() );
}

//function onMess()
//{
//	stomp.subscribe("/topic/g." + ur,function (name) {
//		showName(name.body);
//	});	
//}

function disconnect() {
	if(stomp !== null)
	{
		stomp.disconnect();	
	}
	setup(false);
}

function sendMess()
{
	
	var data = 
		{
			"message" : $("#message").val(),
			"sender" : $("#senderid").val(),
			"reciver" : $("#reciverid").val(),
			"time" : startTime()
		}
	
	stomp.send("/app/all."+ ur,{},JSON.stringify(data));
}

function sendName() {
	
	
	var data = 
	{
		"id"       : $("#userid").val(),
		"username" : $("#username").val(),
		"password" : $("#password").val(),
		"email"      : $("#email").val(),
		"name"     : $("#name").val()
	};

	stomp.send("/app/check",{},JSON.stringify(data));
	
}

function showName(temp) {
	$("#conversation").append("<tr><td>" + temp + "</td></tr>");
}


function make(a,b)
{
	a = parseInt(a);
	b = parseInt(b);
	
	return a+b;
}

function checkTime(i) {
	  if (i < 10) {
	    i = "0" + i;
	  }
	  return i;
	}

function startTime() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  // add a zero in front of numbers<10
  m = checkTime(m);
  s = checkTime(s);
  var temp = h + ":" + m + ":" + s;
	  
  return temp;
}

//Type of the main function

$(function () {
   /* $("form").on('submit', function (e) {
        e.preventDefault();
    });*/
    $( "#connect" ).click(function() { connect();});
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#Submit" ).click(function() { sendMess(); });
    $("#test").click(function(){showName("haha")});
}
);

