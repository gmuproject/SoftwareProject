<%-- 
    Document   : index
    Created on : Sep 22, 2013, 11:45:37 AM
    Author     : nafiseh
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
             <form method="post" action="/Chess_11032013/Login">
                    <center>
                            <h1> <b>Welcome to Chess World!!!</b></h1>
                    </center>
			<h3><b>Login Here!!!</b></h3>
			<br>
            <br>
                     User Name:<input type="text" name="username"/>
             <br>
		     Password: <input type="password" name="password"/>
             <br>
             <br>
             <input type="submit" value="Login" />
             <input type="reset" value="Reset" />
             <br>
             <br>
             <br>
             <b> <a href="Register.jsp">Register Here</a></b>
        </form>
    </body>
</html>

