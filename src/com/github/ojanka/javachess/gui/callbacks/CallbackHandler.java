package com.github.ojanka.javachess.gui.callbacks;

public class CallbackHandler {
	public final CursorPositionCallback cursorPosition;
	public final MouseButtonCallback mouseButton;
	
	public CallbackHandler() {
		this.cursorPosition = new CursorPositionCallback();
		this.mouseButton = new MouseButtonCallback();
	}
}
