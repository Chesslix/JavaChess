package com.github.ojanka.javachess.game;

public class Board {
	private Piece[] pieces;
	
	public Board(Piece[] pieces) {
		this.pieces = pieces;
	}

	public Piece getPiece(int x, int y) {
		return null;
	}
	
	public void killPiece(Piece piece) {
		
	}
	
	public Piece[] getPieces() {
		return pieces;
	}
}
