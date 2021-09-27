package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	// Representa��o da pe�a no tabuleiro pela letra K
	@Override
	public String toString() {
		return "K";
	}

}
