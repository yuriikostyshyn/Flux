<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Flux login screen</title>
<script type="text/javascript" src="js/jquery/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<link type="text/css" href="css/jquery/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
<link type="text/css" href="css/login/login.css" rel="stylesheet" />

<script>
	$(function() {
		$("#login_screen").dialog();
		$("#login_screen").dialog("option", "width", 350);
		$("#login_screen").dialog("option", "draggable", false);
		$("#login_screen").dialog("option", "closeOnEscape", false);
		$("#login_screen").dialog("option", "resizable", false);
	});
</script>

</head>
<body>
    <div id="login_screen">
        <p>Enter your login and password</p>
        <form action="login.do" method="post">
            <label> Login: </label> <input type="text" maxlength="40" name="login" id="login" /> <br> <label> Password: </label> <input id="password" type="password"
                maxlength="40" name="password" /> <br>
            <a href="#">Forgot your password</a><input id="login_submit" type="submit" name="Login" />
        </form>
    </div>
</body>

</html>