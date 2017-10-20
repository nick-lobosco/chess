package pieces;


public class King extends Piece
{
	public King(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'K';
	}
	public boolean isValidMove(String move){
		return true;
	}
}
