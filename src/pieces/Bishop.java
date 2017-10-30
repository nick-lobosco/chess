package pieces;

import chess.Board;

public class Bishop extends Piece
{
	public Bishop(char player, int r, int c){
		super(player, r, c);
		this.type = 'B';
	}
	public boolean isValidMove(int rDest, int cDest, Board board, char plyr){
		if(super.isValidMove(rDest, cDest, board, plyr)){
			int diff = Math.abs(cDest-cCoord);
			if(diff != Math.abs(rDest-rCoord))
				return false;
			int rDir = (rDest - rCoord) >0 ? 1 : -1; 
			int cDir = (cDest - cCoord) >0 ? 1 : -1;
			for(int i=1; i<diff;i++){
				if(board.board[rCoord+rDir*i][cCoord+cDir*i]!=null)
						return false;
			}
			if(board.board[rCoord + rDir*diff][cCoord+cDir*diff]!=null)
			{
				if(board.board[rCoord + rDir*diff][cCoord+cDir*diff].player==plyr)
					return false;
			}
			return true;
		}
		return false;
	}
}
