package com.github.ojanka.javachess.gui;

import com.github.ojanka.javachess.gui.screens.MainMenu;
import com.github.ojanka.javachess.gui.screens.Screen;

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
					size(950, 900);
				};
				
				@Override
				public void setup() {
					surface.setTitle("JavaChess");
					surface.setResizable(false);
				}
				
				public void draw() {
					if (getScreen() == null) {
						changeScreen(new MainMenu());
					}
					getScreen().draw();
				};
			};
			PApplet.runSketch(new String[] {"JavaChess"}, applet);
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void changeScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.dispatch();
		}
		screen.init();
		this.screen = screen;
	}
	
	public Screen getScreen() {
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
}
