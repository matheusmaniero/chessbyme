package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private Integer turn =  1;
	private Color currentPlayer = Color.WHITE;
	List<Piece> piecesOnTheBoard = new ArrayList<>();

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

		if (!(this.validateSourcePosition(sourcePosition)
				&& this.validateTargetPosition(targetPosition, sourcePosition))) {
			throw new ChessException("Is not possible to move to this position");
		} else {
			this.makeMove(sourcePosition, targetPosition);
			this.nextTurn();
		}

	}

	public boolean validateSourcePosition(ChessPosition sourcePosition) {
		ChessPiece piece = (ChessPiece)this.getBoard().piece(sourcePosition.toPosition());
		if (piece.getColor() != this.currentPlayer) {
			throw new ChessException("This piece is not yours.");
		}
		
		
		if ((this.getBoard().positionExists(sourcePosition.toPosition())
				&& this.getBoard().piece(sourcePosition.toPosition()).isThereAnyPossibleMove())) {
			return true;
		}

		return false;
	}

	public boolean validateTargetPosition(ChessPosition targetPosition, ChessPosition sourcePosition) {
		if (this.getBoard().positionExists(targetPosition.toPosition())
				&& this.getBoard().piece(sourcePosition.toPosition()).possibleMove(targetPosition.toPosition())) {
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

		this.getBoard().placePiece(new King(this.board, Color.WHITE), new ChessPosition('b',1).toPosition());
		//this.getBoard().placePiece(new Rook(this.board, Color.WHITE), new ChessPosition('h',1).toPosition());
		//this.getBoard().placePiece(new King(this.board, Color.BLACK), new ChessPosition('f',7).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new ChessPosition('h',1).toPosition());

	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition.toPosition())) {
			throw new ChessException("There is no piece in this position");
		}

		ChessPiece piece = (ChessPiece) this.getBoard().piece(sourcePosition.toPosition());
		boolean[][] mat = piece.possibleMoves();
		return mat;
	}
	
	
	private Color nextTurn() {
		if (this.currentPlayer == Color.WHITE) {
			this.currentPlayer = Color.BLACK;
		}else {
			this.currentPlayer = Color.WHITE;
		}
		
		this.turn++;
		
		return currentPlayer;
		
	}

}
