package com.github.chesslix.javachess.gui.widgets;

public abstract class Widget {
	
	public abstract void init();
	
	public abstract void draw();
	
	public abstract void dispatch();
	
	public void event(String name) {}
}
