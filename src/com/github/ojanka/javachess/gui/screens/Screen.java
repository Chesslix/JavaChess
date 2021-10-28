package com.github.ojanka.javachess.gui.screens;

import processing.core.PApplet;

public abstract class Screen {
	public abstract void init();
	
	public abstract void draw();
	
	public abstract void dispatch();
}
