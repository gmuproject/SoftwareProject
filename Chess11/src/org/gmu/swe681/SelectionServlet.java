package org.gmu.swe681;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectionServlet
 */
@WebServlet("/Selection")
public class SelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice=(String)request.getParameter("choice");
		if(choice.equals("statistics"))
		{
			System.out.println("See Statistics");
		}else if(choice.equals("seemoves"))
		{
			System.out.println("See Moves");
		}else if(choice.equals("newgame"))
		{
			System.out.println("Start new game");
		}else if(choice.equals("join"))
		{
			System.out.println("Join an existing game");
		}
	}

}
