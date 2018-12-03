var ws;

function connect() {
    var current_user = document.getElementById("current_user").value;
    var friend_user = document.getElementById("friend_user").value;
    
    var host = document.location.host;
    var pathname = document.location.pathname;
    
    ws = new WebSocket("ws://" +"proj309-ds-01.misc.iastate.edu:8080"+"/websocket" + "/"+current_user+"/"+friend_user);

    ws.onmessage = function(event) {
    var log = document.getElementById("log");
        console.log(event.data);
        log.innerHTML += event.data + "\n";
    };
}

function send() {
	
    var content = document.getElementById("msg").value;
    ws.send(content);
}
