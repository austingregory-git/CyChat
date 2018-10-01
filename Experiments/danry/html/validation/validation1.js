
function validation1()
{
    if (check())
    {
        setTimeout(nextpage,3000);
    }
    return false;
}

function nextpage()
{
    window.open("valiadtion2.html", "_self");
}

function check()
{
    var pass = true;
    var first = document.getElementById("firstName").value;
    if(alphanumeric(first))
    {
        showCorrect("firstNameimg");
        pass = pass && true;
    }
    else
    {
        showWrong("firstNameimg");
        pass = pass && false;
    }
    var last = document.getElementById("lastName").value;
    if(alphanumeric(last))
    {
        showCorrect("lastNameimg");
        pass = pass && true;
    }
    else
    {
        showWrong("lastNameimg");
        pass = pass && false;
    }
    var gender = document.getElementById("gender").value;
    if(notEmpty(gender))
    {
        showCorrect("genderimg");
        pass = pass && true;
    }
    else
    {
        showWrong("genderimg");
        pass = pass && false;
    }
    var state = document.getElementById("state").value;
    if(notEmpty(state))
    {
        showCorrect("stateimg");
        pass = pass && true;
    }
    else
    {
        showWrong("stateimg");
        pass = pass && false;
    }

    return pass;
}

function alphanumeric(str)
{
    return /^[A-z0-9]+$/.test(str);
}

function notEmpty(str)
{
    return !str=="";
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