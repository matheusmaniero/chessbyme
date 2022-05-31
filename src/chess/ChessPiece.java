package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super( board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece piece = (ChessPiece)this.getBoard().piece(position);
		if (piece != null && piece.getColor() != this.getColor()) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	

	

}
