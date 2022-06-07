package application;

import java.util.Scanner;

import boardgame.BoardException;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ChessMatch chessMatch;

		chessMatch = new ChessMatch();

		while (!chessMatch.isCheckMate()) {

			try {
				UI.printBoard(chessMatch);
				System.out.println();
				System.out.println("Captured Pieces: ");
				System.out.println(UI.printWhiteCapturedPieces(chessMatch.getCapturedPieces()));				
				System.out.println(UI.printBlackCapturedPieces(chessMatch.getCapturedPieces()));
				System.out.println();
				System.out.println(UI.printTurn(chessMatch));
				System.out.println(UI.printCheck(chessMatch));
				

				UI.readChessPosition(sc, chessMatch);
				System.out.println();

			} catch (BoardException e) {
				UI.clearScreen();
				System.out.println(e.getMessage());
				System.out.println();

			}
			
			

		}
		
		System.out.println("CHECK MATE!");

	}

}
