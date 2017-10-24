package pieces;

import chess.Board;

public class Knight extends Piece
{
	public Knight(char player, int r, int c){
		super(player, r, c);
		this.type = 'N';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}
}
