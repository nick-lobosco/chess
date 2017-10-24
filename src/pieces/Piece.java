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
	
	public void move(int r, int c){
		this.rCoord = r;
		this.cCoord = c;
		this.hasMoved = true;
	}
	
	public abstract boolean isValidMove(int rDest, int cDest, Board board);
}
