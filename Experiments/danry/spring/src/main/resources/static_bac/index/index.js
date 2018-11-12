var Index =
{
	run : function()
	{
        document.getElementById("database").onclick = Index.Controll.database;
		document.getElementById("gitlab").onclick = Index.Controll.gitlab;
		document.getElementById("signup").onclick = Index.Controll.signup;
        document.getElementById("signin").onclick = Index.Controll.signin;
	},

	Model :
	{
	},

	View :
	{
	},

	Controll :
	{
        signin : function()
		{
			// window.open("./signup/signup.html", "_self");
			window.open("./index/signin/signin.html");
        },
        
		signup : function()
		{
			// window.open("./signup/signup.html", "_self");
			window.open("./index/signup/signup.html");
		},

		gitlab : function()
		{
			// window.open("https://git.linux.iastate.edu/cs309/Fall2018/SD_1", "_self");
			window.open("https://git.linux.iastate.edu/cs309/Fall2018/SD_1");
		},

		database : function()
		{
			window.open("http://proj309-ds-01.misc.iastate.edu:8080/database");
		}
	},

}
