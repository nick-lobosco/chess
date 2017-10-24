package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board
{
	public int wkr;
	public int wkc;
	public int bkr;
	public int bkc;

	public Piece[][] board;
	public Board(){
		board = new Piece[8][8];
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
		
		wkr = 0;
		wkc = 4;
		bkr = 7;
		bkc = 4;
	}
	
	public void print(){
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
		System.out.println();
	}
	
	public boolean makeTurn(String move, int turn){
		int c1 = move.charAt(0)-'a';
		int r1 = move.charAt(1) -'0' -1;
		int c2 = move.charAt(3) - 'a';
		int r2 = move.charAt(4) -'0' -1;
		char plyr = (turn == 0 ? 'w' : 'b');
		
		if(r1==r2 && c1==c2)//not moving at all
		{
			System.out.println("1");
			return false;
		}
		if(!(r1>=0 && r1<=7 && c1>=0 && c1<=7 && r2>=0 && r2<=7 && c2>=0 && c2<=7)) //pieces not on board
		{
			System.out.println("2");
			return false;
		}
		if(board[r1][c1] == null) //piece doesnt exist
		{
			System.out.println("3");
			return false;
		}
		if(board[r1][c1].player != plyr)//can't move opponents piece
		{
			System.out.println("4");
			return false;
		}
		if(board[r2][c2] != null){
			System.out.println("5");
			if(board[r2][c2].player == plyr)//can't move piece into teammate's square
				return false;
		}
		if(!board[r1][c1].isValidMove(r2,c2, this))//individual piece's valid move
		{
			System.out.println("6");
			return false;
		}

		if(board[r1][c1].type == 'K'){
			if(c2 - c1 == -2){ //castle left
				board[r1][3] = board[r1][0];
				board[r1][0] = null;
			}
			else if(c2 - c1 == 2){
				board[r1][5] = board[r1][7];
				board[r1][7] = null;
			}
		}
		
		board[r2][c2] = board[r1][c1]; //actually move piece
		board[r2][c2].move(r2, c2);
		board[r1][c1] = null;
		return true;
	}
}
