package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() throws BoardException {
		this.board = new Board(8, 8);		
			initialSetup();
		
		}
	

	public Board getBoard() {
		return board;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] chessPieces = new ChessPiece[this.getBoard().getRows()][this.getBoard().getColumns()];
		for (int i = 0; i < chessPieces.length; i++) {
			for (int j = 0; j < chessPieces[i].length; j++) {
				try {
					chessPieces[i][j] = (ChessPiece) this.getBoard().piece(i, j);
				} catch (BoardException e) {					
					e.getMessage();
				}
			}

		}

		return chessPieces;

	}

	public void initialSetup() throws BoardException {
		
		
			this.getBoard().placePiece(new King(this.board, Color.WHITE), new Position(5, 7));
			this.getBoard().placePiece(new Rook(this.board, Color.WHITE), new Position(0, 7));
			this.getBoard().placePiece(new King(this.board, Color.BLACK), new Position(7, 6));
			this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new Position(5, 1));
		
		
	}
	
		
	}


