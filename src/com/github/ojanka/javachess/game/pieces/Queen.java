package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAsBitmapByColor(this.getColor());
		long bitboardEnemies = Game.getInstance().getBoard().getAsBitmapByColor(ChessColor.getOpposite(this.getColor()));
		int cPos = getCurrentPosition().getY() * 8 + getCurrentPosition().getX();
		ArrayList<Position> validPositions = new ArrayList<>();
		int[] possibleMoves = {1, 7, 8, 9, -1, -7, -8, -9};
		for (int move : possibleMoves) {
			for (int nPos = cPos + move; nPos < 64 && nPos > -1; nPos += move) {
				// check if ally
				if (((bitboardAllies >> nPos) & 1) == 1) {
					break;
				}
				// check if enemy
				if (((bitboardEnemies >> nPos) & 1) == 1) {
					validPositions.add(new Position(nPos % 8, nPos >>> 3));
					break;
				}
				// check if on boarder
				if ((nPos + 1) % 8 == 0 || nPos % 8 == 0) {
					validPositions.add(new Position(nPos % 8, nPos >>> 3));
					break;
				}
				// add to valid positions
				validPositions.add(new Position(nPos % 8, nPos >>> 3));
			}
		}
		return validPositions.toArray(Position[]::new);
	}

}
