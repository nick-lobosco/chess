package pieces;

import chess.Board;

public class Rook extends Piece
{
	public Rook(char player, int r, int c){
		this.player = player;
		this.rCoord = r;
		this.cCoord = c;
		this.type = 'R';
		this.hasMoved = false;
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}

}
