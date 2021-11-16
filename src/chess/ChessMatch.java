package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;                // Turno no in�cio da partida
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0 ; i < board.getRows(); i++) {
			for (int j=0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);            // Respos�vel por validar a posi��o de origem
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if (testCheck(currentPlayer)) {               // Testar se o pr�pio jogador se colocou em posi��o de check
			undoMove(source, target, capturedPiece);  // Impedir e desfazer a jogada
			throw new ChessException("You can't put yourself in check");
		}

		check = (testCheck(opponent(currentPlayer))) ? true : false;    // Testar se o oponente se colocou em check
		
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);       // remove pe�a da posi��o de origem
		Piece capturedPiece = board.removePiece(target);    // remove pe�a da posi��o de destino
		board.placePiece(p, target);
		
		if (capturedPiece != null) {                        // Se for diferente de nulo, uma pe�a foi capturada
			piecesOnTheBoard.remove(capturedPiece);         // Remove a pe�a da lista de pe�as no tabuleiro
			capturedPieces.add(capturedPiece);              // Add a pe�a a lista de pe�as capturadas
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {  // Desfazer movimento
		Piece p = board.removePiece(target);
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {  // Caso escolha pe�a do advers�rio
			throw new ChessException("There chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {     // Se n�o existir movimentos poss�veis
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void nextTurn() {         // troca de turnos
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {           // Localizar o rei de determinada cor	
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {                 // Se a pe�a for uma instancia de um Rei
				return (ChessPiece)p;                // Retorna a pe�a p
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

	// Varrer todas as pe�as advers�rias para testar se algum movimento cai na casa do Rei, com isso ocasionar o check
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition(); // Pegar a posi��o do rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		// ^^ Lista das pe�as do oponente de determinada cor ^^
		
		// Testar se existe alguma pe�a do oponente que leva a pe�a do Rei
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();      // Se a posi��o da matriz for true, significa que o rei est� em check
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if (mat[i][j]) {                // Se for um movimento poss�vel na matriz
						// Testar se o movimento poss�vel retira do check
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);   // Testar se o rei de determinada cor ainda est� em check
						undoMove(source, target, capturedPiece); // Desfazer o movimento de teste
						if (!testCheck) {  // Se n�o estava em check, significa que o movimento retirou o rei de check
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); // Add nova pe�a
		piecesOnTheBoard.add(piece);                                          // Add a pe�a na lista de pe�as do tabuleiro
	}
	
	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}
}
