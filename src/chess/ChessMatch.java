package chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {		
		this.board = new Board(8,8);
	}

	public Board getBoard() {
		return board;
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] chessPieces = new ChessPiece[this.getBoard().getRows()][this.getBoard().getRows()];
	 return chessPieces;
		
	}

}



