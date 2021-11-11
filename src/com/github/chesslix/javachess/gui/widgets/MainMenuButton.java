package com.github.chesslix.javachess.gui.widgets;

import com.github.chesslix.javachess.gui.GUIManager;
import com.github.chesslix.javachess.gui.screens.MainMenu;

/**
 * This class builds the Button which returns you to the MainMenu Screen
 *
 */
public class MainMenuButton extends Button {

	public MainMenuButton(String label, int x, int y, int width, int height) {
		super(label, x, y, width, height);
		
	}
	
	/**
	 * When clicking the Button it changes the Screen to MainMenu Screen
	 */
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		GUIManager.getInstance().changeScreen(new MainMenu());
	}

}
