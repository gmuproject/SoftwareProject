package org.gmu.swe681.gameaction;

public class Knight extends Piece{

	public Knight(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(int src1, int src2, int dst1, int dst2, char piece) {
		System.out.println("Knight");
		int dX=src1-dst1;
		dX=dX<0?-dX:dX;
		int dY=src2-dst2;
		dY=dY<0?-dY:dY;
						
		return (dY==2 && dX==1) || (dY==1 && dX==2);
	}

}
