package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	// Representação da peça no tabuleiro pela letra R
	@Override
	public String toString() {
		return "R";
	}
}
