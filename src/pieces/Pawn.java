package pieces;

import chess.Board;

public class Pawn extends Piece
{
	public Pawn(char player, int r, int c){
		this.player = player;
		this.rCoord = r;
		this.cCoord = c;
		this.type = 'p';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}
}
