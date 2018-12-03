var Index =
{
	run : function()
	{
		document.getElementById("signup").onclick = Index.Controll.signup;
		document.getElementById("signin").onclick = Index.Controll.signin;
		document.getElementById("database").onclick = Index.Controll.database;
	},

	Model :
	{
	},

	View :
	{
	},

	Controll :
	{
		signup : function()
		{
			window.open("./index/signup/signup.html");
		},

		signin : function()
		{
			window.open("./index/signin/signin.html");
		},

		database : function()
		{
			window.open("http://proj309-ds-01.misc.iastate.edu:8080/database");
		}
	},

}
