<%-- 
    Document   : Result
    Created on : Sep 22, 2013, 4:36:38 PM
    Author     : nafiseh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome!!!</h1>
        <form method="post" action="/Chess11/Selection">
			<p><input type="radio" name="choice" value="statistics"> Win Loss Statistics</input></p>
			<p><input type="radio" name="choice" value="seemoves"> See game moves</input></p>
			<p><input type="radio" name="choice" value="newgame"> Start a new game </input></p>
			<p><input type="radio" name="choice" value="join"> Join an existing game</input></p>
			<input type="submit" value="Submit">
		</form>
    </body>
</html>
