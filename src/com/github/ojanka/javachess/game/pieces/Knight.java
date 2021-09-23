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
		long bitboard = Game.getInstance().getBoard().getAlliesAsBitmap(this.getColor());
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
		int[] possibleMoves = {6, 15, 17, 10, -6, -15, -17, -10};
		ArrayList<Position> validPositions = new ArrayList<>();
		for(int move: possibleMoves){
			int nPos = cPos + move;
			if(nPos > 63 || nPos < 0) continue;
			if(((bitboard >> nPos) & 1) == 1) continue;
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
