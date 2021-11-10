package com.github.chesslix.javachess.gui;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.gui.screens.EndGameScreen;
import com.github.chesslix.javachess.gui.screens.GameScreen;
import com.github.chesslix.javachess.gui.screens.MainMenu;
import com.github.chesslix.javachess.gui.screens.Screen;
import com.github.chesslix.javachess.util.ChessColor;

import processing.core.PApplet;

public class GUIManager {
	private static GUIManager instance;
	private boolean running = false;
	private PApplet applet;
	private Screen screen;
	
	public void startGUI() {
		if (this.isRunning()) {
			throw new IllegalStateException("Cannot start GUI twice");
		} else {
			this.running = true;
			this.applet = new PApplet() {
				public void settings() {
					noSmooth();
					size(800, 800);
				}
				
				@Override
				public void setup() {
					surface.setTitle("JavaChess");
					surface.setResizable(false);
					textSize(15);
				}
				
				public void draw() {
					if (getScreen() != null) {
						getScreen().draw();
					}
				}
				
				@Override
				public void mouseClicked() {
					if (getScreen() != null) getScreen().event("mouseClicked");
				}
				
				@Override
				public void mouseReleased() {
					if (getScreen() != null) getScreen().event("mouseRelease");
				}
				
				@Override
				public void keyPressed() {
					if (getScreen() != null) getScreen().event("keyPressed");
				}
				
				@Override
				public void exit() {
					System.out.println("exiting...");
					running = false;
					changeScreen(null);
					Game.getInstance().shutdown();
					super.exit();
				}
			};
			PApplet.runSketch(new String[] {"JavaChess"}, applet);
			//DEBUG
			Game.getInstance().setupDefaultBoard();
			Game.getInstance().setTeam(ChessColor.BLACK);

			Game.getInstance().startRound();
			this.changeScreen(new MainMenu());
		}
	}
	
	public void exit() {
		this.applet.exit();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public synchronized void changeScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.dispatch();
		}
		if (screen != null) {
			screen.init();
		}
		this.screen = screen;
	}
	
	public synchronized Screen getScreen() {
		return screen;
	}
	
	public static GUIManager getInstance() {
		if (instance == null) {
			instance = new GUIManager();
		}
		return instance;
	}

	public PApplet getApplet() {
		return this.applet;
	}

	public void event(String string) {
		if (getScreen() != null) getScreen().event(string);
	}
}
