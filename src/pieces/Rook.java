package pieces;

import chess.Board;

public class Rook extends Piece
{
	public Rook(char player, int r, int c){
		super(player, r, c);
		this.type = 'R';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		if(cDest - cCoord == 0) //moving vertically
		{
			int rDir = (rDest - rCoord) >0 ? 1 : -1; 
			for(int i=1; i<Math.abs(rDest-rCoord);i++){
				if(board.board[rCoord+rDir*i][cCoord]!=null)
					return false;
			}
		}
		else if(rDest - rCoord == 0)//moving horizontally
		{
			int cDir = (cDest - cCoord) >0 ? 1 : -1;
			for(int i=1; i<Math.abs(cDest-cCoord);i++){
				if(board.board[rCoord][cCoord+cDir*i]!=null)
					return false;
			}
			
		}
		else//trying to move both vertically and horizontally
			return false;
		return true;
	}

}
