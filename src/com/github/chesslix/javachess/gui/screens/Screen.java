package com.github.chesslix.javachess.gui.screens;

import java.util.HashMap;
import java.util.function.Consumer;

import com.github.chesslix.javachess.gui.widgets.Widget;

public abstract class Screen {
	private HashMap<String, Widget> widgetList = new HashMap<>();
	
	public void init() {
		for (Widget widget : widgetList.values()) {
			widget.init();
		}
	}
	
	public void draw() {
		for (Widget widget : widgetList.values()) {
			widget.draw();
		}
	}
	
	public void dispatch() {
		for (Widget widget : widgetList.values()) {
			widget.dispatch();
		}
	}
	
	public void event(String name) {
		for (Widget widget : widgetList.values()) {
			widget.event(name);
		}
	}
	
	public Widget getWidget(String name) {
		return widgetList.get(name);
	}
	
	public void addWidget(String name, Widget widget) {
		widgetList.put(name, widget);
	}
	
	public void forEachWidget(Consumer<? super Widget> action) {
		this.widgetList.values().forEach(action);
	}
}
