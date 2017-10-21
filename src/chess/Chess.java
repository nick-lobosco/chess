package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Chess
{
	public static void main(String[] args)
	{
		Board board = new Board();
		int turn = 0; //0=white, 1=black, -1 = gameover
		boolean valid;
		board.print();
		BufferedReader br;
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			String s;
			while(turn != -1){
				System.out.println((turn==0?"white's ":"black's ") + "turn");
				s = br.readLine();
				if(s.equals("resign")){
					System.out.println(turn==0 ? "Black wins" : "White wins");
					turn = -1;
				}
				if(!(valid = board.makeTurn(s, turn)))
					System.out.println("Illegal Move, Try again");
				else{
					turn = (turn+1)%2;
					board.print();
				}
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		board.print();
		
	}

}
