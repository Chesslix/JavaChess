package com.github.chesslix.javachess.gui.screens;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.gui.GUIManager;
import com.github.chesslix.javachess.gui.widgets.Button;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class MainMenu extends Screen {

	private PApplet p = GUIManager.getInstance().getApplet();
	private final PImage background_img = p.loadImage("./assets/textures/chess_board.png");

	@Override
	public void init() {		
		this.addWidget("hostGameButton", new Button("Host Game", p.sketchWidth() / 2 - 100, 300, 200, 50) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				GUIManager.getInstance().changeScreen(new HostGameMenu());

			}
		});

		this.addWidget("joinGameButton", new Button("Join Game", p.sketchWidth() / 2 - 100, 400, 200, 50) {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				GUIManager.getInstance().changeScreen(new JoinGameMenu());

			}
		});
		
		this.addWidget("exitGame", new Button("Exit Game", p.sketchWidth() / 2 - 100, 500, 200, 50) {

			@Override
			public void onClick() {
				GUIManager.getInstance().exit();
			}
		});

		super.init();
	}

	@Override
	public void draw() {
		//p.image(background_img, 0, 0, p.width, p.height);
		//p.background(0, 100);
		p.background(115, 70, 54);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.textSize(60);
		p.text("Welcome to JavaChess", p.sketchWidth() / 2, 200);
		p.textSize(15);
		super.draw();
	}

	@Override
	public void dispatch() {
		// TODO Auto-generated method stub

	}

}
