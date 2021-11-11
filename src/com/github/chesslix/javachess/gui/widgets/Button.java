package com.github.chesslix.javachess.gui.widgets;

import com.github.chesslix.javachess.gui.GUIManager;

import processing.core.PApplet;
import processing.core.PConstants;


/**
 * This Class is to display a button on the Screen. A action could be append to it by overwriting the event method.
 *
 */
public abstract class Button extends Widget {
	private String label;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private PApplet p = GUIManager.getInstance().getApplet();
	
	public Button(String label, int x, int y, int width, int height) {
		this.label = label;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * In this draw mehtod the button gets drawed. It gets it styles and sizes by adding all the properties.
	 * This button has two states. One when its hovered, the other when not hovering.
	 */
	@Override
	public void draw() {
		if (this.isHovered()) {
			p.fill(p.color(131, 151, 95));
			p.stroke(p.color(245, 236, 203));
			p.rect(x, y, width, height);
			
			p.noStroke();
			p.fill(p.color(245, 236, 203));
			p.textAlign(PConstants.CENTER, PConstants.CENTER);
			p.text(this.label, this.x + this.width / 2, this.y + this.height / 2);
		} else {
			p.fill(p.color(0, 0, 0));
			p.noStroke();
			p.rect(x, y, width, height);
			
			p.fill(p.color(245, 236, 203));
			p.textAlign(PConstants.CENTER, PConstants.CENTER);
			p.text(this.label, this.x + this.width / 2, this.y + this.height / 2);
		}
	}
	/**
	 * This method must be overwritten with the functionallity by clicking on the button.
	 */
	public abstract void onClick();
	
	/**
	 * This method calls the onClick Method when mouseRelease event gets submitted.
	 */
	@Override
	public void event(String name) {
		if (name.equals("mouseRelease")) {
			if (this.isHovered()) {
				this.onClick();
			}
		}
	}
	/**
	 * This function looks if the cursor is on the button
	 * @return boolean -> isHovered or not
	 */
	public boolean isHovered() {
		if (p.mouseX > this.x && p.mouseX < this.x+this.width &&
			p.mouseY > this.y && p.mouseY < this.y+this.height) {
			return true;
		} else return false;
	}
	
	
	
	@Override
	public void dispatch() {
		// TODO Auto-generated method stub
		
	}
}
