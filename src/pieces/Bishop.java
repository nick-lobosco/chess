package pieces;


public class Bishop extends Piece
{
	public Bishop(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'B';
	}
	public boolean isValidMove(String move){
		return true;
	}
}
