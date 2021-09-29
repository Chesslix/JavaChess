package com.github.ojanka.javachess.util;

public enum ChessColor {
	BLACK("BLACK"),
	WHITE("WHITE");
	
	private String color;
	
	ChessColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return color;
	}
	
	public static ChessColor fromString(String color) {
		if (color.toUpperCase().equals("BLACK")) {
			return BLACK;
		} else if (color.toUpperCase().equals("WHITE")) {
			return WHITE;
		} else {
			return null;
		}
	}

	public ChessColor getOpposite() {
		if (this.color.equals("BLACK")) {
			return ChessColor.WHITE;
		} else return ChessColor.BLACK;
	}
}
