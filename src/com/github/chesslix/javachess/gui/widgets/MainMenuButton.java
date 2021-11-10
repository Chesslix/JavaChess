package com.github.chesslix.javachess.gui.widgets;

import com.github.chesslix.javachess.gui.GUIManager;
import com.github.chesslix.javachess.gui.screens.MainMenu;

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
