package com.github.ojanka.javachess.gui.callbacks;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorPositionCallback extends GLFWCursorPosCallback {
	private double x;
	private double y;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		this.x = arg1;
		this.y = arg2;
	}
}
