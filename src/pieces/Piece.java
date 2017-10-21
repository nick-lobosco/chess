package pieces;

import chess.Board;

public abstract class Piece
{
	public char player;
	public char type;
	public int rCoord;
	public int cCoord;
	public boolean hasMoved;
	public String toString(){
		return Character.toString(player) + Character.toString(type);
	}
	
	public abstract boolean isValidMove(int rDest, int cDest, Board board);
}
