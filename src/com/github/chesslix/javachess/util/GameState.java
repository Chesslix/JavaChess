package com.github.chesslix.javachess.util;

public enum GameState {
	INGAME(0),
	GAME_WON(1),
	GAME_LOST(2),
	REMOTE_LEFT(3);

	private int state;
	
	GameState(int i) {
		this.state = i;
	}
}
