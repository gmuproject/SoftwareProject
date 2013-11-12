package org.gmu.swe681;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gmu.swe681.gameaction.Chess;

/**
 * Servlet implementation class GameplayServlet
 */
@WebServlet("/GameplayServlet")
public class GameplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Chess chesscode = new Chess();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In GamePlayServlet");
		Chess chesscode = new Chess();
		//int i=0;
		request.getSession().setAttribute("gameboard", chesscode.getBoard1());
		request.getRequestDispatcher("ShowGameBoardResult.jsp").forward(request, response);
//		while(i<5)
//		{
//			chesscode.whiteMove();
//			request.getSession().setAttribute("gameboard", chesscode.getBoard1());
//			request.getRequestDispatcher("ShowGameBoardResult.jsp").forward(request, response);
//			chesscode.blackMove();
//			request.getSession().setAttribute("gameboard", chesscode.getBoard1());
//			request.getRequestDispatcher("ShowGameBoardResult.jsp").forward(request, response);
//			i++;
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String wmove=(String)request.getParameter("wmove");
		System.out.println(wmove);
		
		String bmove=(String)request.getParameter("bmove");
		System.out.println(bmove);
		boolean valid;
		if(wmove !=null)
		{
			valid=chesscode.ProcessWhiteMove(wmove);
			request.getSession().setAttribute("gameboard", chesscode.getBoard1());
			request.getRequestDispatcher("ShowGameBoardResult_black.jsp").forward(request, response);

		}else if(bmove !=null)
		{
			valid=chesscode.ProcessBlackMove(bmove);
			request.getSession().setAttribute("gameboard", chesscode.getBoard1());
			request.getRequestDispatcher("ShowGameBoardResult.jsp").forward(request, response);
		}
//		if(!valid)
//		{
//			whiteMove();
//		}
	}

}
