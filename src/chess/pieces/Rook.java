package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	// Representa��o da pe�a no tabuleiro pela letra R
	@Override
	public String toString() {
		return "R";
	}
}