package pieces;

import chess.Board;

public class Queen extends Piece
{
	public Queen(char player, int r, int c){
		this.player = player;
		this.rCoord = r;
		this.cCoord = c;
		this.type = 'Q';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}
}
