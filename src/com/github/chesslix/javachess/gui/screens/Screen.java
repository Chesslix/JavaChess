package com.github.chesslix.javachess.gui.screens;

import java.util.HashMap;
import java.util.function.Consumer;

import com.github.chesslix.javachess.gui.widgets.Widget;

/**
 * The abstract class for a screen. To prevent bugs in the widget list, children should always call super-methods
 *
 */
public abstract class Screen {
	private HashMap<String, Widget> widgetList = new HashMap<>();
	
	/**
	 * Called before the screen is displayed the first times
	 */
	public void init() {
		for (Widget widget : widgetList.values()) {
			widget.init();
		}
	}
	
	/**
	 * Called every frame.
	 */
	public void draw() {
		for (Widget widget : widgetList.values()) {
			widget.draw();
		}
	}
	
	/**
	 * Called when the screen is no longer used. It might be used again, so you may close everything you open in {@link #init()} here but leave open what you create in the constructor
	 */
	public void dispatch() {
		for (Widget widget : widgetList.values()) {
			widget.dispatch();
		}
	}
	
	/**
	 * Called on events
	 * @param name processing event Name
	 */
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
