function userhome()
{

    window.open("../chat/chat.html");

    var database_url = 'http://proj309-ds-01.misc.iastate.edu:8080/database/';
    var friendlist = database_url + userid;
    showfriend(getJSON_object(friendlist));

}


function getJSON_object(url)
{
    return JSON.parse(getJSON(url));
}

function getJSON(url)
{
    var httpreq = new XMLHttpRequest();
    httpreq.open("GET", url, false);
    httpreq.send(null);
    return httpreq.responseText;          
}

function showfriend(str) {
    document.getElementById("friendlist").innerHTML = str;
    document.getElementById("friendlist").hidden = false;
}

