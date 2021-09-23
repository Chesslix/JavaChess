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
		long bitboard = Game.getInstance().getBoard().getAsBitmapByColor(this.getColor());
		// get current position and translate to fit to 1 dimensional array
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
		// all possible moves according to pattern
		int[] possibleMoves = {6, 15, 17, 10, -6, -15, -17, -10};
		ArrayList<Position> validPositions = new ArrayList<>();
		for(int move: possibleMoves){
			// add current position with move value to calculate new "index" move
			int nPos = cPos + move;
			// check if position is out of board
			if(nPos > 63 || nPos < 0) continue;
			// check if ally on position, if not means either empty or enemy
			if(((bitboard >> nPos) & 1) == 1) continue;
			// set new position, nPos >> 3 is the same as nPos / 8
			validPositions.add(new Position(nPos % 8, nPos >> 3));
		}

		return validPositions.toArray(Position[]::new);
	}

	@Override
	public boolean isPositionValid(Position arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
