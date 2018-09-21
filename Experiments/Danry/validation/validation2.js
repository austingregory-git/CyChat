function validation2()
{
    if (check())
    {
        //submit
    }
    return false;
}

function check()
{
    var pass = true;
    var email = document.getElementById("email").value;
    if (emailcheck(email))
    {
        showCorrect("emailimg");
        pass = pass && true;
    }
    else
    {
        showWrong("emailimg");
        pass = pass && false;
    }
    var tel = document.getElementById("tel").value;
    if (telcheck(tel))
    {
        showCorrect("telimg");
        pass = pass && true;
    }
    else
    {
        showWrong("telimg");
        pass = pass && false;
    }
    var add = document.getElementById("add").value;
    if (addcheck(add))
    {
        showCorrect("addimg");
        pass = pass && true;
    }
    else
    {
        showWrong("addimg");
        pass = pass && false;
    }

    return pass;
}

function emailcheck(str)
{
    //return false;
    return /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/.test(str);
}

function telcheck(str)
{
    var digits = /^\d{10}$/.test(str);
    var dash = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/.test(str);
    return digits || dash;
    //return /^[0-9]{3}-[0-9]{3}-[0-9]{4}|\d{10}$/.test(str);
}

function addcheck(str)
{
    if (!/^[A-Za-z,]+$/.test(str)) return false;
    if (str[0] == "," || str[str.length-1] == ",") return false;
    var i;
    var count = 0;
    for (i=0; i<str.length; i++)
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
