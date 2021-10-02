package com.github.ojanka.javachess.gui.screens;

import static org.lwjgl.opengl.GL11.*;

public class MainMenuScreen extends Screen {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		glBegin(GL_QUADS);
		
		glColor3f(1.0f, 0.0f, 0.0f);
		glVertex2f(-0.5f, -0.5f);
		glVertex2f(0.5f, -0.5f);
		glVertex2f(0.5f, 0.5f);
		glVertex2f(-0.5f, 0.5f);
		
		glEnd();
	}

	@Override
	public void dispatch() {
		// TODO Auto-generated method stub
		
	}

}
