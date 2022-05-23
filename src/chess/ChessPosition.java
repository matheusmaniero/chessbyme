package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {		
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	protected Position toPosition() {
		int matrixColumn = (int) (this.column - 'a');
		int matrixRow = 8 - this.row;
		return new Position (matrixRow,matrixColumn);
	}
	
	protected ChessPosition fromPosition(Position position) {
		int matrixRow = position.getRow();
		int matrixColumn = position.getColumn();
		char chessColumn = (char) ((char) matrixColumn + 'a');
		int chessRow = 8 - matrixRow;
		return new ChessPosition(chessColumn,chessRow);
		
	}
	
	
	

}
