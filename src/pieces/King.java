package pieces;

import chess.Board;

public class King extends Piece
{
	public King(char player, int r, int c){
		this.player = player;
		this.rCoord = r;
		this.cCoord = c;
		this.type = 'K';
		this.hasMoved = false;
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		if(rDest-rCoord == 0)
		{
			if(cDest-cCoord == 2) //trying to castle right
			{
				if(board.board[rCoord][cCoord+3] == null || this.hasMoved)
					return false;
				if(board.board[rCoord][cCoord+3].type != 'R' || board.board[rCoord][cCoord+3].hasMoved)
					return false;
				if(board.board[rCoord][cCoord+1] != null || board.board[rCoord][cCoord+2] != null)
					return false;
				return true;
			}
			else if(cDest - cCoord == -2)//trying to castle left
			{
				if(board.board[rCoord][cCoord-4] == null || this.hasMoved)
					return false;
				if(board.board[rCoord][cCoord-4].type != 'R' || board.board[rCoord][cCoord-4].hasMoved)
					return false;
				if(board.board[rCoord][cCoord-1]!=null || board.board[rCoord][cCoord-2]!=null || board.board[rCoord][cCoord-3]!=null)
					return false;
				return true;
			}
		}	
	
		if(Math.abs(rDest-rCoord)>1 || Math.abs(cDest-cCoord)>1)
			return false;
		
		return true;
	}
}
