package com.github.ojanka.javachess.gui.fontmanager;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.zip.ZipFile;

public class JCFont {
	
	public final String name;
	public final String version;
	public final boolean ignaoreCase;
	
	public JCFont(String filepath) {
		try {
			ZipFile jcf = new ZipFile(filepath);
			
			var characterMap = jcf.getEntry("characters.bmp");
			var info = jcf.getEntry("fontinfo.txt");
			
			if (characterMap == null || info == null) throw new RuntimeException("Invalid jcf-file");
			
			
			
		} catch (Exception e) {
			this.name = null;
			this.version = null;
			this.ignaoreCase = false;
			System.out.println("Could not load the font: " + filepath);
			e.printStackTrace();
		}
	}
	
	private static class FontCharacter {
		
	}
}
