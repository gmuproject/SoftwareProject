package org.gmu.swe681.gameaction;

public class Board
{
	public Piece board[][]=new Piece[8][8];
	
	public Board()
	{
		board[0][0]=new Rook('W');
		board[0][1]=new Knight('W');
		
	}

}
