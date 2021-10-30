package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.gui.widgets.Button;
import com.github.ojanka.javachess.gui.widgets.MainMenuButton;
import com.github.ojanka.javachess.gui.widgets.TextField;

import processing.core.PApplet;

public class HostGameMenu extends Screen {
	
	private PApplet p = GUIManager.getInstance().getApplet();
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.addWidget("textFieldPortHostGame", new TextField(p.sketchWidth() / 2 - 100, 250, 200, 40, TextField.ALL_DIGITS));
		this.addWidget("returnMainMenuButton", new MainMenuButton("Back to Menu", p.sketchWidth() -120, 30, 100, 40));
		this.addWidget("startGame", new Button("Start Game", p.sketchWidth() / 2 -50 , 350, 100, 40) {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				GUIManager.getInstance().changeScreen(new GameScreen());
			}
		});
		super.init();
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		p.background(9, 77, 195);
		p.text("Host a Game!", p.sketchWidth() / 2, 100);
		p.text("Insert Port", p.sketchWidth() / 2, 200);
		super.draw();
	}
	

}
