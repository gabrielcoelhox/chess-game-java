package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	// Representação da peça no tabuleiro pela letra K
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position); // Saber se o rei pode se mover. Pega a peça P
		return p == null || p.getColor() != getColor();        // Verifica se a peça P não é nula e se é uma peça adversária

	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // Tamanho do tabuleiro

		Position p = new Position(0, 0);

		// Acima
		p.setValues(position.getRow() - 1, position.getColumn()); // Definir os valores para a posição acima do rei
		if (getBoard().positionExists(p) && canMove(p)) {         // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); // Definir os valores para a posição abaixo do rei
		if (getBoard().positionExists(p) && canMove(p)) {         // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Esquerda
		p.setValues(position.getRow(), position.getColumn() - 1); // Definir os valores para a posição a esquerda do rei
		if (getBoard().positionExists(p) && canMove(p)) {         // Testar se a posição existe e se pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Esquerda
		p.setValues(position.getRow(), position.getColumn() + 1); // Definir os valores para a posição a direita do rei
		if (getBoard().positionExists(p) && canMove(p)) {         // Testar se a posição existe e se pode mover
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
		
		return mat;
	}
}
