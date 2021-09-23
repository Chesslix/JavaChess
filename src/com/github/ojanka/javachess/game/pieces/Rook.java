package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

public class Rook extends Piece {

	public Rook(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAlliesAsBitmap(this.getColor());
		int cPos = getCurrentPosition().getY() * 8 + getCurrentPosition().getX();
		// relative moves
		int[] possibleMovesUp = {8, 16, 24, 32, 40, 48, 56};
		int[] possibleMovesDown = {-8, -16, -24, -32, -40, -48, -56};
		int[] possibleMovesLeft = {-1, -2, -3, -4, -5, -6, -7};
		int[] possibleMovesRight = {1, 2, 3, 4, 5, 6, 7};
		for(int move : possibleMovesUp){
			// calc absolute move
			// check if out of board	continue
			// check if ally on pos		continue
			// check if enemy on board  -> break

			// -> add to validPositions
		}
		return null;
	}

	@Override
	public boolean isPositionValid(Position arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
