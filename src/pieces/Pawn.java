package pieces;

import chess.Board;

public class Pawn extends Piece
{
	public boolean canPassant = false;
	public int passantDir;
	public Pawn(char player, int r, int c){
		super(player, r, c);
		this.type = 'p';
		this.canPassant = false;
		this.passantDir = 0;
	}
	public boolean isValidMove(int rDest, int cDest, Board board){
		
		int dir = (this.player == 'w' ? 1 : -1);
		
		if(this.canPassant && rDest - rCoord == dir && cDest - cCoord == this.passantDir)	//execute enpassant
		{
			board.board[rCoord][cDest] = null;
			this.hasMoved = true;
			return true;
		}
		
		if(board.board[rDest][cDest] != null && Math.abs(cDest - cCoord) == 1 && rDest - rCoord == dir)	//captures
		{
			this.hasMoved = true;
			return true;
		}
		
		if(cDest - cCoord != 0 || board.board[rDest][cDest] != null)	//cant move horizontal
		{
			return false;
		}
		
		if(!this.hasMoved && rDest - rCoord == 2*dir && board.board[rDest+dir][cDest] == null)	//if first step, can move 2 spaces
		{
			int right = cDest + 1;										//enable enpassant
			int left = cDest - 1;
			if(right < 8 && board.board[rDest][right] != null && board.board[rDest][right].type == 'p')
			{
				Pawn p = (Pawn) board.board[rDest][right];
				p.canPassant = true;
				p.passantDir = -1;
			}
			
			if(left >= 0 && board.board[rDest][left] != null && board.board[rDest][left].type == 'p')
			{
				Pawn p = (Pawn) board.board[rDest][left];
				p.canPassant = true;
				p.passantDir = 1;
			}
			
			
			this.hasMoved = true;
			return true;
		}
		
		if(rDest - rCoord == dir)		//regular move
		{
			this.hasMoved = true;
			return true;
		}
		
		return false;
	}
}
