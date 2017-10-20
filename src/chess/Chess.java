package chess;

import pieces.*;

public class Chess
{
	public static Piece[][] createBoard(){
		Piece[][] board = new Piece[8][8];
		for(int i=0;i<8;i++){
			board[1][i] = new Pawn('w',1,i);
			board[6][i] = new Pawn('b',6,i);
		}
		board[0][0] = new Rook('w',0,0);
		board[0][7] = new Rook('w',0,7);
		board[7][0] = new Rook('b',7,0);
		board[7][7] = new Rook('b',7,7);
		
		board[0][1] = new Knight('w',0,1);
		board[0][6] = new Knight('w',0,6);
		board[7][1] = new Knight('b',7,1);
		board[7][6] = new Knight('b',7,6);
		
		board[0][2] = new Bishop('w',0,2);
		board[0][5] = new Bishop('w',0,5);
		board[7][2] = new Bishop('b',7,2);
		board[7][5] = new Bishop('b',7,5);
		
		board[0][3] = new Queen('w',0,3);
		board[0][4] = new King('w',0,4);
		board[7][3] = new Queen('b',7,3);
		board[7][4] = new King('b',7,4);
		
		return board;
	}
	
	public static void printBoard(Piece[][] board){
		System.out.println();
		for(int i=7; i>=0; i--){
			for(int j=0; j<8; j++){
				if(board[i][j] != null)
					System.out.print(board[i][j] + " ");
				else
					System.out.print((i+j)%2==0 ? "## " : "   ");
			}
			System.out.print(i+1 +"\n");
		}
		for(int i=0; i <8;i++)
			System.out.print(" " + (char)('a'+i) + " ");
		System.out.println();
	}

	public static void main(String[] args)
	{
		Piece[][] board = createBoard();
		printBoard(board);

	}

}
