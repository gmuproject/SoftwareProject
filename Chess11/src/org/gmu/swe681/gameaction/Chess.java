package org.gmu.swe681.gameaction;

import java.io.*;


public class Chess
{
	public char[][] board={{'R','N','B','Q','K','B','N','R'},{'P','P','P','P','P','P','P','P'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'*','*','*','*','*','*','*','*'},{'p','p','p','p','p','p','p','p'},{'r','n','b','q','k','b','n','r'}};
	int wkingMoveCnt;
	int bkingMoveCnt;
	int[] wK=new int[2];
	int[] bK=new int[2];
	boolean checkmate=false;
	public Chess()
	{
		//record the position of the king;
		wK[0]=0;
		wK[1]=4;
		bK[0]=7;
		bK[1]=4;
		wkingMoveCnt=0;
		bkingMoveCnt=0;
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
			boolean valid=ProcessWhiteMove(move);
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
			boolean valid=ProcessBlackMove(move);
			if(!valid)
			{
				blackMove();
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public boolean ProcessWhiteMove(String move)
	{
		boolean valid=false;
		int src1;
		int src2;
		int dst1;
		int dst2;
		char piece;
		if(move.equals("O-O"))
		{
			kingSideCastleWhite();
		}else if(move.equals("O-O-O"))
		{
			queenSideCastleWhite();
		}
		else if(move.length()>=5 && move.length()<=7)
		{
			src1=Integer.parseInt(move.substring(2, 3))-1;
			src2=move.substring(1, 2).charAt(0)-97;
			dst1=Integer.parseInt(move.substring(4, 5))-1;
			dst2=move.substring(3, 4).charAt(0)-97;
			piece=move.charAt(0);
			
			if(move.length()==5)
			{
				if(validWhite(src1,src2,dst1,dst2,piece))
				{
					board[src1][src2]='*';
					board[dst1][dst2]=piece;
					return(true);
				}
			}else  if(move.length()==6)
			{
				System.out.println(move.charAt(5)+" inside check");
				if(move.charAt(5)=='+')
				{
					if((validWhite(src1,src2,dst1,dst2,piece) )&&(IsBlackCheck(wK[0], wK[1])))
					{
						board[src1][src2]='*';
						board[dst1][dst2]=piece;
						System.out.println("Check");
						return(true);
					}
				}
			}else if((move.length()==7))
			{
				System.out.println(move.charAt(5));
				System.out.println(move.charAt(6));
				if((move.charAt(5)=='+')&&(move.charAt(6)=='+'))
				{
					valid=(validWhite(src1,src2,dst1,dst2,piece) )&& (isCheckmate("white",wK));
					if (valid)
					{
						checkmate=true;
					}
				}
			}
		}
		return valid;
		
	}
	
	public boolean ProcessBlackMove(String move)
	{
		boolean valid=false;
		int src1;
		int src2;
		int dst1;
		int dst2;
		char piece;
		if(move.equals("O-O"))
		{
			kingSideCastleBlack();
		}else if(move.equals("O-O-O"))
		{
			queenSideCastleBlack();
		}
		else if(move.length()>=5 && move.length()<=7)
		{
			src1=Integer.parseInt(move.substring(2, 3))-1;
			src2=move.substring(1, 2).charAt(0)-97;
			dst1=Integer.parseInt(move.substring(4, 5))-1;
			dst2=move.substring(3, 4).charAt(0)-97;
			piece=move.charAt(0);
			if(move.length()==5)
			{
				if(validBlack(src1,src2,dst1,dst2,piece)) 
				{
					board[src1][src2]='*';
					board[dst1][dst2]=piece;
					return(true);
				}
			}else  if(move.length()==6)
			{
				System.out.println(move.charAt(5)+" inside check");
				if(move.charAt(5)=='+')
				{
					if((validBlack(src1,src2,dst1,dst2,piece) )&& (IsWhiteCheck(bK[0], bK[1])))
					{
						board[src1][src2]='*';
						board[dst1][dst2]=piece;
						System.out.println("Check");
						return(true);
					}
				}
			}else if((move.length()==7))
			{
				System.out.println(move.charAt(5));
				System.out.println(move.charAt(6));
				if((move.charAt(5)=='+')&&(move.charAt(6)=='+'))
				{
					valid=(validBlack(src1,src2,dst1,dst2,piece) )&& (isCheckmate("black",bK));
					if (valid)
					{
						System.out.println("You won:Check mate");
						checkmate=true;
					}
				}
			}
		}
		return valid;
		
	}

	private boolean validWhite(int src1,int src2,int dst1,int dst2,char piece) 
	{
		boolean flag=false;
		if(hasPiece(src1,src2,piece)==true)
		{
			if(dstPiece(dst1, dst2)==false||canCut(src1, src2, dst1, dst2, piece)==true)
			{
				if(piece=='P')
				{
					flag=validPawnMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='R')
				{
					flag=validRookMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='B')
				{
					flag=validBishopMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='Q')
				{
					flag=validBishopMove(src1,src2,dst1,dst2,'B')||validRookMove(src1,src2,dst1,dst2,'R');
				}else if(piece=='N')
				{
					flag=validKnightMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='K')
				{
					flag=validKingMove(src1,src2,dst1,dst2,piece)&&IsWhiteCheck(dst1, dst2);
					if(flag==true)
					{
						wkingMoveCnt++;
					}
				}
			}
		}
		
		return flag;
	}
	
	private boolean validBlack(int src1,int src2,int dst1,int dst2,char piece) 
	{
		boolean flag=false;
		if(hasPiece(src1,src2,piece)==true)
		{
			if(dstPiece(dst1, dst2)==false||canCut(src1, src2, dst1, dst2, piece)==true)
			{
				if( piece=='p')
				{
					flag=validPawnMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='r')
				{
					flag=validRookMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='b')
				{
					flag=validBishopMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='q')
				{
					flag=validBishopMove(src1,src2,dst1,dst2,'b')||validRookMove(src1,src2,dst1,dst2,'r');
				}else if(piece=='n')
				{
					flag=validKnightMove(src1,src2,dst1,dst2,piece);
				}else if(piece=='k' )
				{
					flag=validKingMove(src1,src2,dst1,dst2,piece)&&IsBlackCheck(dst1, dst2);
					if(flag==true)
					{
						bkingMoveCnt++;
					}
				}
			}
		}
		
		return flag;
	}
	
	private boolean validKnightMove(int src1, int src2, int dst1, int dst2,char piece) 
	{
		System.out.println("Knight");
		int dX=src1-dst1;
		dX=dX<0?-dX:dX;
		int dY=src2-dst2;
		dY=dY<0?-dY:dY;
						
		return (dY==2 && dX==1) || (dY==1 && dX==2);
		
	}

	private boolean validKingMove(int src1, int src2, int dst1, int dst2,char piece) 
	{
		System.out.println("King");
		if(isAdjacent(src1,src2,dst1,dst2)==true)
		{
			return true;
		}
		return false;	
	}

//	private boolean validQueenMove(int src1, int src2, int dst1, int dst2,char piece) 
//	{
//		System.out.println("Queen");
//		return(validBishopMove(src1,src2,dst1,dst2,'B')||validRookMove(src1,src2,dst1,dst2,'R'));
//		
//	}

	private boolean validBishopMove(int src1, int src2, int dst1, int dst2,char piece) 
	{
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
	
	public boolean isAdjacent(int src1,int src2,int dst1,int dst2)
	{
		int dX,dY;
		int dX1=src1-dst1;
		dX=dX1<0?-dX1:dX1;
		int dY1=src2-dst2;
		dY=dY1<0?-dY1:dY1;
		if(dX==1 ||dX==0)
		{
			if(dY==1 || dY==0)
			{
				return true;
			}
		}
		return false;
	}

	private boolean validPawnMove(int src1,int src2,int dst1,int dst2,char piece)
	{
		if(piece=='P')
		{
			if(src2==dst2)
			{
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
		}
		return false;
	}

	private boolean validRookMove(int src1,int src2,int dst1,int dst2,char piece)
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
	
	private boolean finish()
	{
		
		return checkmate;
	}
	
	private boolean hasPiece(int src1, int src2,char piece)
	{
		if(board[src1][src2]==piece)
		{
			return true;
		}
		return false;
		
	}
	
	private boolean dstPiece(int dst1,int dst2)
	{
		if(board[dst1][dst2]!='*')
		{
			return true;
		}
		return false;
	}
	
	private boolean canCut(int src1,int src2,int dst1,int dst2, char piece)
	{
		
		if(dstPiece(dst1,dst2)==true)
		{
			char dPiece=board[dst1][dst2];
			if(Character.isUpperCase(piece)!=Character.isUpperCase(dPiece))
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean IsWhiteCheck(int wKingR, int wKingC)
	{
		//Check for rook or queen to left
		for(int i=wKingR-1;i>=0;i--)
		{
			if((board[i][wKingC]=='r')||(board[i][wKingC]=='q'))
			{
				return true;
			}else if(board[i][wKingC]!='*')
			{
				return false;
			}		
		}
		//Check for rook or queen to right
		for(int i=wKingR+1;i<8;i++)
		{
			if((board[i][wKingC]=='r')||(board[i][wKingC]=='q'))
			{
				return true;
			}else if(board[i][wKingC]!='*')
			{
				return false;
			}		
		}
		
		//Check for rook or queen up
		for(int i=wKingC-1;i>=0;i--)
		{
			if((board[i][wKingR]=='r')||(board[i][wKingR]=='q'))
			{
					return true;
			}else if(board[i][wKingR]!='*')
			{
				return false;
			}		
		}
		//Check for rook or queen down
		for(int i=wKingC+1;i<8;i++)
		{
			if((board[wKingR][i]=='r')||(board[wKingR][i]=='q'))
			{
				return true;
			}else if(board[wKingR][i]!='*')
			{
				return false;
			}		
		}
		
		//Check diagonal NW for bishop or queen
		int r=wKingR;
		int c=wKingC;
		while(r>=0 && c>=0)
		{
			if((board[r][c]=='b')||(board[r][c]=='q'))
			{
				return true;
			}else if(board[r][c]!='*')
			{
				return false;
			}		
			r--;
			c--;
		}
		
		
		//Check diagonal NE for bishop or queen
		r=wKingR;
		c=wKingC;
		while(r>=0 && c<8)
		{
			if((board[r][c]=='b')||(board[r][c]=='q'))
			{
					return true;
			}else if(board[r][c]!='*')
			{
				return false;
			}	
			r--;
			c++;
		}
		
		//Check diagonal SE for bishop or queen
		r=wKingR;
		c=wKingC;
		while(r<8 && c<8)
		{
			if((board[r][c]=='b')||(board[r][c]=='q'))
			{
				return true;
			}else if(board[r][c]!='*')
			{
				return false;
			}	
			r++;
			c++;
		}
		
		//Check diagonal SW for bishop or queen
		r=wKingR;
		c=wKingC;
		while(r<8 && c>=0)
		{
			if((board[r][c]=='b')||(board[r][c]=='q'))
			{
				return true;
			}else if(board[r][c]!='*')
			{
				return false;
			}	
			r++;
			c--;
		}
		
		//check for knight checks
		r=wKingR;
		c=wKingC;
		if(board[r+2][c-1]=='n')
			return true;
		if(board[r+2][c+1]=='n')
			return true;
		if(board[r+1][c-2]=='n')
			return true;
		if(board[r+1][c+2]=='n')
			return true;
		if(board[r-2][c-1]=='n')
			return true;
		if(board[r-2][c+1]=='n')
			return true;
		if(board[r-1][c-2]=='n')
			return true;
		if(board[r-1][c+2]=='n')
			return true;
		
		//check for pawn checks
		if(board[r+1][c-1]=='p')
			return true;
		if(board[r+1][c+1]=='p')
			return true;
		
		//else not check
		return false;
		
	}
	
	private boolean IsBlackCheck(int bKingR, int bKingC)
	{
		//Check for rook or queen to left
				for(int i=bKingR-1;i>=0;i--)
				{
					if((board[i][bKingC]=='R')||(board[i][bKingC]=='Q'))
					{
						return true;
					}else if(board[i][bKingC]!='*')
					{
						return false;
					}		
				}
				//Check for rook or queen to right
				for(int i=bKingR+1;i<8;i++)
				{
					if((board[i][bKingC]=='R')||(board[i][bKingC]=='Q'))
					{
						return true;
					}else if(board[i][bKingC]!='*')
					{
						return false;
					}		
				}
				
				//Check for rook or queen up
				for(int i=bKingC-1;i>=0;i--)
				{
					if((board[bKingR][i]=='R')||(board[bKingR][i]=='Q'))
					{
							return true;
					}else if(board[bKingR][i]!='*')
					{
						return false;
					}		
				}
				//Check for rook or queen down
				for(int i=bKingC+1;i<8;i++)
				{
					if((board[bKingR][i]=='R')||(board[bKingR][i]=='Q'))
					{
						return true;
					}else if(board[bKingR][i]!='*')
					{
						return false;
					}		
				}
				
				//Check diagonal NW for bishop or queen
				int r=bKingR;
				int c=bKingC;
				while(r>=0 && c>=0)
				{
					if((board[r][c]=='B')||(board[r][c]=='Q'))
					{
						return true;
					}else if(board[r][c]!='*')
					{
						return false;
					}		
					r--;
					c--;
				}
				
				
				//Check diagonal NE for bishop or queen
				r=bKingR;
				c=bKingC;
				while(r>=0 && c<8)
				{
					if((board[r][c]=='B')||(board[r][c]=='Q'))
					{
							return true;
					}else if(board[r][c]!='*')
					{
						return false;
					}	
					r--;
					c++;
				}
				
				//Check diagonal SE for bishop or queen
				r=bKingR;
				c=bKingC;
				while(r<8 && c<8)
				{
					if((board[r][c]=='B')||(board[r][c]=='Q'))
					{
						return true;
					}else if(board[r][c]!='*')
					{
						return false;
					}	
					r++;
					c++;
				}
				
				//Check diagonal SW for bishop or queen
				r=bKingR;
				c=bKingC;
				while(r<8 && c>=0)
				{
					if((board[r][c]=='B')||(board[r][c]=='Q'))
					{
						return true;
					}else if(board[r][c]!='*')
					{
						return false;
					}	
					r++;
					c--;
				}
				
				//check for knight checks
				r=bKingR;
				c=bKingC;
				if(board[r+2][c-1]=='N')
					return true;
				if(board[r+2][c+1]=='N')
					return true;
				if(board[r+1][c-2]=='N')
					return true;
				if(board[r+1][c+2]=='N')
					return true;
				if(board[r-2][c-1]=='N')
					return true;
				if(board[r-2][c+1]=='N')
					return true;
				if(board[r-1][c-2]=='N')
					return true;
				if(board[r-1][c+2]=='N')
					return true;
				
				//check for pawn checks
				if(board[r-1][c-1]=='P')
					return true;
				if(board[r-1][c+1]=='P')
					return true;
				
				//else not check
				return false;
		
	}
	
	private boolean isCheckmate(String type,int[] kingPosition)
	{
		
		int x=kingPosition[0];
		int y=kingPosition[1];
		if(type.equals("white"))
		{
			if(x==0 && y==0)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsWhiteCheck(x+i,y+j)==false)
							return false;
					}
				}
			}
			else if(x==0 && y==7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsWhiteCheck(x+i,y-j)==false)
							return false;
					}
				}
			}else if(x==7 && y==7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsWhiteCheck(x-i,y-j)==false)
							return false;
					}
				}
			}else if(x==7 && y==0)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsWhiteCheck(x-i,y+j)==false)
							return false;
					}
				}
			}else if(x==0 && y>0 && y<7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(IsWhiteCheck(x+i,y+j-1)==false)
							return false;
					}
				}
			}else if(x==7 && y>0 && y<7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(IsWhiteCheck(x-i,y+j-1)==false)
							return false;
					}
				}
			}else if(y==7 && x>0 && x<7)
			{
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsWhiteCheck(x+i-1,y-j)==false)
							return false;
					}
				}
			}else if(y==0 && x>0 && x<7)
			{
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsWhiteCheck(x+i-1,y+j)==false)
							return false;
					}
				}
			}else if(y>0 && y<7 && x>0 && x<7)
			{
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(IsWhiteCheck(x+i-1,y+j-1)==false)
							return false;
					}
				}
			}
			
		}else if(type.equals("black"))
		{
			if(x==0 && y==0)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsBlackCheck(x+i,y+j)==false)
							return false;
					}
				}
			}
			else if(x==0 && y==7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsBlackCheck(x+i,y-j)==false)
							return false;
					}
				}
			}else if(x==7 && y==7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsBlackCheck(x-i,y-j)==false)
							return false;
					}
				}
			}else if(x==7 && y==0)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsBlackCheck(x-i,y+j)==false)
							return false;
					}
				}
			}else if(x==0 && y>0 && y<7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(IsBlackCheck(x+i,y+j-1)==false)
							return false;
					}
				}
			}else if(x==7 && y>0 && y<7)
			{
				for(int i=0;i<2;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(IsBlackCheck(x-i,y+j-1)==false)
							return false;
					}
				}
			}else if(y==7 && x>0 && x<7)
			{
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsBlackCheck(x+i-1,y-j)==false)
							return false;
					}
				}
			}else if(y==0 && x>0 && x<7)
			{
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<2;j++)
					{
						if(IsBlackCheck(x+i-1,y+j)==false)
							return false;
					}
				}
			}else if(y>0 && y<7 && x>0 && x<7)
			{
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(IsBlackCheck(x+i-1,y+j-1)==false)
							return false;
					}
				}
			}
			
		}
		return true;
		
	}
	
	private boolean kingSideCastleWhite()
	{
		if(wkingMoveCnt==0)
		{
				if((board[0][7]=='R')&&(board[0][5]=='*') && (board[0][6]=='*'))
				{
					return true;
				}	
		}
		return false;
	}
	
	private boolean kingSideCastleBlack()
	{
		if(bkingMoveCnt==0)
		{
				if((board[7][7]=='R')&&(board[7][5]=='*') && (board[7][6]=='*'))
				{
					return true;
				}	
		}
		return false;
	}
	
	private boolean queenSideCastleWhite()
	{
		if(wkingMoveCnt==0)
		{
				if((board[0][0]=='R')&&(board[0][1]=='*') && (board[0][2]=='*') && (board[0][3]=='*'))
				{
					return true;
				}	
		}
		return false;
	}
	
	private boolean queenSideCastleBlack()
	{
		if(bkingMoveCnt==0)
		{
			if((board[7][0]=='R')&&(board[7][1]=='*') && (board[7][2]=='*') && (board[7][3]=='*'))
				{
					return true;
				}	
		}
		return false;
	}
		 
	public static void main(String args[])
	{
		Chess chess =new Chess();
		chess.viewBoard();
		while(!chess.finish())
		{
			chess.whiteMove();
			chess.viewBoard();
			chess.blackMove();
			chess.viewBoard();
		}
	}


	public Object getBoard1() {
		boardinfo bi=new boardinfo();
		//bi.setAuthor(board[0][0]);
		//First Row
		bi.setR1(board[0][0]);
		bi.setN1(board[0][1]);
		bi.setB1(board[0][2]);
		bi.setQ(board[0][3]);
		bi.setK(board[0][4]);
		bi.setB2(board[0][5]);
		bi.setN2(board[0][6]);
		bi.setR2(board[0][7]);
		//Second Row
		bi.setP1(board[1][0]);
		bi.setP2(board[1][1]);
		bi.setP3(board[1][2]);
		bi.setP4(board[1][3]);
		bi.setP5(board[1][4]);
		bi.setP6(board[1][5]);
		bi.setP7(board[1][6]);
		bi.setP8(board[1][7]);
		//Third Row
		bi.sets31(board[2][0]);
		bi.sets32(board[2][1]);
		bi.sets33(board[2][2]);
		bi.sets34(board[2][3]);
		bi.sets35(board[2][4]);
		bi.sets36(board[2][5]);
		bi.sets37(board[2][6]);
		bi.sets38(board[2][7]);
		//Fourth Row
		bi.sets41(board[3][0]);
		bi.sets42(board[3][1]);
		bi.sets43(board[3][2]);
		bi.sets44(board[3][3]);
		bi.sets45(board[3][4]);
		bi.sets46(board[3][5]);
		bi.sets47(board[3][6]);
		bi.sets48(board[3][7]);
		//Fifth Row
		bi.sets51(board[4][0]);
		bi.sets52(board[4][1]);
		bi.sets53(board[4][2]);
		bi.sets54(board[4][3]);
		bi.sets55(board[4][4]);
		bi.sets56(board[4][5]);
		bi.sets57(board[4][6]);
		bi.sets58(board[4][7]);
		//Sixth Row
		bi.sets61(board[5][0]);
		bi.sets62(board[5][1]);
		bi.sets63(board[5][2]);
		bi.sets64(board[5][3]);
		bi.sets65(board[5][4]);
		bi.sets66(board[5][5]);
		bi.sets67(board[5][6]);
		bi.sets68(board[5][7]);
		//Seventh Row
		bi.setp1(board[6][0]);
		bi.setp2(board[6][1]);
		bi.setp3(board[6][2]);
		bi.setp4(board[6][3]);
		bi.setp5(board[6][4]);
        bi.setp6(board[6][5]);
		bi.setp7(board[6][6]);
		bi.setp8(board[6][7]);
		//Eight Row
		bi.setr1(board[7][0]);
		bi.setn1(board[7][1]);
		bi.setb1(board[7][2]);
		bi.setq(board[7][3]);
		bi.setk(board[7][4]);
		bi.setb2(board[7][5]);
		bi.setn2(board[7][6]);
		bi.setr2(board[7][7]);
		//System.out.println(board1.toString());
		return bi;
	}	
	
}