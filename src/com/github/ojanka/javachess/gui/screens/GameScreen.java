package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.util.Position;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends Screen {
	private PApplet p = GUIManager.getInstance().getApplet();
	private PImage img;
	public Position selectedField;

	@Override
	public void init() {
		img = p.loadImage("com/github/ojanka/javachess/gui/util/statics/chessbackground.jpg"); 	
	}

	@Override
	public void draw() {
		if(p.mousePressed) {
			//System.out.println(returnField(p.mouseX) + " "+ returnField(p.mouseY));
			selectedField = new Position(returnField(p.mouseX), returnField(p.mouseY));
			System.out.println(selectedField.getX() + " "+ selectedField.getY());
		}
		
		if(p.keyPressed) {
			if(p.keyCode == 27) {
				p.key = 0;
				System.out.println("test");
			}
			
		}
		
		p.image(img, 0,0, 500, 500);
		
	}

	@Override
	public void dispatch() {
		// TODO Auto-generated method stub
		
	}
	
	private int returnField(int pixelposition) {
		int currentpixel = pixelposition;
		int counter = 0;
		while(currentpixel - 62 > 0) {
			currentpixel = currentpixel - 62;
			counter ++;
		}
		
		return counter;
	}
	
}
