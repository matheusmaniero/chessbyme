package application;

import java.util.Scanner;

import boardgame.BoardException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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
		} else if (piece.getColor() == Color.BLACK) {
			System.out.print(ANSI_BLUE_BACKGROUND + ANSI_YELLOW + " " + piece + " " + ANSI_RESET);
		} else {
			System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + " " + piece + " " + ANSI_RESET);
		}

	}

	public static void readChessPosition(Scanner sc, ChessMatch chessMatch) {
		System.out.println();
		System.out.print("Source: ");
		String sourceString = sc.nextLine();
		ChessPosition sourceChessPosition = new ChessPosition(sourceString.charAt(0),
				Integer.parseInt(sourceString.substring(1)));

		chessMatch.getBoard().positionExists(sourceChessPosition.toPosition());
		chessMatch.getBoard().thereIsAPiece(sourceChessPosition.toPosition());

		System.out.print("Target: ");
		String targetString = sc.nextLine();
		ChessPosition targetChessPosition = new ChessPosition(targetString.charAt(0),
				Integer.parseInt(targetString.substring(1)));
		
		chessMatch.getBoard().positionExists(targetChessPosition.toPosition());
		

		chessMatch.performChessMove(sourceChessPosition, targetChessPosition);
	}

}
