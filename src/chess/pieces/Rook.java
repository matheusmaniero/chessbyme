package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];

		Position pos = new Position(0, 0);

		// north
		pos.setValues(this.position.getRow() - 1, this.position.getColumn());

		while (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7 && !this.getBoard().thereIsAPiece(pos)) {

			
				mat[pos.getRow()][pos.getColumn()] = true;
				pos.setValues(pos.getRow() - 1, pos.getColumn());
		}
		
		
		
				// south

			pos.setValues(this.position.getRow() + 1, this.position.getColumn());

			while (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7 && !this.getBoard().thereIsAPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
				pos.setValues(pos.getRow() + 1, pos.getColumn());
			}

			// left
			pos.setValues(this.position.getRow(), this.position.getColumn() - 1);

			while (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7 && !this.getBoard().thereIsAPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
				pos.setValues(pos.getRow(), pos.getColumn() - 1);
			}

			// right
			pos.setValues(this.position.getRow(), this.position.getColumn() + 1);

			while (pos.getColumn() >= 0 && pos.getColumn() <= 7 && pos.getRow() >= 0 && pos.getRow() <= 7 && !this.getBoard().thereIsAPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
				pos.setValues(pos.getRow(), pos.getColumn() + 1);
			}

		

		return mat;

	}

}
