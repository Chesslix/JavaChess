package com.github.ojanka.javachess.game;

public class Board {
	/**
	 * Array with all Pieces. Killed pieces are replaced with null
	 */
	private Piece[] pieces;
	
	public Board(Piece[] pieces) {
		this.pieces = pieces;
	}

	/**
	 * Returns the piece at the given position.
	 * @param x
	 * @param y
	 * @return the Piece or null if there is no piece at this position
	 */
	public Piece getPiece(int x, int y) {
		for (Piece piece : pieces) {
			if (piece == null) continue;
			if (piece.getCurrentPosition().equals(x, y)) return piece;
		}
		return null;
	}
	
	/**
	 * Removes the piece from the board
	 * @param toKill
	 */
	public void killPiece(Piece toKill) {
		for (int i = 0; i < pieces.length; i++) {
			if (pieces[i] == toKill) pieces[i] = null;
		}
	}
	
	/**
	 * Moves the piece to the given position and kills every other piece on the same position, no matter in which team it is (to prevent bugs). Position validation has to be done before that method.
	 * @param toMove
	 * @param x
	 * @param y
	 */
	public void movePiece(Piece toMove, int x, int y) {
		for (int i = 0; i < pieces.length; i++) {
			if (pieces[i] == null) continue;
			if (pieces[i].getCurrentPosition().equals(x, y)) {
				killPiece(pieces[i]);
			}
		}
		toMove.setCurrentPosition(x, y);
	}
	
	public Piece[] getPieces() {
		return pieces;
	}
}
