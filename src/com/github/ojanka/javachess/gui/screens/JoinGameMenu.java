package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.gui.widgets.MainMenuButton;

import processing.core.PApplet;

public class JoinGameMenu extends Screen {
	private PApplet p = GUIManager.getInstance().getApplet();

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.addWidget("returnMainMenuButton", new MainMenuButton("Back to Menu", p.sketchWidth() - 120, 30, 100, 40));
		super.init();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		p.background(9, 77, 195);
		p.text("Join a Game!", p.sketchWidth() / 2, 100);
		p.text("Insert IP Adress", p.sketchWidth() / 2, 200);
		p.text("Insert Port", p.sketchWidth() / 2, 300);
		super.draw();
	}

}
