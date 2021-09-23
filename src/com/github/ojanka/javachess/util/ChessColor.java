package com.github.ojanka.javachess.util;

public enum ChessColor {
	BLACK,
	WHITE;

	public static ChessColor getOpposite(ChessColor chessColor){
		if(chessColor == WHITE) return BLACK;
		return WHITE;
	}
}
