package pieces;

import chess.Board;

public abstract class Piece
{
	public char player;
	public char type;
	public int xCoord;
	public int yCoord;
	public String toString(){
		return Character.toString(player) + Character.toString(type);
	}
	
	public abstract boolean isValidMove(int x, int y, Board board);
}
