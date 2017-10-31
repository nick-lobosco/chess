/**
 * @author Nick, Nithin
 */
package chess;

import java.util.ArrayList;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * Manages board state and holds all pieces.
 * Keeps track of the entire chess board, with pieces held in a 2D array list of pieces.
 * Also keeps track of locations of both kings to help with detecting checks.
 * Cotains an arraylist of pawns that can employ enpassant.
 * 
 * @author Nick
 * @author Nithin
 *
 */
public class Board
{
	/** 
	 * Row number of white's king.
	 */   
	public int wKingR = 0;
	/** 
	 * Column number of white's king.
	 */   
	public int wKingC = 4;
	/**
	 * Row number of black's king.
	 */
	public int bKingR = 7;
	/**
	 * Column number of black's king.
	 */
	public int bKingC = 4;
	/**
	 * ArrayList of pawns that can use enpassant.
	 */
	public ArrayList<Pawn> passantables;
	
	/**
	 * 2D array of pieces that represents the whole board.
	 */
	public Piece[][] board;
	/**
	 * Board constructor. Initializes the board with the standard initial locations for each piece.
	 * Contains fields for the king locations set to default initial locations. Initializes an empty
	 * arraylist of pawns that can use enpassant.
	 */
	public Board(){
		passantables = new ArrayList<Pawn>();	//need to keep track of which pawns can passant
		
		board = new Piece[8][8];
		for(int i=0;i<8;i++){
			board[1][i] = new Pawn('w',1,i);
			board[6][i] = new Pawn('b',6,i);
		}
		board[0][0] = new Rook('w',0,0);
		board[0][7] = new Rook('w',0,7);
		board[7][0] = new Rook('b',7,0);
		board[7][7] = new Rook('b',7,7);
		
		board[0][1] = new Knight('w',0,1);
		board[0][6] = new Knight('w',0,6);
		board[7][1] = new Knight('b',7,1);
		board[7][6] = new Knight('b',7,6);
		
		board[0][2] = new Bishop('w',0,2);
		board[0][5] = new Bishop('w',0,5);
		board[7][2] = new Bishop('b',7,2);
		board[7][5] = new Bishop('b',7,5);
		
		board[0][3] = new Queen('w',0,3);
		board[0][4] = new King('w',0,4);
		board[7][3] = new Queen('b',7,3);
		board[7][4] = new King('b',7,4);
	}
	
	/**
	 * Executes the given move for a player. The move is a string that encodes the players instruction.
	 * This method parses the move, checks if the move is a valid move according to the piece classes and 
	 * executes the move on this board instance if it is a valid move. 
	 * <p>
	 * Also checks for check, checkmate, and stalemate after a move is executed. Returns an integer to indicate
	 * the success of the move.
	 * 
	 * @param move	the user input string as sent from the main method from the Chess class.
	 * @param turn	the current player turn, 0 is white and 1 is black
	 * @return	returns 1 for valid move, 0 for invalid, and -1 if game over (checkmate, stalemate)
	 */
	
	public int makeTurn(String move, int turn){
		//breakdown move String
		char promo = 'Q';
		int c1 = move.charAt(0)-'a';
		int r1 = move.charAt(1) -'0' -1;
		int c2 = move.charAt(3) - 'a';
		int r2 = move.charAt(4) -'0' -1;
		char plyr = (turn == 0 ? 'w' : 'b');
		char oppPlyr = (turn == 0 ? 'b' : 'w');
		boolean canDraw = false;
		if(move.length()==7){
			promo = move.charAt(6);
		}
		
			//can't enpassant if not used immediately
		for(int i = 0; i<this.passantables.size();i++)
		{
			Pawn p = this.passantables.get(i);
			if(p.player == oppPlyr)
			{
				p.canPassant = false;
				this.passantables.remove(i);
			}
		}
		
		//validate move
		if(board[r1][c1]==null)
			return 0;
		if(!board[r1][c1].isValidMove(r2,c2, this, plyr))//individual piece's valid move
			return 0;
		//actually move piece
		board[r1][c1].move(r2, c2, this);
		
		//promotions
		int end = plyr=='w' ? 7:0;
		if(r2 == end && board[r2][c2].type == 'p'){
			promote(board[r2][c2], promo, plyr);
		}
		
		
		//testForCheck
		ArrayList<Piece> attackers = (plyr == 'w' ? canMoveTo(bKingR, bKingC, plyr) : canMoveTo(wKingR, wKingC, plyr));
		//testForCheckMate
		if(attackers.size()>0){
			if(isCheckMate(oppPlyr, plyr, attackers)){
				System.out.println("Checkmate");
				return -1;
			}
			else
				System.out.println("Check");
		}
		if(isStaleMate(oppPlyr)){
			System.out.println("Stalemate");
			return -1;
		}
		return 1;
	}
	
