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
	}

	public static void main(String[] args)
	{
		Piece[][] board = createBoard();
		printBoard(board);

	}

}
