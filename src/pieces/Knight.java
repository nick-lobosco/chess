package pieces;


public class Knight extends Piece
{
	public Knight(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'N';
	}
	public boolean isValidMove(String move){
		return true;
	}
}
