package pieces;

import chess.Board;

public class Rook extends Piece
{	
	public Rook(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'R';
	}
	public boolean isValidMove(int x, int y, Board board){
		return true;
	}

}
