<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="org.gmu.swe681.gameaction.Chess"%>
<%@page import="org.gmu.swe681.gameaction.boardinfo"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table style="text-align: left; width: 1074px; height: 206px;" border="1" cellpadding="1" cellspacing="1">
		<tbody>
			<%
			//private char[][] boardG;
			//if(session.getAttribute("gameboard") != null) {
				boardinfo board1=(boardinfo)session.getAttribute("gameboard");
			//	Char
			//---
				//System.out.println(board1.getb1());
				//for(int i=0;i<8;i++)
					
				//{ //for (BookInfoVO vo : (List<BookInfoVO>) session.getAttribute("bookslist"))

			%>
			<tr>
				<td><%=board1.getR1()%></td>
				<td><%=board1.getN1()%></td>
				<td><%=board1.getB1()%></td>
				<td><%=board1.getQ()%></td>
				<td><%=board1.getK()%></td>
				<td><%=board1.getB2()%></td>
				<td><%=board1.getN2()%></td>
				<td><%=board1.getR2()%></td>
			</tr>
			
		    <tr>
				<td><%=board1.getP1()%></td>
				<td><%=board1.getP2()%></td>
				<td><%=board1.getP3()%></td>
				<td><%=board1.getP4()%></td>
				<td><%=board1.getP5()%></td>
				<td><%=board1.getP6()%></td>
				<td><%=board1.getP7()%></td>
				<td><%=board1.getP8()%></td>
			</tr>
			
		    <tr>
				<td><%=board1.gets31()%></td>
				<td><%=board1.gets32()%></td>
				<td><%=board1.gets33()%></td>
				<td><%=board1.gets34()%></td>
				<td><%=board1.gets35()%></td>
				<td><%=board1.gets36()%></td>
				<td><%=board1.gets37()%></td>
				<td><%=board1.gets38()%></td>

			</tr>

		    <tr>
				<td><%=board1.gets41()%></td>
				<td><%=board1.gets42()%></td>
				<td><%=board1.gets43()%></td>
				<td><%=board1.gets44()%></td>
				<td><%=board1.gets45()%></td>
				<td><%=board1.gets46()%></td>
				<td><%=board1.gets47()%></td>
				<td><%=board1.gets48()%></td>

			</tr>
			
		    <tr>
				<td><%=board1.gets51()%></td>
				<td><%=board1.gets52()%></td>
				<td><%=board1.gets53()%></td>
				<td><%=board1.gets54()%></td>
				<td><%=board1.gets55()%></td>
				<td><%=board1.gets56()%></td>
				<td><%=board1.gets57()%></td>
				<td><%=board1.gets58()%></td>

			</tr>
			
		    <tr>
				<td><%=board1.gets61()%></td>
				<td><%=board1.gets62()%></td>
				<td><%=board1.gets63()%></td>
				<td><%=board1.gets64()%></td>
				<td><%=board1.gets65()%></td>
				<td><%=board1.gets66()%></td>
				<td><%=board1.gets67()%></td>
				<td><%=board1.gets68()%></td>

			</tr>	
					
  		    <tr>
				<td><%=board1.getp1()%></td>
				<td><%=board1.getp2()%></td>
				<td><%=board1.getp3()%></td>
				<td><%=board1.getp4()%></td>
				<td><%=board1.getp5()%></td>
				<td><%=board1.getp6()%></td>
				<td><%=board1.getp7()%></td>
				<td><%=board1.getp8()%></td>

			</tr>		
			
			<tr>
				<td><%=board1.getr1()%></td>
				<td><%=board1.getn1()%></td>
				<td><%=board1.getb1()%></td>
				<td><%=board1.getq()%></td>
				<td><%=board1.getk()%></td>
				<td><%=board1.getb2()%></td>
				<td><%=board1.getn2()%></td>
				<td><%=board1.getr2()%></td>
			</tr>		
			
			<form method="post" action="GameplayServlet">
				<h3>Black to move:</h3>
			    <input type="text" name="bmove">
				<input type="submit" name="submit" >
				</form>
		</tbody>
	</table>
	<br>
	<br>
</body>
</html>