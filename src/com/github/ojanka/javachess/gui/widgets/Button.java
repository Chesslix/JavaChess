package com.github.ojanka.javachess.gui.widgets;

import com.github.ojanka.javachess.gui.GUIManager;

import processing.core.PApplet;
import processing.core.PConstants;

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
	
	
	@Override
	public void draw() {
		if (this.isHovered()) {
			p.fill(p.color(0, 0, 0));
			p.stroke(p.color(255, 255, 255));
			p.rect(x, y, width, height);
			
			p.noStroke();
			p.fill(p.color(255, 255, 255));
			p.textAlign(PConstants.CENTER, PConstants.CENTER);
			p.text(this.label, this.x + this.width / 2, this.y + this.height / 2);
		} else {
			p.fill(p.color(0, 0, 0));
			p.noStroke();
			p.rect(x, y, width, height);
			
			p.fill(p.color(255, 255, 255));
			p.textAlign(PConstants.CENTER, PConstants.CENTER);
			p.text(this.label, this.x + this.width / 2, this.y + this.height / 2);
		}
	}
	
	public abstract void onClick();
	
	@Override
	public void event(String name) {
		if (name.equals("mouseClicked")) {
			if (this.isHovered()) {
				this.onClick();
			}
		}
	}
	
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
