package com.github.ojanka.javachess.gui.widgets;

import com.github.ojanka.javachess.gui.GUIManager;

import processing.core.PApplet;
import processing.core.PConstants;

public class TextField extends Widget {
	public static char[] ALL_DIGITS = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	
	public static char[] ALL_LETTERS = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
	};
	
	private PApplet p = GUIManager.getInstance().getApplet();
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private char[] validChars;
	
	private StringBuilder sb;
	
	public TextField(int x, int y, int width, int height, char... validChars) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.validChars = validChars;
	}
	
	@Override
	public void init() {
		sb = new StringBuilder();
	}

	@Override
	public void draw() {
		p.fill(p.color(0, 0, 0));
		p.noStroke();
		p.rect(x, y, width, height);
		
		p.fill(p.color(255, 255, 255));
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.text(this.getText(), this.x + 5, this.y + this.height / 2);
	}
	
	@Override
	public void dispatch() {
		
	}
	
	@Override
	public void event(String name) {
		if(name.equals("keyPressed")) {
			if (p.keyCode == PConstants.BACKSPACE) {
				if (sb.length() > 0) {
					sb.setLength(sb.length() - 1);
				}
			} else if (isValidChar(p.key)) {
				sb.append(p.key);
			}
		}
	}
	
	public String getText() {
		return sb.toString();
	}
	
	private boolean isValidChar(char c) {
		for (char e : validChars) {
			if (c == e) return true;
		}
		return false;
	}
}
