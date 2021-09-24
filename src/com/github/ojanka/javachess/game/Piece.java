package com.github.ojanka.javachess.game;

import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

public abstract class Piece {
	private final Position id;
	private Position currentPosition;
	private ChessColor color;
	
	public Piece(int startX, int startY, ChessColor color) {
		Position startPosition = new Position(startX, startY);
		this.id = startPosition;
		this.currentPosition = startPosition;
		this.color = color;
	}
	
	public abstract Position[] getValidPositions();
	
	public abstract boolean isPositionValid(Position arg0);
	
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
		return this.getClass().getSimpleName().substring(0,2)+this.getColor().toString().charAt(0);
	}
}
