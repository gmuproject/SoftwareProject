package org.gmu.swe681.gameaction;

public abstract class Piece 
{
	private int timesMoved = 0;
	private char color;
	
	//public char[][] board={{'R','N','B','Q','K','B','N','R'},{'P','P','P','P','P','P','P','P'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'p','p','p','p','p','p','p','p'},{'r','n','b','q','k','b','n','r'}};
	
	public Piece(char color) 
	{
		if (color=='W' || color == 'B') {
			this.color = color;
		}
		else {
			System.out.println("Invalid move");
		}
	}
	
	
	public boolean isFirstMove() {
		return timesMoved == 0;
	}
	
	
	public void incrementTimesMoved() {
		timesMoved += 1;
	}
	
	
	public void decrementTimesMove() {
		if (timesMoved > 0) {
			timesMoved -= 1;
		}
	}
	
	public abstract boolean isValid(int src1,int src2,int dst1,int dst2,char piece);


//	public boolean equals(Object obj) {
//		boolean equals = false;
//		if (obj != null && obj instanceof Piece) {
//			Piece that = (Piece)obj;
//			equals = this.pieceType().equals(that.pieceType()) && this.color().equals(that.color()); 
//		}
//		return equals;
//	}
//	
//	public int hashCode() {
//		return 31 * color().hashCode() + 31 * pieceType().hashCode();
//	}
//
//	protected boolean validSetupForMove(Board board, Square currentSquare, Square emptySquare) {
//		return (this == board.pieceOn(currentSquare) && !board.hasPieceOn(emptySquare));
//	}
//	
//	protected boolean validSetupForAttack(Board board, Square currentSquare, Square occupiedSquare) {
//		return (this == board.pieceOn(currentSquare) &&
//				board.hasPieceOn(occupiedSquare) && 
//				!this.color().equals(board.pieceOn(occupiedSquare).color()));
//	}
}
