package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.ArrayList;

public class Pawn extends Piece {
	private boolean start = true;

	public Pawn(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAsBitmapByColor(this.getColor());
		long bitboardEnemies = Game.getInstance().getBoard().getAsBitmapByColor(ChessColor.getOpposite(this.getColor()));

		ArrayList<Position> validPositions = new ArrayList<>();
		int[] possibleMoves = {9,7};
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
		for(int move : possibleMoves){
			int nPos = cPos + move;
			if(nPos > 63 || nPos < 0) continue;
			if(((bitboardEnemies >> nPos) & 1) == 0) continue;
			validPositions.add(new Position(nPos & 7, nPos >> 3));
		}

		if((cPos + 8) < 64 && ((bitboardAllies | bitboardEnemies) >> cPos + 8 & 1) == 0){
			validPositions.add(new Position((cPos + 8) & 7, (cPos + 8) >> 3));
			if(this.start) {
				if((cPos + 16) < 64 && ((bitboardAllies | bitboardEnemies) >> cPos + 16 & 1) == 0){
					validPositions.add(new Position((cPos + 16) & 7, (cPos + 16) >> 3));
				}
			}
		}
		
		return validPositions.toArray(Position[]::new);
	}

	@Override
	public boolean isPositionValid(Position arg0) {
		// TODO Auto-generated method stub
		// if return true also do this.start = false;
		return false;
	}

	public boolean getStart() {
		return start;
	}
}
