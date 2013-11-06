package org.gmu.swe681.gameaction;

public class Bishop extends Piece{

	public Bishop(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int src1, int src2, int dst1, int dst2, char piece) {
		int dX,dY;
		int dX1=src1-dst1;
		dX=dX1<0?-dX1:dX1;
		int dY1=src2-dst2;
		dY=dY1<0?-dY1:dY1;
		if(dX==dY)
		{
			if(dX1<0 && dY1<0)
			{
			
				for(int i=1;i<dX;i++)
				{
					if(board[src1+i][src2+i]!='*')
					{
						return false;
					}
				}
			}else if(dX1<0 && dY1>0)
			{
				for(int i=1;i<dX;i++)
				{
					if(board[src1+i][src2-i]!='*')
					{
						return false;
					}
				}
			}else if(dX1>0 && dY1<0)
			{
				for(int i=1;i<dX;i++)
				{
					if(board[src1-i][src2+i]!='*')
					{
						return false;
					}
				}
			}else if(dX1>0 && dY1>0)
			{
				for(int i=1;i<dX;i++)
				{
					if(board[src1-i][src2-i]!='*')
					{
						return false;
					}
				}
			}
			System.out.println("valid bishop");
			return true;
		}
		return false;
	}

}
