package com.github.chesslix.javachess.game;

import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.Position;

/**
 * This is the super class for all the pieces. It contains abstract classes
 * providing some polymorphisms methods.
 *
 * @version 1.0
 * @author David Abderhalden / Nino Arisona / Oliver Janka / Joris Haenseler
 */
public abstract class Piece {
	private final Position id;
	protected boolean firstTurn;
	private Position currentPosition;
	private ChessColor color;

	/**
	 * constructor
	 * @param startX initial X Axe
	 * @param startY initial Y Axe
	 * @param color the team color (enumeration)
	 */
	public Piece(int startX, int startY, ChessColor color) {
		Position startPosition = new Position(startX, startY);
		this.id = startPosition;
		this.currentPosition = startPosition;
		this.color = color;
	}

	/**
	 * sets the first turn to false
	 */
	public void setFirstTurn() {
		this.firstTurn = false;
	}

	public boolean getFirstTurn() {
		return this.firstTurn;
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
