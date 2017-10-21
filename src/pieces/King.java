package pieces;

import chess.Board;

public class King extends Piece
{
	public King(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'K';
	}
	public boolean isValidMove(int x, int y, Board board){
		return true;
	}
}
