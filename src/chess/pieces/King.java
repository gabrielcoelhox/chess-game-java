package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	// Representação da peça no tabuleiro pela letra K
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position); // Saber se o rei pode se mover. Pega a peça P
		return p == null || p.getColor() != getColor(); // Verifica se a peça P não é nula e se é uma peça adversária

	}

	private boolean testRookCastling(Position position) { // Testar se a torre está apta para Rook
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // Tamanho do tabuleiro

		Position p = new Position(0, 0);

		// Acima
		p.setValues(position.getRow() - 1, position.getColumn()); // Definir os valores para a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) { // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); // Definir os valores para a posição abaixo do rei
		if (getBoard().positionExists(p) && canMove(p)) { // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Esquerda
		p.setValues(position.getRow(), position.getColumn() - 1); // Definir os valores para a posição a esquerda do rei
		if (getBoard().positionExists(p) && canMove(p)) { // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Esquerda
		p.setValues(position.getRow(), position.getColumn() + 1); // Definir os valores para a posição a direita do rei
		if (getBoard().positionExists(p) && canMove(p)) { // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Diagonal NW
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Diagonal NE
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Diagonal SW
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Diagonal SE
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Movimento de ROOK	
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// Rook lado do rei
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// Rook lado da rainha
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}
}
