var Index =
{
	run : function()
	{
		document.getElementById("signup").onclick = Index.Controll.signup;
		document.getElementById("signin").onclick = Index.Controll.signin;
		document.getElementById("chat").onclick = Index.Controll.chat;
		document.getElementById("database").onclick = Index.Controll.database;
		document.getElementById("database_schema").href = "./docs/database_schema.pdf";
		document.getElementById("java_docs").href = "./docs/javadocs/package-tree.html";
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
			window.open("./signup/signup.html");
		},

		signin : function()
		{
			window.open("./signin/signin.html");
		},

		chat : function()
		{
			window.open("./chatroom/chatroom.html");
		},

		database : function()
		{
			window.open("http://proj309-ds-01.misc.iastate.edu:8080/database");
		}
	},

}