	/**
	 * Checks for stalemate.
	 * 
	 * @param plyr character indicating the player to check stalemate with respect to. 'w' for white, 'b' for black.
	 * @return	true if stalemate, false otherwise
	 */
	
	public boolean isStaleMate(char plyr){
		for(int i=0; i<8;i++){
			for(int j=0; j<8; j++){
				if(canMoveTo(i,j,plyr).size() > 0)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks for checkmate. Checks if checkmate has occurred by first checking if the king can move to a location
	 * that is not attacked. If only one attacker, also checks if attacker can be blocked or captured. If there
	 * is no way for the king to escape check, returns true, false otherwise.
	 * 
	 * @param plyr	player who is potentially checkmated
	 * @param oppPlyr	player who just made the move and will win if checkmate
	 * @param attackers list of current attackers that are attacking the king.
	 * @return	true if checkmate, false otherwise
	 */
	public boolean isCheckMate(char plyr,char oppPlyr, ArrayList<Piece> attackers){
		//plyr is the color that is in check
		//tests if king can move
		int r = (plyr == 'b' ? bKingR : wKingR);
		int c = (plyr == 'b' ? bKingC : wKingC);
		int row[] = {r+1,r+1,r+1,r,r,r-1,r-1,r-1};
		int col[] = {c-1,c,c+1,c-1,c+1,c-1,c,c+1};
		Piece p;
		for(int i=0;i<8;i++){
			if(row[i] <8 && row[i] >=0 && col[i] <8 && col[i] >=0){
				p = board[row[i]][col[i]];
				if(p == null){
					if(canMoveTo(row[i],col[i], oppPlyr).size() == 0)
						return false;
				}
				else if(p.player!=plyr && (canMoveTo(row[i],col[i], oppPlyr).size() == 0))
					return false;
			}
		}
		if( attackers.size() > 1 ) //if there are more than 1 attacker, can only get out of check by moving King
			return true;
		else{ //if only one attacker, can move king, kill attacker, or block attacker
			p = attackers.get(0);
			if(p.type == 'N') //check from one knight
				return canMoveTo(p.rCoord, p.cCoord, plyr).size() == 0; //can't block knight
			int kR = (plyr=='b'?bKingR:wKingR);
			int kC = (plyr=='b'?bKingC:wKingC);
			int rDiff, cDiff;
			rDiff = (p.rCoord-kR > 0 ? 1 : -1);
			if(p.rCoord-kR == 0)
				rDiff = 0;
			cDiff = p.cCoord-kC>0 ? 1 : -1;
			if(p.cCoord-kC == 0)
				cDiff = 0;
			//int r,c;
			for(int i=1; (rDiff==0 || (r=kR+i*rDiff)!=p.rCoord+rDiff) && (cDiff == 0 || (c=kC+i*cDiff)!=p.cCoord+cDiff);i++){
				ArrayList<Piece> x = canMoveTo(r,c,plyr);
				if(x.size()>0)
					return false;
			}
			return true;
		}
		
	}
	
	/**
	 * Checks if oppPlyer can attack the square specified by row r and column c.
	 * Creates an ArrayList of all the pieces that belong to oppPlyer and can move to square (r,c).
	 * 
	 * @param r	the row of the square being tested against
	 * @param c	the column of the square being tested against
	 * @param oppPlyr	the player whose pieces are checked if can move to square
	 * @return	ArrayList of pieces that belong to oppPlyr and that can attack square (r,c)
	 */
	public ArrayList<Piece> canMoveTo(int r, int c, char oppPlyr){
		ArrayList<Piece> attackers = new ArrayList<Piece>();
		Piece p;
		
		int pawnRow = (oppPlyr == 'b' ? 1 : -1);
		if(r+pawnRow>=0 && r+pawnRow<8){
			if((p=board[r][c])!=null){
				if(p.player != oppPlyr){
					if(c+1<8){
						if((p=board[r+pawnRow][c+1]) != null){
							if(p.player == oppPlyr && p.type=='p'){
								attackers.add(p);
							}
						}
					}
					if(c-1>=0){
						if((p=board[r+pawnRow][c-1]) != null){
							if(p.player == oppPlyr && p.type=='p')
								attackers.add(p);
						}
					}
				}
			}
			else{
				if((p=board[r+pawnRow][c]) != null){
					if(p.player == oppPlyr && p.type=='p')
						attackers.add(p);
				}
				else{
					if(r+2*pawnRow>=0 && r+2*pawnRow<8){
						if((p=board[r+2*pawnRow][c]) != null){
							if(p.player == oppPlyr && p.type=='p' && !p.hasMoved)
								attackers.add(p);
						}
					}
				}
			}
		}
			
		int rowIncs[] = {1, 1, -1, -1, 1, -1, 0, 0};
		int colIncs[] = {1, -1, 1, -1, 0, 0, 1, -1};
		int nRows[] = {r+1, r+1, r+2, r+2, r-1, r-1, r-2, r-2};
		int nCols[] = {c-2, c+2, c-1, c+1, c-2, c+2, c-1, c+1};
		for(int i=0;i<8;i++){
			for(int j=1; r+j*rowIncs[i]>=0 && r+j*rowIncs[i]<8 && c+j*colIncs[i]>=0 && c+j*colIncs[i]<8; j++){
				p = board[r+j*rowIncs[i]][c+j*colIncs[i]];
				char x = i<4 ? 'B' : 'R';
				if(p != null){
					if(p.player != oppPlyr)
						break;
					else {
						if(p.type==x || p.type == 'Q')
							attackers.add(p);
						else
							break;
						
					}
				}
			}
			if(nRows[i] <8 && nRows[i] >=0 && nCols[i] <8 && nCols[i] >=0){
				p = board[nRows[i]][nCols[i]];
				if(p != null){
					if(p.player == oppPlyr && p.type == 'N')
						attackers.add(p);
				}
			}
		}
		return attackers;
	}
	
	/**
	 * Promotes a pawn to chosen piece.
	 * Called when a pawn reaches the opposite end of the board. Promotes that pawn to the piece specified
	 * by the player, passed through the param promo. Defaults to a promotion to Queen. Places a piece of
	 * of type specified by promo on the board at the location p is.
	 * 
	 * @param p	the piece that is being promoted
	 * @param promo	the piece to be promoted to
	 * @param plyr	sets the new piece's player field to this char
	 */
	public void promote(Piece p, char promo, char plyr)
	{
		int r = p.rCoord;
		int c = p.cCoord;
		System.out.println(promo);
		switch(promo){
		case 'Q':
			this.board[r][c] = new Queen(plyr, r, c);
			break;
		case 'N':
			this.board[r][c] = new Knight(plyr, r, c);
			break;
		case 'R':
			this.board[r][c] = new Rook(plyr, r, c);
			break;
		case 'B':
			this.board[r][c] = new Bishop(plyr, r, c);
			break;
		case 'p':
			this.board[r][c] = new Pawn(plyr, r, c);
			break;
		default:
			this.board[r][c] = new Queen(plyr, r, c);
			break;
		}
	}
	
	/**
	 * Prints the board as specified.
	 */
	public void print(){
		System.out.println();
		for(int i=7; i>=0; i--){
			for(int j=0; j<8; j++){
				if(board[i][j] != null)
					System.out.print(board[i][j] + " ");
				else
					System.out.print((i+j)%2==0 ? "## " : "   ");
			}
			System.out.print(i+1 +"\n");
		}
		for(int i=0; i <8;i++)
			System.out.print(" " + (char)('a'+i) + " ");
		System.out.println("\n");
	}
}
