package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private Integer turn = 1;
	private Color currentPlayer = Color.WHITE;
	private List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
	private List<ChessPiece> capturedPieces = new ArrayList<>();
	private boolean check;
	private boolean checkMate;

	public ChessMatch() {
		this.board = new Board(8, 8);
		initialSetup();
		loadPiecesOnBoardList();

	}

	public boolean isCheckMate() {
		return checkMate;
	}

	public boolean isCheck() {
		return check;
	}

	public Integer getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public List<ChessPiece> getPiecesOnTheBoard() {
		return piecesOnTheBoard;
	}

	public List<ChessPiece> getCapturedPieces() {
		return capturedPieces;
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

	public void performChessMove(ChessPosition sourceChessPosition, ChessPosition targetChessPosition) {
		Position sourcePosition = sourceChessPosition.toPosition();
		Position targetPosition = targetChessPosition.toPosition();
		ChessPiece[][] mat = getPieces();
		if (!(this.validateSourcePosition(sourceChessPosition)
				&& this.validateTargetPosition(targetChessPosition, sourceChessPosition))) {
			throw new ChessException("Is not possible to move to this position");

		} else {

			ChessPiece piece = (ChessPiece) this.board.piece(sourcePosition);

			if (piece instanceof King) {
				this.makeMove(sourcePosition, targetPosition);
				this.nextTurn();
				if (isTheKingUnderCheck()) {
					this.undoMove(targetPosition, sourcePosition);
					throw new ChessException("You can't put yourself in check!");
				}

			} else if (!this.check) {

				this.makeMove(sourcePosition, targetPosition);
				this.testCheck(targetChessPosition);

				this.nextTurn();
				this.testCheckMate();
			} else {
				this.makeMove(sourcePosition, targetPosition);
				this.nextTurn();
				this.testCheckMate();
				if (isTheKingUnderCheck()) {
					this.undoMove(targetPosition, sourcePosition);

				}
			}

		}
	}

	public boolean isTheKingUnderCheck() {

		ChessPiece[][] mat = this.getPieces();
		Position enemyKingPosition = new Position(0,0);
		

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] instanceof King && mat[i][j].getColor() != this.currentPlayer) {
					ChessPiece opKing = mat[i][j];
					enemyKingPosition.setValues(i, j);
				}
			}

		}

		 

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] instanceof ChessPiece && mat[i][j].getColor() == this.currentPlayer) {
					ChessPiece piece = mat[i][j];
					if (piece.possibleMove(enemyKingPosition)) {
						this.check = true;
						return true;
					}

				}
			}

		}
		this.check = false;
		return false;

	}

	public boolean validateSourcePosition(ChessPosition sourcePosition) {
		ChessPiece piece = (ChessPiece) this.getBoard().piece(sourcePosition.toPosition());
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

	public void makeMove(Position sourcePosition, Position targetPosition) {

		ChessPiece movingPiece = (ChessPiece) this.getBoard().removePiece(sourcePosition);
		if (this.getBoard().thereIsAPiece(targetPosition)) {
			ChessPiece pieceOnTarget = (ChessPiece) this.getBoard().piece(targetPosition);
			if (pieceOnTarget.getColor() != currentPlayer) {
				this.piecesOnTheBoard.removeIf(x -> x == pieceOnTarget);
				this.capturedPieces.add(pieceOnTarget);
			}
		}

		this.getBoard().placePiece(movingPiece, targetPosition);
	}

	public void undoMove(Position sourcePosition, Position targetPosition) {
		ChessPiece movingPiece = (ChessPiece) this.getBoard().removePiece(sourcePosition);
		this.getBoard().placePiece(movingPiece, targetPosition);
		this.capturedPieces.removeIf(x -> x == movingPiece);
		this.piecesOnTheBoard.add(movingPiece);
		this.nextTurn();
		this.turn--;
		this.turn--;
	}

	public void initialSetup() {

		this.getBoard().placePiece(new King(this.board, Color.WHITE), new ChessPosition('a', 3).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.WHITE), new ChessPosition('d', 3).toPosition());
		this.getBoard().placePiece(new King(this.board, Color.BLACK), new ChessPosition('e', 2).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new ChessPosition('b', 4).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new ChessPosition('b', 2).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new ChessPosition('b', 7).toPosition());
		this.getBoard().placePiece(new Rook(this.board, Color.BLACK), new ChessPosition('e', 3).toPosition());
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
		} else {
			this.currentPlayer = Color.WHITE;
		}

		this.turn++;

		return currentPlayer;

	}

	public void loadPiecesOnBoardList() {
		ChessPiece[][] chessPieces = this.getPieces();
		for (int i = 0; i < chessPieces.length; i++) {
			for (int j = 0; j < chessPieces.length; j++) {
				piecesOnTheBoard.add(chessPieces[i][j]);
			}
		}
	}

	public boolean testCheck(ChessPosition targetPosition) {
		ChessPiece[][] mat = this.getPieces();
		ChessPiece opKing = null;
		boolean[][] possibleMoves = this.possibleMoves(targetPosition);

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] instanceof King && mat[i][j].getColor() != this.currentPlayer) {
					opKing = mat[i][j];
				}
			}

		}

		if (possibleMoves[opKing.getPosition().getRow()][opKing.getPosition().getColumn()]) {
			this.check = true;
			return true;
		}
		this.check = false;
		return false;
	}

	public boolean testCheckMate() {
		if (!this.check) {
			return false;
		}
		boolean checkMate = false;
		ChessPiece[][] allPieces = this.getPieces();
		ChessPiece king = null;
		for (int i = 0; i < allPieces.length; i++) {
			for (int j = 0; j < allPieces.length; j++) {
				if (allPieces[i][j] instanceof King && allPieces[i][j].getColor() != this.currentPlayer) {
					king = allPieces[i][j];
				}
			}
		}

		Position sourceKingPosition = king.getChessPosition().toPosition();
		boolean[][] kingPossibleMoves = king.possibleMoves();
		for (int i = 0; i < kingPossibleMoves.length; i++) {
			for (int j = 0; j < kingPossibleMoves.length; j++) {
				Position targetToTest = new Position(i, j);
				makeMove(sourceKingPosition, targetToTest);
				if (isTheKingUnderCheck()) {
					checkMate = true;
				}
				undoMove(targetToTest, sourceKingPosition);
			}

		}
		this.checkMate = checkMate;
		return checkMate;

	}

}
