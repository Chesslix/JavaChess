package com.github.ojanka.javachess.util;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Position arg0) {
		return (this.x == arg0.x && this.y == arg0.y);
	}
	
	public boolean equals(int x, int y) {
		return (this.x == x && this.y == y);
	}
}
