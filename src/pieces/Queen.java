package pieces;

import chess.Board;

public class Queen extends Piece
{
	public Queen(char player, int r, int c){
		super(player, r, c);
		this.type = 'Q';
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		
		if(cDest - cCoord == 0) //moving vertically
		{
			int rDir = (rDest - rCoord) >0 ? 1 : -1; 
			for(int i=1; i<Math.abs(rDest-rCoord);i++){
				if(board.board[rCoord+rDir*i][cCoord]!=null)
					return false;
			}
			return true;
		}
		else if(rDest - rCoord == 0)//moving horizontally
		{
			int cDir = (cDest - cCoord) >0 ? 1 : -1;
			for(int i=1; i<Math.abs(cDest-cCoord);i++){
				if(board.board[rCoord][cCoord+cDir*i]!=null)
					return false;
			}
			return true;
			
		}
		
		int diff = Math.abs(cDest-cCoord);		//moving diagonally
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
