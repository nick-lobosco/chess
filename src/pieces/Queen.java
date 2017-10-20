package pieces;


public class Queen extends Piece
{
	public Queen(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'Q';
	}
	public boolean isValidMove(int x, int y){
		return true;
	}
}
