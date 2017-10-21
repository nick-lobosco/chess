package pieces;

import chess.Board;

public class Bishop extends Piece
{
	public Bishop(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'B';
	}
	public boolean isValidMove(int xDest, int yDest, Board board){
		int diff = Math.abs(yDest-yCoord);
		if(diff != Math.abs(xDest-xCoord))
			return false;
		//System.out.println("passed");
		int xDir = (xDest - xCoord) >0 ? 1 : -1; 
		int yDir = (yDest - yCoord) >0 ? 1 : -1;
		for(int i=1; i<=diff;i++){
			if(board.board[xCoord+xDir*i][yCoord+yDir*i]!=null)
				return false;
		}
		return true;
	}
}
