package com.github.ojanka.javachess.util;

public enum GameState {
	INGAME(0),
	GAME_WON(1),
	GAME_LOST(2);

	private int state;
	
	GameState(int i) {
		this.state = i;
	}
}
