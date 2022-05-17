package application;

import boardgame.BoardException;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {		
		
		ChessMatch chessMatch;
		try {
			chessMatch = new ChessMatch();
			UI.printBoard(chessMatch);
		} catch (BoardException e) {			
			System.out.println(e.getMessage());
		}
		
	}

}
