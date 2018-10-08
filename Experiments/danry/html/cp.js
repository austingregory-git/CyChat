var Index =
{
	run : function()
	{
		document.getElementById("gitlab").onclick = Index.Controll.gitlab;
		document.getElementById("signup").onclick = Index.Controll.signup;
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
			// window.open("./validation/validation.html", "_self");
			window.open("./validation/validation.html");
		},

		gitlab : function()
		{
			// window.open("https://git.linux.iastate.edu/cs309/Fall2018/SD_1", "_self");
			window.open("https://git.linux.iastate.edu/cs309/Fall2018/SD_1");
		},
	},

}
