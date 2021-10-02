package com.github.ojanka.javachess.gui.screens;

public abstract class Screen {

	/**
	 * Is called every time the screen is set in the UIManager
	 */
	public abstract void init();
	
	public abstract void draw();
	
	public abstract void dispatch();
}
