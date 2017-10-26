package pieces;

import chess.Board;

public class Pawn extends Piece
{
	public boolean canPassant = false;
	public Pawn(char player, int r, int c){
		super(player, r, c);
		this.type = 'p';
		this.canPassant = false;
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		
		int dir = (this.player == 'w' ? 1 : -1);
		
		if(board.board[rDest][cDest] != null && Math.abs(cDest - cCoord) == 1 && rDest - rCoord == dir)	//captures
		{
			this.hasMoved = true;
			return true;
		}
		
		if(cDest - cCoord != 0 || board.board[rDest][cDest] != null)
		{
			return false;
		}
		
		if(!this.hasMoved && rDest - rCoord == 2*dir)		//if first step, can move 2 spaces
		{
			this.hasMoved = true;
			return true;
		}
		
		if(rDest - rCoord == dir)
		{
			this.hasMoved = true;
			return true;
		}
		
		return false;
	}
}
