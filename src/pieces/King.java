package pieces;

import chess.Board;

public class King extends Piece
{
	public King(char player, int r, int c){
		super(player, r, c);
		this.type = 'K';
	}
	
	public boolean isValidMove(int rDest, int cDest, Board board, char plyr){
		if(super.isValidMove(rDest, cDest, board, plyr))
		{
			//check for castling
			if(rDest-rCoord == 0)
			{
				if(Math.abs(cDest - cCoord) == 2){
					int dest = (cDest-cCoord > 0 ? 7 : 0);
					int sign = (cDest-cCoord > 0 ? 1 : -1);
					if(board.board[rCoord][dest] == null || this.hasMoved)
						return false;
					if(board.board[rCoord][dest].type != 'R' || board.board[rCoord][dest].hasMoved)
						return false;
					for(int i = 1; cCoord+i*sign!=dest;i++){
						if(board.board[rCoord][cCoord+i*sign] != null)
							return false;
					}
					return true;
				}
			}	
			if(Math.abs(rDest-rCoord)>1 || Math.abs(cDest-cCoord)>1)
				return false;
			return true;
		}
		return false;
	}
	
	public void move(int r, int c, Board b){
		if(c - cCoord == -2){
			b.board[r][0].move(r, 3, b); //move rook
		}
		else if(c - cCoord == 2){ //castle right
			b.board[r][7].move(r,5,b); //move rook
		}
		if(player=='w'){//moving white king
			b.wKingR = r;
			b.wKingC = c;
		}
		else{ //moving black king
			b.bKingR = r;
			b.bKingC = c;
		}
		super.move(r, c, b);
	
		
	}
}
