package application;

import java.util.Scanner;

import boardgame.BoardException;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args)  {		
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch;
		
			chessMatch = new ChessMatch();
			
			
			while(true) {
				
				try {
					UI.printBoard(chessMatch);
					UI.readChessPosition(sc, chessMatch);
				}catch (BoardException e) {
					UI.clearScreen();
					System.out.println(e.getMessage());
					System.out.println();
					
				}
				
				
				
			}
		
		
	}

}
