package com.github.ojanka.javachess.gui.callbacks;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButtonCallback extends GLFWMouseButtonCallback {

	private int button;
	private int action;
	private int mods;
	
	public int getButton() {
		return button;
	}
	
	public int getAction() {
		return action;
	}
	
	public int getMods() {
		return mods;
	}
	
	@Override
	public void invoke(long arg0, int arg1, int arg2, int arg3) {
		this.button = arg1;
		this.action = arg2;
		this.mods = arg3;
	}
	
}
