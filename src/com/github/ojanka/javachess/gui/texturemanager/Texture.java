package com.github.ojanka.javachess.gui.texturemanager;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	private int id;
	private int width, height;
	
	public Texture(String filePath) throws Exception {
		FileInputStream is = new FileInputStream(new File(filePath));
		this.init(is);
	}
	
	public Texture(InputStream inputStream) throws Exception {
		this.init(inputStream);
	}
	
	private void init(InputStream inputStream) throws Exception {
		BufferedImage bi = ImageIO.read(inputStream);
		this.width = bi.getWidth();
		this.height = bi.getHeight();
		
		int[] rawPixels = bi.getRGB(0, 0, this.width, this.height, null, 0, this.width);
		
		ByteBuffer pixels = BufferUtils.createByteBuffer(this.width * this.height * 4);
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				int pixel = rawPixels[y*this.width+x];
				pixels.put( (byte) ((pixel & 0xff0000) >> 16));    //RED
				pixels.put( (byte) ((pixel & 0xff00) >> 8));       //GREEN
				pixels.put( (byte) (pixel & 0xff));                //BLUE
				pixels.put( (byte) ((pixel & 0xff000000) >>> 24)); //ALPHA
			}
		}
		
		pixels.flip();
		
		this.id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, this.id);
	}
}
