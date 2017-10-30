package pieces;

import chess.Board;

public abstract class Piece
{
	public char player;
	public char type;
	public int rCoord;
	public int cCoord;
	public boolean hasMoved;
	
	public Piece(char player, int rCoord, int cCoord){
		this.player = player;
		this.rCoord = rCoord;
		this.cCoord = cCoord;
		this.hasMoved = false;
	}
	
	public String toString(){
		return Character.toString(player) + Character.toString(type);
	}
	
	public boolean isValidMove(int rDest, int cDest, Board board, char plyr){
		if(!(rCoord>=0 && rCoord<=7 && cCoord>=0 && cCoord<=7 && rDest>=0 && rDest<=7 && cDest>=0 && cDest<=7)) //pieces not on board
			return false;
		if(rCoord==rDest && cCoord==cDest)//not moving at all
			return false;
		if(board.board[rCoord][cCoord] == null) //piece doesnt exist
			return false;
		if(board.board[rCoord][cCoord].player != plyr)//can't move opponents piece
			return false;
		if(board.board[rDest][cDest] != null){
			if(board.board[rDest][cDest].player == plyr)//can't move piece into teammate's square
				return false;
		}
		return true;
	}
	
	public void move(int r, int c, Board b){
		this.hasMoved = true;
		/*if(check(r,c,b))
			System.out.println("Check");*/
		
		//move piece on board
		b.board[r][c] = b.board[rCoord][cCoord]; 
		b.board[rCoord][cCoord] = null;
		//move pieces own coordinates
		this.rCoord = r;
		this.cCoord = c;
	}
}
