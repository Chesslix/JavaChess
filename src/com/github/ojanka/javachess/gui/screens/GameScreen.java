package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.util.Position;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends Screen {
	private PApplet p = GUIManager.getInstance().getApplet();
	private PImage img;
	private boolean isClicked = false;;
	public Position selectedField;

	@Override
	public void init() {
		img = p.loadImage("com/github/ojanka/javachess/gui/util/statics/chessbackground.jpg");
		super.init();
	}

	@Override
	public void draw() {
		p.image(img, 0,0, p.width, p.height);
		super.draw();
	}
	
	@Override
	public void event(String name) {
		if (name.equals("mouseRelease")) {
			
		}
		super.event(name);
	}
	private int returnField(int pixelposition) {
		int counter = 0;
		while(pixelposition - 64 >= 0) {
			pixelposition = pixelposition - 64;
			counter ++;
		}
		
		return counter;
	}
	
}
