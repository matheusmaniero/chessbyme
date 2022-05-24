package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
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
				chessPieces[i][j] = (ChessPiece) this.getBoard().piece(i, j);

			}

		}

		return chessPieces;

	}

	public void performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		if (!(this.validateSourcePosition(sourcePosition) && this.validateTargetPosition(targetPosition,sourcePosition))) {
			throw new ChessException("Is not possible to move to this position");
		} else {
			this.makeMove(sourcePosition, targetPosition);
		}

	}

	public boolean validateSourcePosition(ChessPosition sourcePosition) {
		if ((this.getBoard().positionExists(sourcePosition.toPosition())
				&& this.getBoard().piece(sourcePosition.toPosition()).isThereAnyPossibleMove())) {
			return true;
		}

		return false;
	}

	public boolean validateTargetPosition(ChessPosition targetPosition, ChessPosition sourcePosition) {
		if (this.getBoard().positionExists(targetPosition.toPosition()) && this.getBoard().piece(sourcePosition.toPosition()).possibleMove(targetPosition.toPosition()) ) {
			return true;
		} else {
			return false;
		}
	}

	public void makeMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		ChessPiece movingPiece = (ChessPiece) this.getBoard().removePiece(sourcePosition.toPosition());
		this.getBoard().placePiece(movingPiece, targetPosition.toPosition());
	}

	public void initialSetup() {

		// this.getBoard().placePiece(new King(this.board, Color.WHITE), new
		// ChessPosition('e', 1).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.WHITE), new ChessPosition('d', 7).toPosition());
		// this.getBoard().placePiece(new King(this.board, Color.BLACK), new
		// ChessPosition('b', 2).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new ChessPosition('a', 5).toPosition());

	}

}
