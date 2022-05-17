package application;

import chess.ChessMatch;
import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessMatch chessMatch) {
		for (int i = 0; i < chessMatch.getPieces().length; i++) {
			System.out.print(8 - i);
			
			for (int j = 0; j < chessMatch.getPieces().length; j++) {
				printPiece(chessMatch.getPieces()[i][j]);
				
			}
			System.out.println();
		}
		
		System.out.println("  a  b  c  d  e  f  g  h");
	}

	public static void printPiece(ChessPiece piece) {		
		if (piece == null) {
			System.out.print(" - ");
		}else {
			System.out.print(" "+piece+" ");
		}
		
	}

}
