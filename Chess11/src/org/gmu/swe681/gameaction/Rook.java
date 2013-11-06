package org.gmu.swe681.gameaction;

public class Rook extends Piece{
	
	public Rook(char color) 
	{
		super(color);
		
	}
	
	@Override
	public boolean isValid(int src1,int src2,int dst1,int dst2,char piece)
	{
		System.out.println("Rook");
		System.out.println(src1+" "+dst1);
		System.out.println(src2+" "+dst2);
		if(src1==dst1)
		{
			if(src2!=dst2) 
			{
				for(int i=src2;i<dst2;i++)
				{
					if(board[src1][i]!='*')
					{
						return false;
					}
				}
				return true;
			}
		}
		else if(src2==dst2)
		{
			if(src1!=dst1) 
			{
				for(int i=src1;i<dst1;i++)
				{
					if(board[src1][i]!='*')
					{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
		
//	@Override
//	public boolean canAttack(Board board, Square currentSquare, Square occupiedSquare) 
//	{
//			return (validSetupForAttack(board, currentSquare, occupiedSquare) &&
//					board.clearPathBetween(currentSquare, occupiedSquare) &&
//					differentSquareInSameRankOrFile(currentSquare, occupiedSquare));
//	}
//
//		@Override
//		public boolean canMove(Board board, Square currentSquare, Square emptySquare) {
//			return (validSetupForMove(board, currentSquare, emptySquare) &&
//					board.clearPathBetween(currentSquare, emptySquare) &&
//					differentSquareInSameRankOrFile(currentSquare, emptySquare));
//		}
//		
//		private boolean differentSquareInSameRankOrFile(Square square1, Square square2) {
//			return !square1.equals(square2) && (square1.inSameRankAs(square2) || square1.inSameFileAs(square2));
//		}
//
//		@Override
//		public PieceType pieceType() {
//			return PieceType.ROOK;
//		}
//
//	@Override
//	public boolean isValid() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
