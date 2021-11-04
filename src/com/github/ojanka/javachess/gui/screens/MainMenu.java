package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.gui.widgets.Button;

import processing.core.PApplet;

public class MainMenu extends Screen {

	private PApplet p = GUIManager.getInstance().getApplet();
	private Button hostGame;
	
	@Override
	public void init() {
		hostGame = new Button("Host Game", p.sketchWidth() / 2 - 50, 300, 100, 50) {
			
			@Override
			public void onClick() {
				
			}
		};
		
		super.init();
	}

	@Override
	public void draw() {
		p.background(255, 255, 255);
		hostGame.draw();
	}

	@Override
	public void dispatch() {
		// TODO Auto-generated method stub
		
	}

}
