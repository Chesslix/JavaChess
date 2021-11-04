package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		// get bitboard allies marked as 1
		long bitboard = Game.getInstance().getBoard().getAlliesBitmap(this.getColor());
		// get current position and translate to fit to 1 dimensional array
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
		// bitmap of all possible moves!
		long possibleMovesLookUp = knightMovesLookUp(cPos);
		// all possible moves according to pattern
		int[] possibleMoves = {6, 15, 17, 10, -6, -15, -17, -10};
		ArrayList<Position> validPositions = new ArrayList<>();
		for(int move: possibleMoves){
			// add current position with move value to calculate new "index" move
			int nPos = cPos + move;
			// ungodly out of bounds check
			if(((possibleMovesLookUp >> nPos) & 1) != 1) continue;
			// check if ally on position, if not means either empty or enemy
			if(((bitboard >> nPos) & 1) == 1) continue;
			// set new position, nPos >> 3 is the same as nPos / 8
			validPositions.add(new Position(nPos & 7, nPos >>> 3));
		}

		return validPositions.toArray(Position[]::new);
	}

	private long knightMovesLookUp(long knightPos){
			// super complex hardcore bithacks! :) Not :)
			long bitboardKnight = 1L << knightPos;
			long l1 = (bitboardKnight >>> 1) & 0x7f7f7f7f7f7f7f7fL;
			long l2 = (bitboardKnight >>> 2) & 0x3f3f3f3f3f3f3f3fL;
			long r1 = (bitboardKnight << 1) & 0xfefefefefefefefeL;
			long r2 = (bitboardKnight << 2) & 0xfcfcfcfcfcfcfcfcL;
			long h1 = l1 | r1;
			long h2 = l2 | r2;
			return (h1<<16) | (h1>>>16) | (h2<<8) | (h2>>>8);
	}
}
