package com.github.chesslix.javachess.gui.screens;

import com.github.chesslix.javachess.gui.GUIManager;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Screen which shows a message to a player and provides a runnable which gets called on the custom solve event
 * @author Oliver
 *
 */
public class StatusScreen extends Screen {
	
	private static final PApplet p = GUIManager.getInstance().getApplet();
	
	private String statusMessage;
	private final Runnable onSolve;
	
	public StatusScreen(String statusMessage) {
		this(statusMessage, null);
	}
	
	public StatusScreen(String statusMessage, Runnable onSolve) {
		this.statusMessage = statusMessage;
		this.onSolve = onSolve;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	@Override
	public void draw() {
		p.background(115, 70, 54);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.text(this.statusMessage, p.sketchWidth() / 2, 100);
		super.draw();
	}
	
	@Override
	public void event(String name) {
		if (name.equals("solve")) {
			onSolve.run();
		}
	}

}
