package com.github.chesslix.javachess.gui.screens;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.gui.GUIManager;
import com.github.chesslix.javachess.gui.widgets.Button;
import com.github.chesslix.javachess.gui.widgets.MainMenuButton;
import com.github.chesslix.javachess.gui.widgets.TextField;
import com.github.chesslix.javachess.networking.NetworkManager;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.GameSettings;

import processing.core.PApplet;
import processing.core.PConstants;

public class HostGameMenu extends Screen {
	
	private PApplet p = GUIManager.getInstance().getApplet();
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.addWidget("textFieldPortHostGame", new TextField(p.sketchWidth() / 2 - 100, 250, 200, 40, true, TextField.ALL_DIGITS));
		this.addWidget("returnMainMenuButton", new MainMenuButton("Back to Menu", p.sketchWidth() -120, 30, 100, 40));
		this.addWidget("startGame", new Button("Start Game", p.sketchWidth() / 2 -100 , 350, 200, 60) {
			
			@Override
			public void onClick() {
				GameSettings gameSettings = new GameSettings();
				gameSettings.ownColor = ChessColor.BLACK;
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							NetworkManager.getInstance().startServer(getPort(), gameSettings);
						} catch (Exception e) {
						}
					}
				}, "Networking").start();
			}
		});
		super.init();
	}
	
	private int getPort() {
		String portStr = ((TextField) getWidget("textFieldPortHostGame")).getText();
		int port = 0;
		try {
			port = Integer.parseInt(portStr);
		} catch (Exception e) {}
		return port;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		p.background(9, 77, 195);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.textSize(60);
		p.text("Host a Game!", p.sketchWidth() / 2, 100);
		p.textSize(30);
		p.text("Insert Port", p.sketchWidth() / 2, 200);
		p.textSize(15);
		super.draw();
	}
	
	@Override
	public void event(String name) {
		if (name.equals("mouseRelease")) {
			this.forEachWidget((widget) -> {
				if (widget instanceof TextField) {
					TextField textField = (TextField) widget;
					
					if (textField.isHovered()) {
						textField.setActive(true);
					} else textField.setActive(false);
				}
			});
		}
		super.event(name);
	}

}
