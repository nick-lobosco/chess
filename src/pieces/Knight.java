package pieces;

import chess.Board;

public class Knight extends Piece
{
	public Knight(char player, int r, int c){
		this.player = player;
		this.rCoord = r;
		this.cCoord = c;
		this.type = 'N';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}
}
