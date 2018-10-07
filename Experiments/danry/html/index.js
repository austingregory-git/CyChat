
function index()
{
    displayTime();
    enter();
}

function enter()
{
    window.open("https://git.linux.iastate.edu/cs309/Fall2018/SD_1", "_self");
    return false;
}

function displayTime()
{
    document.getElementById("time").innerHTML = new Date();
}
