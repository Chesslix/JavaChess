package com.github.chesslix.javachess.gui.screens;

import java.awt.Color;

import com.github.chesslix.javachess.gui.GUIManager;
import com.github.chesslix.javachess.gui.widgets.Button;
import com.github.chesslix.javachess.gui.widgets.MainMenuButton;
import com.github.chesslix.javachess.gui.widgets.TextField;
import com.github.chesslix.javachess.networking.NetworkManager;
import com.github.chesslix.javachess.util.ArrayUtils;

import processing.core.PApplet;
import processing.core.PConstants;

public class JoinGameMenu extends Screen {
	private PApplet p = GUIManager.getInstance().getApplet();

	private String error = "";
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.addWidget("returnMainMenuButton", new MainMenuButton("Back to Menu", p.sketchWidth() - 120, 30, 100, 40));
		this.addWidget("address", new TextField(p.sketchWidth() / 2 - 100, 230, 200, 40, true, ArrayUtils.concatenateCharArrays(TextField.ALL_DIGITS, new char[] {'.'})));
		this.addWidget("port", new TextField(p.sketchWidth() / 2 - 100, 360, 200, 40, false, TextField.ALL_DIGITS));
		this.addWidget("join", new Button("Join Game", p.sketchWidth() / 2 -100 , 480, 200, 60) {
			
			@Override
			public void onClick() {
				String ip = ((TextField) getWidget("address")).getText();
				String portStr = ((TextField) getWidget("port")).getText();
				int port;
				if (!validateIp(ip)) {
					error = "Invalid IP-Adress";
					return;
				}
				try {
					port = Integer.parseInt(portStr);
				} catch (Exception e) {
					error = "Invalid port";
					return;
				}
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							NetworkManager.getInstance().connect(ip, port);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, "Networking").start();
			}
		});
		super.init();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		p.background(9, 77, 195);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.textSize(60);
		p.text("Join a Game!", p.sketchWidth() / 2, 100);
		p.textSize(30);
		p.text("Insert IP Adress", p.sketchWidth() / 2, 200);
		p.text("Insert Port", p.sketchWidth() / 2, 330);
		p.textSize(15);
		
		p.fill(Color.RED.getRGB());
		p.text(error, p.sketchWidth() / 2, 380);
		super.draw();
	}
	
	@Override
	public void event(String name) {
		if (name.equals("mouseRelease")) {
			this.forEachWidget((widget) -> {
				if (widget instanceof TextField) {
					TextField textField = (TextField) widget;
					
					if (textField.isHovered()) {
						textField.setActive(true);
					} else textField.setActive(false);
				}
			});
		}
		super.event(name);
	}

	private boolean validateIp(String ip) {
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
	    return ip.matches(PATTERN);
	}
	
}
