var ws;

function connect()
{
    var current_user = document.getElementById("current_user").value;
    var friend_user = document.getElementById("friend_user").value;
    
    var host = document.location.host;
    var pathname = document.location.pathname;
    
    //document.getElementById("ItemPreview").src = "data:image/png;base64," + imageByte;
    ws = new WebSocket("ws://" +"proj309-ds-01.misc.iastate.edu:8080"+"/websocket"
                        + "/"+current_user+"/"+friend_user);
    
    ws.onmessage = function(event)
    {
        if (event.data instanceof Blob)
        {
            //alert("get blob");
            var objurl = window.URL.createObjectURL(event.data);
            var img = new Image();
            img.src = objurl;
            img.onload = function ()
            {
                document.getElementById("itemPreview").src = objurl;
            }
        }
        else
        {
            console.log(event.data);
            document.getElementById("log").innerHTML += event.data + "\n";
        }
    };
}

function send()
{
    var content = document.getElementById("msg").value;
    ws.send(content);
}

function sendImage()
{
    var content = document.getElementById("msg").value;

    //content = 'https://www.w3.org/People/mimasa/test/imgformat/img/w3c_home.png';

    // const image2base64 = require('image-to-base64');
    // image2base64(content) // you can also to use url
    //     .then(
    //         (response) => {
    //             console.log(response); //cGF0aC90by9maWxlLmpwZw==
    //             ws.send(response);
    //         }
    //     )
    //     .catch(
    //         (error) => {
    //             console.log(error); //Exepection error....
    //         }
    //     )

    toDataURL(content).then(dataUrl => {
        console.log('RESULT:', dataUrl)
        ws.send(dataUrl);
    });
    // toDataURL(content, function (dataUrl) {
    //     console.log('RESULT:', dataUrl)
    //     ws.send(dataUrl);
    // });
    
    // content = "@image@" + "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAF\
    //CAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
    // ws.send(content);
}

// function toDataURL(url, callback)
// {
//     var xhr = new XMLHttpRequest();
//     xhr.onload = function ()
//     {
//         var reader = new FileReader();
//         reader.onloadend = function ()
//         {
//             callback(reader.result);
//         }
//         reader.readAsDataURL(xhr.response);
//     };
//     xhr.open('GET', url);
//     xhr.responseType = 'blob';
//     xhr.send();
// }

const toDataURL = url => fetch(url)
    .then(response => response.blob())
    .then(blob => new Promise((resolve, reject) => {
        const reader = new FileReader()
        reader.onloadend = () => resolve(reader.result)
        reader.onerror = reject
        reader.readAsDataURL(blob)
    }))

