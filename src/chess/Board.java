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
	Piece[][] board;
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
	}
	
	public boolean makeTurn(String move, int turn){
		int y1 = move.charAt(0)-'a';
		int x1 = move.charAt(1) -'0' -1;
		int y2 = move.charAt(3) - 'a';
		int x2 = move.charAt(4) -'0' -1;
		char plyr = (turn == 0 ? 'w' : 'b');
		
		if(!(x1>=0 && x1<=7 && y1>=0 && y1<=7 && x2>=0 && x2<=7 && y2>=0 && y2<=7)) //pieces not on board
			return false;
		if(board[x1][y1] == null) //piece doesnt exist
			return false;
		if(board[x1][y1].player != plyr)//can't move opponents piece
			return false;
		if(board[x2][y2] != null){
			if(board[x2][y2].player == plyr)//can't move piece into teammate
				return false;
		}
		if(!board[x1][y1].isValidMove(x2,y2))//individual piece's valid move
			return false;
		board[x2][y2] = board[x1][y1]; //actually move piece
		board[x1][y1] = null;
		return true;
	}
}
