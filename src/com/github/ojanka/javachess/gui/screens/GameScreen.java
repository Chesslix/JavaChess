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
		if(p.mousePressed) {
			if(isClicked == false) {
				//action when field gets clicked
				selectedField = new Position(returnField(p.mouseX), returnField(p.mouseY));
				System.out.println(selectedField.getX() + " "+ selectedField.getY());
				isClicked = true;
			}
		} else isClicked = false;
		
		if(p.keyPressed) {
			if(p.key == 27) {
				p.key = 0;
				System.out.println("test");
			}
		}
		
		p.image(img, 0,0, 512, 512);
		
		super.draw();
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
