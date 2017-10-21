package pieces;

import chess.Board;

public class Pawn extends Piece
{
	public Pawn(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'p';
	}
	public boolean isValidMove(int x, int y, Board board){
		return true;
	}
}
