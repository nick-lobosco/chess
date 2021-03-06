/**
 * @author Nick, Nithin
 */
package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pieces.Pawn;


/**
 * Runs the chess game. Creates a board instance, manages player turns, and reads player input.
 * Processes resigns and draws. Ends the game if victory, draw, or resign.
 *
 *@author Nick
 *@author Nithin
 */

public class Chess
{
	public static void main(String[] args)
	{
		Board board = new Board();
		int turn = 0; //0=white, 1=black
		int state = 0; //1=valid, 0=invalid, -1= gameover
		board.print();
		BufferedReader br;
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			boolean canDraw = false;
			while(turn != -1){
				System.out.print((turn==0?"white's ":"black's ") + "move: ");
				s = br.readLine();
				if(s.equals("resign")){
					System.out.println(turn==0 ? "Black wins" : "White wins");
					System.exit(0);	
				}
				
				if(s.equals("draw") && canDraw)
				{
					System.out.println("draw");
					System.exit(0);
				}
				else{
					canDraw = false;}
				if(s.length()>7){
					String in = s.substring(6);
					if(in.equals("draw?"))
					{
						canDraw = true;
						s = s.substring(0, 5);
					}
				}
				state = board.makeTurn(s, turn);
				if(state == 0)
					System.out.println("Illegal Move, Try again");
				else if(state == 1){
					turn = (turn+1)%2;
					board.print();
				}
				else{
					//board.print();
					System.out.println(turn==1 ? "Black wins" : "White wins");
					return;
				}
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		board.print();
		
	}

}
