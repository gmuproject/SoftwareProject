<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Chess World!</h1>
     
          
         <form method="post" action="/Chess11/Register">
                     username:<input type="text" name="username">
                 <br>
                 <br>
                     password:<input type="text" name="password">
                 <br>
                 <br>
                     first name:<input type="text" name="fname">
                 <br>
                 <br>
                     last name:<input type="text" name="lname">
                 <br>
                 <br>
                     email:<input type="text" name="email">
                 <br>
                 <br>
              <input type="submit" value="Register" name="register">
              <br>
              <br>
              <b> <a href="index.jsp">Login Here</a></b>
        </form>
    </body>    
    
</html>
