package pieces;


public class Rook extends Piece
{	
	public Rook(char player, int x, int y){
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.type = 'R';
	}
	public boolean isValidMove(String move){
		return true;
	}

}
