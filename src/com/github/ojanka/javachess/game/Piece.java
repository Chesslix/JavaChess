package com.github.ojanka.javachess.game;

import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

public abstract class Piece {
	private final Position id;
	protected boolean firstTurn;
	private Position currentPosition;
	private ChessColor color;
	
	public Piece(int startX, int startY, ChessColor color) {
		Position startPosition = new Position(startX, startY);
		this.id = startPosition;
		this.currentPosition = startPosition;
		this.color = color;
	}

	public void setFirstTurn() {
		this.firstTurn = false;
	}

	public abstract Position[] getValidPositions();
	
	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(int x, int y) {
		this.currentPosition = new Position(x, y);
	}
	
	public ChessColor getColor() {
		return color;
	}
	
	public Position getId() {
		return id;
	}

	public String getClassName(){
		return this.getClass().getSimpleName();
	}
}
