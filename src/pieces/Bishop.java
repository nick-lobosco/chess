package pieces;

import chess.Board;

public class Bishop extends Piece
{
	public Bishop(char player, int r, int c){
		this.player = player;
		this.rCoord = r;
		this.cCoord = c;
		this.type = 'B';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		int diff = Math.abs(cDest-cCoord);
		if(diff != Math.abs(rDest-rCoord))
			return false;
		int rDir = (rDest - rCoord) >0 ? 1 : -1; 
		int cDir = (cDest - cCoord) >0 ? 1 : -1;
		for(int i=1; i<=diff;i++){
			if(board.board[rCoord+rDir*i][cCoord+cDir*i]!=null)
				return false;
		}
		return true;
	}
}
