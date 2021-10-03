package com.github.ojanka.javachess.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import com.github.ojanka.javachess.gui.callbacks.CallbackHandler;
import com.github.ojanka.javachess.gui.screens.MainMenuScreen;
import com.github.ojanka.javachess.gui.screens.Screen;
import com.github.ojanka.javachess.gui.texturemanager.Texture;
import com.github.ojanka.javachess.gui.texturemanager.TextureManager;

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
		GL.createCapabilities();
		
		int[] windowSize = getWindowSize();
		glOrtho(0.f, windowSize[0], windowSize[1], 0.f, 0.f, 1.f);
		
		// Enable v-sync
		glfwSwapInterval(1);
		
		loadTextures();
		
		centerWindowOnMainScreen();
		
		glfwShowWindow(window);
		isRunning = true;
		this.screen = new MainMenuScreen();
	}
	
	public void loadTextures() {
	}
	
	public void gameLoop() {
		while(this.isRunning) {
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
	
	public int[] getWindowSize() {
		int[] windowWidth = new int[1];
		int[] windowHeight = new int[1];
		glfwGetWindowSize(window, windowWidth, windowHeight);
		return new int[] {windowWidth[0], windowHeight[0]};
 	}
	
	public void changeScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.dispatch();
		}
		screen.init();
		this.screen = screen;
	}
	
	public void drawText() {
		
	}
	
	public void drawTexture(Texture texture, int x, int y) {
		drawTexture(texture, x, y, texture.getWidth(), texture.getHeight());
	}
	
	public void drawTexture(Texture texture, int x, int y, int width, int height) {
		glEnable(GL_TEXTURE_2D);
		texture.bind();
		
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		
		glTexCoord2f(1, 0);
		glVertex2f(width, y);
		
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		
		glTexCoord2f(0, 1);
		glVertex2f(x, height);
		
		glDisable(GL_TEXTURE_2D);
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
