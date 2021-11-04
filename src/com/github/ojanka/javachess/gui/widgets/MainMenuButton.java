package com.github.ojanka.javachess.gui.widgets;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.gui.screens.MainMenu;

public class MainMenuButton extends Button {

	public MainMenuButton(String label, int x, int y, int width, int height) {
		super(label, x, y, width, height);
		
	}
	
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		GUIManager.getInstance().changeScreen(new MainMenu());
	}

}
