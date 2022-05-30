package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];

		Position pos = new Position(0, 0);
		// north
		pos.setValues(this.position.getRow() - 1, this.position.getColumn());
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// south
		pos.setValues(this.position.getRow() + 1, this.position.getColumn());
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// left
		pos.setValues(this.position.getRow(), this.position.getColumn() - 1);
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// right
		pos.setValues(this.position.getRow(), this.position.getColumn() + 1);
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// north-west
		pos.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// north-east
		pos.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// south-west
		pos.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		// south-east
		pos.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
		if (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7
				&& !this.getBoard().thereIsAPiece(pos)) {

			mat[pos.getRow()][pos.getColumn()] = true;
		}

		return mat;

	}

}
