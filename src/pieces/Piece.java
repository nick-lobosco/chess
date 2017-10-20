package pieces;


public abstract class Piece
{
	char player;
	char type;
	int xCoord;
	int yCoord;
	public String toString(){
		return Character.toString(player) + Character.toString(type);
	}
	
	public abstract boolean isValidMove(String move);
}
