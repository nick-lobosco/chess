package pieces;

import chess.Board;

public class Queen extends Piece
{
	public Queen(char player, int r, int c){
		super(player, r, c);
		this.type = 'Q';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		return true;
	}
}
