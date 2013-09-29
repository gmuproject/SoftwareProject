package org.gmu.swe681.gameaction;

import java.io.*;

public class Chess
{
	private char[][] board={{'R','N','B','Q','K','B','N','R'},{'P','P','P','P','P','P','P','P'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'p','p','p','p','p','p','p','p'},{'r','n','b','q','k','b','n','r'}};
	
	public Chess()
	{
		
	}
	
	public void viewBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			System.out.println(board[i]);
		}
	}
	
	public void whiteMove() 
	{
		System.out.println("White to move:");
		InputStreamReader istream = new InputStreamReader(System.in) ;
		BufferedReader bufRead = new BufferedReader(istream);
		String move;
		try {
			move = bufRead.readLine();
			boolean valid=processMove(move);
			if(!valid)
			{
				whiteMove();
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void blackMove() 
	{
		System.out.println("Black to move:");
		InputStreamReader istream = new InputStreamReader(System.in) ;
		BufferedReader bufRead = new BufferedReader(istream);
		String move;
		try {
			move = bufRead.readLine();
			boolean valid=processMove(move);
			if(!valid)
			{
				blackMove();
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public boolean processMove(String move)
	{
		if(move.length()==5)
		{
			int src1=Integer.parseInt(move.substring(2, 3))-1;
			int src2=move.substring(1, 2).charAt(0)-97;
			int dst1=Integer.parseInt(move.substring(4, 5))-1;
			int dst2=move.substring(3, 4).charAt(0)-97;
			char piece=board[src1][src2];
			
			if(valid(src1,src2,dst1,dst2,piece))
			{
				
				board[src1][src2]='*';
				board[dst1][dst2]=piece;
				return(true);
			}else
			{
				System.out.println("invalid move");
				return(false);
			}
			
		}
		return false;
	}
	
	private boolean valid(int src1,int src2,int dst1,int dst2,char piece) {
		
		if(piece=='P')
		{
			if(src2==dst2)
			{
				System.out.println(src1+" "+dst1);
				if(src1>1)
				{
					if(dst1==src1+1)
					{
						return true;
					}
				}else if(src1==1)
				{
					if((dst1==src1+1) ||(dst1==src1+2))
					{
						return true;
					}
				}
			}
		}else if(piece=='p')
		{
			if(src2==dst2)
			{
				System.out.println(src1+" "+dst1);
				if(src1<6)
				{
					if(dst1==src1-1)
					{
						return true;
					}
				}else if(src1==6)
				{
					if((dst1==src1-1) ||(dst1==src1-2))
					{
						return true;
					}
				}
			}
		}else if(piece=='r' ||piece=='R')
		{
			if(src1==dst1)
			{
				if(src2!=dst2) 
				{
					return true;
				}
			}
			else if(src2==dst2)
			{
				if(src1!=dst1) 
				{
					return true;
				}
			}
		}
		
		return false;
	}

	private static boolean finish() {
		
		return false;
	}
		 
	public static void main(String args[])
	{
		Chess chess =new Chess();
		chess.viewBoard();
		while(!finish())
		{
			chess.whiteMove();
			chess.viewBoard();
			chess.blackMove();
			chess.viewBoard();
		}
	}

	
	
}