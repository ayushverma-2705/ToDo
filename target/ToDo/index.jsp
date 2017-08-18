<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>To Do App</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <script src = "index.js" type = "application/javascript" > </script>

    <p id="demo">
    </p>

    <%
        String login_msg=(String)request.getAttribute("error");
        if(login_msg!=null)
        out.println("<center> <font color=red size=4px>"+login_msg+"</font></center>");
    %>

</head>
<body class="login">
<!-- header starts here -->
<div id="facebook-Bar">
    <div id="facebook-Frame">
        <div id="logo"> <a href=""> To Do App </a> </div>
        <div id="header-main-right">
            <div id="header-main-right-nav">
                <form method="post" action="login" id="login_form" name="login_form">
                    <table border="0" style="border:none">
                        <tr>
                            <td ><input type="text" tabindex="1"  id="username" placeholder="Username" name="username" class="inputtext radius1"  value="" required></td>
                            <td ><input type="password" tabindex="2" id="password" placeholder="Password" name="password" class="inputtext radius1" required ></td>
                            <td ><input type="submit" class="fbbutton" name="login" value="Login" /></td>
                        </tr>

                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- header ends here -->
<div class="loginbox radius">
    <h2 style="color:#141823; text-align:center;">Welcome to To Do App</h2>
    <div class="loginboxinner radius">
        <div class="loginheader">
            <h4 class="title">Connect with your colleagues and manage your Team To Do's </h4>
        </div>
        <!--loginheader-->
        <div class="loginform">
            <form id="login" action="register" method="post">
                <p>
                    <input type="text" id="username1" name="username1" placeholder="Username" value="" class="radius" required/>
                </p>
                <p>
                    <input type="password" id="password1" name="password1" placeholder="Password" value="" class="radius" required/>
                </p>
                <p>
                    <input type="email" id="email" name="email" placeholder="Email" class="radius" required />
                </p>
                <p>
                    <input type="tel" id="phone" name="phone" placeholder="Phone" class="radius" required pattern=".{10}" />
                </p>
                <p>
                    <input type="submit" class="fbbutton" name="signup" value="Sign Up for To Do App">
                <!--    <button onclick = "userRegister();"class="radius title" name="signup">Sign Up for To Do App</button> -->
                </p>
            </form>
        </div>
        <!--loginform-->
    </div>
    <!--loginboxinner-->
</div>
<!--loginbox-->
</body>


</html>