package com.github.ojanka.javachess.gui.texturemanager;

import java.util.HashMap;

public class TextureManager {
	private static TextureManager instance;
	
	private HashMap<String, Texture> textures = new HashMap<>();
	
	public void addTexture(String id, String filePath) {
		try {
			textures.put(id, new Texture(filePath));
		} catch (Exception e) {
			System.out.println("Could not load texture: " + filePath);
			e.printStackTrace();
		}
	}
	
	public Texture getTexture(String id) {
		return textures.get(id);
	}
	
	public static TextureManager getInstance() {
		if(instance == null) {
			instance = new TextureManager();
		}
		return instance;
	}
}
