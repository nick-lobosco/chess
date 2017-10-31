/**
 * @author Nick, Nithin
 */
package pieces;

import chess.Board;

/**
 * The base piece class that all the pieces inherit from.
 * Provides functionality common to all pieces, such as the piece location, movement, move validity.
 * 
 * @author Nick
 * @author Nithin
 *
 */
public abstract class Piece
{
	/**
	 * The player that this piece belongs to.
	 */
	public char player;
	/**
	 * The type of the piece, 'N' for knight, 'Q' for queen, etc...
	 */
	public char type;
	/**
	 * The row number of this piece.
	 */
	public int rCoord;
	/**
	 * The column number of this piece.
	 */
	public int cCoord;
	/**
	 * Indicates if this piece has moved before. Used for things such as castling and pawn movement.
	 */
	public boolean hasMoved;
	
	/**
	 * Base constructor for all pieces.
	 * 
	 * @param player	the player this piece belongs to
	 * @param rCoord	the row number
	 * @param cCoord	the column number
	 */
	public Piece(char player, int rCoord, int cCoord){
		this.player = player;
		this.rCoord = rCoord;
		this.cCoord = cCoord;
		this.hasMoved = false;
	}
	
	/**
	 * Returns the string representation of the piece
	 */
	public String toString(){
		return Character.toString(player) + Character.toString(type);
	}
	
	/**
	 * Basic validity checker common to all pieces. Checks to make sure not moving out of board, into a
	 * friendly piece, not moving enemy piece, and not moving an empty square. All other piece specific
	 * isValid methods call the isValidMove of the Piece class first. The isValidMove method is overridden for
	 * each subclass. Each subclass' isValidMove only returns true if the move specified is possible for the
	 * specific piece type.
	 * 
	 * <p>
	 * Pawn: Can move forward vertically by 1, unless another piece is in the way. Can move ahead by 2 steps
	 * if in initial row. Cannot move horizontally. Can only capture diagonally in the forward direction.
	 * Also checks for enpassant.
	 * 
	 * <p>
	 * 
	 * Rook: Can move only horizontally or only vertically. Cannot move past pieces, if piece is in the way,
	 * the squares behind it are not valid.
	 * 
	 * <p>
	 * 
	 * Knight: can move in 'L' shape as long as not off the board or into friendly piece.
	 * 
	 * <p>
	 * 
	 * Bishop: Same as Rook, but only diagonally. |rDest-rCoord| must be equal to |cDest-cCoord|
	 * 
	 * <p>
	 * 
	 * King: Can move by 1 step in any direction. If hasMoved is false, can potentially castle if no pieces
	 * are in the way.
	 * 
	 * <p>
	 * 
	 * Queen: Combination of Rook and Bishop.
	 * 
	 * @param rDest	destination row coordinate
	 * @param cDest	destination column coordinate
	 * @param board	the board instance that is being used for the game
	 * @param plyr	the player that is making this move
	 * @return	true if valid move, false otherwise
	 */
	
	public boolean isValidMove(int rDest, int cDest, Board board, char plyr){
		if(!(rCoord>=0 && rCoord<=7 && cCoord>=0 && cCoord<=7 && rDest>=0 && rDest<=7 && cDest>=0 && cDest<=7)) //pieces not on board
			return false;
		if(rCoord==rDest && cCoord==cDest)//not moving at all
			return false;
		if(board.board[rCoord][cCoord] == null) //piece doesnt exist
			return false;
		if(board.board[rCoord][cCoord].player != plyr)//can't move opponents piece
			return false;
		if(board.board[rDest][cDest] != null){
			if(board.board[rDest][cDest].player == plyr)//can't move piece into teammate's square
				return false;
		}
		return true;
	}
	
	/**
	 * Simply moves the piece to the location specified by r,c on board b and changes hasMoved to true.
	 * 
	 * @param r	the row to move to
	 * @param c	the column to move to
	 * @param b	the board instance moving on
	 */
	public void move(int r, int c, Board b){
		this.hasMoved = true;
		/*if(check(r,c,b))
			System.out.println("Check");*/
		
		//move piece on board
		b.board[r][c] = b.board[rCoord][cCoord]; 
		b.board[rCoord][cCoord] = null;
		//move pieces own coordinates
		this.rCoord = r;
		this.cCoord = c;
	}
}
