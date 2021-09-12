package com.github.ojanka.javachess.game;

import com.github.ojanka.javachess.util.ChessColor;

public class Game {
	private static Game instance;
	
	private ChessColor team;
	private Board board;
	
	public static Game getInstance() {
		return instance;
	}
	
	public ChessColor getTeam() {
		return team;
	}
	
	public void setTeam(ChessColor team) {
		this.team = team;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setupDefaultBoard() {
		
	}
	
	public static Game createGame() {
		/* TODO */ return null;
	}
}
