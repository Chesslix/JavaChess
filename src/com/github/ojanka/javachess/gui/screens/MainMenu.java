package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.gui.widgets.Button;

import processing.core.PApplet;

public class MainMenu extends Screen {

	private PApplet p = GUIManager.getInstance().getApplet();

	@Override
	public void init() {		
		this.addWidget("hostGameButton", new Button("Host Game", p.sketchWidth() / 2 - 90, 150, 180, 50) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				System.out.println("clicked");
				GUIManager.getInstance().changeScreen(new HostGameMenu());

			}
		});

		this.addWidget("joinGameButton", new Button("Join Game", p.sketchWidth() / 2 - 90, 250, 180, 50) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				System.out.println("clicked");
				GUIManager.getInstance().changeScreen(new JoinGameMenu());

			}
		});
		
		this.addWidget("exitGame", new Button("Exit Game", p.sketchWidth() / 2 - 90, 350, 180, 50) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				Game.getInstance().shutDown();
			}
		});

		super.init();
	}

	@Override
	public void draw() {
		p.background(9, 77, 195);
		p.text("Welcome to JavaChess", p.sketchWidth() / 2, 80);
		super.draw();
	}

	@Override
	public void dispatch() {
		// TODO Auto-generated method stub

	}

}
