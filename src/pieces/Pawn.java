package pieces;

import chess.Board;

public class Pawn extends Piece
{
	public Pawn(char player, int r, int c){
		super(player, r, c);
		this.type = 'p';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}
}
