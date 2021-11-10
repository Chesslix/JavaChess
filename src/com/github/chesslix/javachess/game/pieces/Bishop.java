package com.github.chesslix.javachess.game.pieces;

import java.util.ArrayList;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.game.Piece;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.Position;

public class Bishop extends Piece {

	public Bishop(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAlliesBitmap(this.getColor());
		long bitboardEnemies = Game.getInstance().getBoard().getEnemiesBitmap(this.getColor());
		int cPos = getCurrentPosition().getY() * 8 + getCurrentPosition().getX();
		ArrayList<Position> validPositions = new ArrayList<>();
		int[] possibleMoves = {7, 9, -7, -9};
		for(int move : possibleMoves){
			for(int nPos = cPos+move;nPos<64 && nPos>-1; nPos+=move){
				// check if on border of field
				if((nPos+1) % 8 == 0 || nPos % 8 == 0) {
					// check if last field x axe was more than 1 difference (only for bishop)
					if(Math.abs((nPos % 8)-((nPos-move) % 8)) > 1){
						break;
					}
				}
				// check if ally on pos  break
				if(((bitboardAllies >> nPos) & 1) == 1){break;}
				// check if enemy on board  -> break
				if(((bitboardEnemies >> nPos) & 1) == 1){
					validPositions.add(new Position(nPos % 8, nPos >>> 3));
					break;
				}
				// -> add to validPositions
				validPositions.add(new Position(nPos % 8, nPos >>> 3));
			}
		}
		return validPositions.toArray(Position[]::new);
	}

}
