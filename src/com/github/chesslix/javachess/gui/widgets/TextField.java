package com.github.chesslix.javachess.gui.widgets;

import com.github.chesslix.javachess.gui.GUIManager;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * The TextField class is used to display a Inputfield to the user. You have to pass a char array with all chars wich are valid to enter into the textfield.
 */

public class TextField extends Widget {
	public static char[] ALL_DIGITS = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	
	public static char[] ALL_LETTERS = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
	};
	
	private PApplet p = GUIManager.getInstance().getApplet();
	
	private boolean active;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private char[] validChars;
	
	private StringBuilder sb;
	
	public TextField(int x, int y, int width, int height, boolean active, char... validChars) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.validChars = validChars;
		this.active = active;
	}
	
	@Override
	public void init() {
		sb = new StringBuilder();
	}

	/**
	 * In the draw method the Textfield gets builded and drawn down to the GUI
	 */
	@Override
	public void draw() {
		p.fill(p.color(0, 0, 0));
		
		if (this.active) {
			p.stroke(p.color(245, 236, 203));
		} else p.noStroke();
		
		p.rect(x, y, width, height);
		
		p.fill(p.color(245, 236, 203));
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.text(this.getText(), this.x + 5, this.y + this.height / 2);
	}
	
	@Override
	public void dispatch() {
		
	}
	/**
	 * This function looks if the cursor is on the Inputfield
	 * @return boolean -> isHovered or not
	 */
	public boolean isHovered() {
		if (p.mouseX > this.x && p.mouseX < this.x+this.width &&
			p.mouseY > this.y && p.mouseY < this.y+this.height) {
			return true;
		} else return false;
	}

	/**
	 * This method handles the Keyboardinputs and updates the result string.
	 */
	@Override
	public void event(String name) {
		if (this.isActive()) {
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
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
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
