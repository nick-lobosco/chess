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
	public boolean isValidMove(int rDest, int cDest, Board board, char plyr){
		
		
		if(super.isValidMove(rDest, cDest, board, plyr)){
			
		int dir = (this.player == 'w' ? 1 : -1);
		
		if(this.canPassant && rDest - rCoord == dir && cDest - cCoord == this.passantDir)	//execute enpassant
		{
			board.board[rCoord][cDest] = null;
			this.hasMoved = true;
			this.canPassant = false;
			return true;
		}
		
		if(board.board[rDest][cDest] != null && Math.abs(cDest - cCoord) == 1 && rDest - rCoord == dir)	//captures
		{
			this.hasMoved = true;
			return true;
		}
		
		if(cDest - cCoord != 0 || board.board[rDest][cDest] != null)	//cant move horizontally or if somethings in the way	
		{
			return false;
		}
		
		if(!this.hasMoved && (rDest - rCoord == 2*dir) && board.board[rCoord+dir][cDest] == null)	//if first step, can move 2 spaces
		{
			int right = cDest + 1;										//enable enpassant
			int left = cDest - 1;
			if(right < 8 && board.board[rDest][right] != null && board.board[rDest][right].type == 'p')
			{
				Pawn p = (Pawn) board.board[rDest][right];
				p.canPassant = true;
				p.passantDir = -1;
				board.passantables.add(p);
			}
			
			if(left >= 0 && board.board[rDest][left] != null && board.board[rDest][left].type == 'p')
			{
				Pawn p = (Pawn) board.board[rDest][left];
				p.canPassant = true;
				p.passantDir = 1;
				board.passantables.add(p);
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
	
	return false;
	}
}
