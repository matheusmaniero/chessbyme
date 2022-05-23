package boardgame;

import chess.ChessPiece;

public class Board {

	private int rows;
	private int columns;
	Piece[][] pieces;

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];

	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Piece piece(int row, int column) {
		if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
			return pieces[row][column];
		}
		throw new BoardException("This position exceeds the board.");
	}

	public Piece piece(Position position) {
		if (position.getRow() >= 0 && position.getRow() < this.rows && position.getColumn() >= 0
				&& position.getColumn() < this.columns) {
			return pieces[position.getRow()][position.getColumn()];
		}
		throw new BoardException("This position exceeds the board.");
	}

	public void placePiece(Piece piece, Position position) {
		if (position.getRow() >= 0 && position.getRow() < this.rows && position.getColumn() >= 0
				&& position.getColumn() < this.columns) {
			this.pieces[position.getRow()][position.getColumn()] = piece;
		} else {
			throw new BoardException("This position exceeds the board.");
		}

	}

	public Piece removePiece(Position position) {
		Piece removedPiece;
		if (!(positionExists(position) && thereIsAPiece(position))) {
			throw new BoardException("Is not possible to remove this piece.");
		} else {
			removedPiece = this.pieces[position.getRow()][position.getColumn()];
			this.pieces[position.getRow()][position.getColumn()] = null;
		}

		return removedPiece;

	}

	public boolean positionExists(Position position) {
		if (position.getRow() >= 0 && position.getRow() < this.rows && position.getColumn() >= 0
				&& position.getColumn() < this.columns) {
			return true;
		} else {
			throw new BoardException("This position doesn't exists on the board.");
			
		}		
		

	}

	public boolean thereIsAPiece(Position position) {
		if (this.positionExists(position) && this.piece(position) instanceof ChessPiece) {
			return true;
		}else {
			throw new BoardException("There is no piece on this position.");
		}
		
	}
}
