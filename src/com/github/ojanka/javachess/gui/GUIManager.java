package com.github.ojanka.javachess.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.github.ojanka.javachess.gui.callbacks.CallbackHandler;
import com.github.ojanka.javachess.gui.screens.MainMenuScreen;
import com.github.ojanka.javachess.gui.screens.Screen;

public class GUIManager {
	public long window;
	
	private static GUIManager instance;
	private Screen screen;
	private CallbackHandler callbackHandler;
	
	private boolean isRunning;
	
	private GUIManager() {}
	
	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		this.window = glfwCreateWindow(950, 850, "JavaChess", 0, 0);
		
		//Add callbacks
		this.callbackHandler = new CallbackHandler();
		glfwSetCursorPosCallback(this.window, callbackHandler.cursorPosition);
		glfwSetMouseButtonCallback(this.window, callbackHandler.mouseButton);
		
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);
		
		centerWindowOnMainScreen();
		
		glfwShowWindow(window);
		isRunning = true;
		this.screen = new MainMenuScreen();
	}
	
	public void gameLoop() {
		while(this.isRunning) {
			GL.createCapabilities();
			glfwPollEvents();
			
			// Clear window
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			if (this.screen != null) {
				screen.draw();
			} else {
				this.changeScreen(new MainMenuScreen());
			}
			
			glfwSwapBuffers(window);
			
			if(glfwWindowShouldClose(window)) {
				shutdown();
			}
		}
		glfwTerminate();
	}
	
	public void startGUI() {
		if (!this.isRunning) {
			init();
			gameLoop();
		} else throw new IllegalStateException("Cannot start the GUI twice");
	}
	
	public void shutdown() {
		// PREPARE
		isRunning=false;
	}
	
	private void centerWindowOnMainScreen() {
		var monitors = glfwGetMonitors();
		int[] workareaWidth = new int[1];
		int[] workareaHeight = new int[1];
		int[] windowWidth = new int[1];
		int[] windowHeight = new int[1];
		glfwGetMonitorWorkarea(monitors.get(), null, null, workareaWidth, workareaHeight);
		glfwGetWindowSize(window, windowWidth, windowHeight);
		
		glfwSetWindowPos(window, workareaWidth[0] / 2 - windowWidth[0] / 2, workareaHeight[0] / 2 - windowHeight[0] / 2);
	}
	
	public void changeScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.dispatch();
		}
		screen.init();
		this.screen = screen;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public CallbackHandler getCallbackHandler() {
		return callbackHandler;
	}
	
	public static GUIManager getInstance() {
		if (instance == null) {
			instance = new GUIManager();
		}
		return instance;
	}

}
