package pieces;

import chess.Board;

public class Knight extends Piece
{
	public Knight(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'N';
	}
	public boolean isValidMove(int x, int y, Board board){
		return true;
	}
}
