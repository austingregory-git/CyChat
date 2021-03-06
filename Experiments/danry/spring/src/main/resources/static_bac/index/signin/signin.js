
var datajson;

function signin() {
    var iduser = document.getElementById("userid").value;
    document.getElementById("submit").onclick = validation(iduser);
}

function validation(iduser) {
    if (isEmpty(iduser)) {
        report_error("User ID is empty");
    }
    else if (!isNumber(iduser)) {
        report_error("User ID is invalid");
    }
    else {
        var url = 'http://proj309-ds-01.misc.iastate.edu:8080/database/';
        var user_url = url + iduser;

        //var json_obj = JSON.parse(getJSON(user_url));
        var json_obj = getJSON_object(user_url)

        if (jQuery.isEmptyObject(json_obj))
        {
            alert('user not registered');
            report_error("user not registered");
        }
        else
        {
            //window.open("", "../chat/chat.html");

            var newWindow = window.open("../userhome/userhome.html");
            //newWindow.document.getElementById("list").value = "ppppppppppppppp";
            //newWindow.document.write(<p id="friendlist"> ppppp</p>);

            // var myWindow = window.open("", "MsgWindow", "width=200,height=100");
            // myWindow.document.write("<p>This window's name is: " + myWindow.name + "</p>");
        }
    }
}

function getJSON_object (url)
{
    return JSON.parse(getJSON(url));
}

function getJSON(url){
    var httpreq = new XMLHttpRequest();
    httpreq.open("GET", url, false);
    httpreq.send(null);
    return httpreq.responseText;          
}

function isNumber(str) {
    return /^[0-9]+$/.test(str);
}

function isEmpty(str) {
    return str == "";
}

function report_error(str) {
    document.getElementById("message").innerHTML = str;
    document.getElementById("message").hidden = false;
}
