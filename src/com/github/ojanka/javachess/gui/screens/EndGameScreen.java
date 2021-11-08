package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.gui.widgets.MainMenuButton;
import processing.core.PApplet;
import processing.core.PConstants;

public class EndGameScreen extends Screen{

    private PApplet p = GUIManager.getInstance().getApplet();

    private String textToDisplay;

    public EndGameScreen(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    @Override
    public void init() {
        this.addWidget("returnToMainMenu", new MainMenuButton("Back to Menu", p.sketchWidth() / 2 - 100, 420, 200, 60));
        super.init();
    }

    @Override
    public void draw() {
        p.background(9, 77, 195);
        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.textSize(50);
        p.text(textToDisplay, p.width/ 2, 300);
        p.textSize(15);

        super.draw();
    }

    @Override
    public void event(String name) {
        super.event(name);
    }
}
