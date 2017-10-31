package pieces;

import chess.Board;

public class Knight extends Piece
{
	public Knight(char player, int r, int c){
		super(player, r, c);
		this.type = 'N';
	}
	public boolean isValidMove(int rDest, int cDest, Board board, char plyr){
		if(super.isValidMove(rDest, cDest, board, plyr)){
			
			if(Math.abs(rDest-rCoord) == 2 && Math.abs(cDest - cCoord) == 1)
			{
				return true;
			}
			
			else if(Math.abs(rDest-rCoord) == 1 && Math.abs(cDest - cCoord) == 2)
			{
				return true;
			}
		
			return false;
		}
		
		
		return false;
	}
}
