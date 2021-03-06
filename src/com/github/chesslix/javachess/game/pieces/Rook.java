package com.github.chesslix.javachess.game.pieces;

import java.util.ArrayList;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.game.Piece;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.Position;


/**
 * The Rook "Turm" of Chess. This class extends Piece
 * and is inheriting the main functions.
 *
 * @version 1.0
 * @author David Abderhalden
 */
public class Rook extends Piece {

	public void setFirstTurn(){
		this.firstTurn = false;
	}

	public boolean isFirstTurn() {
		return firstTurn;
	}

	public Rook(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		super.firstTurn = true;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Valid Positions are all positions on the Chess Board, the
	 * Rook can move to. The calculation is made by Bitmaps, heavily dependent
	 * on them.
	 *
	 * The Rook moves horizontally and vertically and can't jump over allies
	 *
	 * @return All possible moves as Position Array
	 */
	@Override
	public Position[] getValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAlliesBitmap(this.getColor());
		long bitboardEnemies = Game.getInstance().getBoard().getEnemiesBitmap(this.getColor());
		int cPos = getCurrentPosition().getY() * 8 + getCurrentPosition().getX();
		ArrayList<Position> validPositions = new ArrayList<>();
		// relative moves
		int[] possibleMoves = {1, -1, 8, -8};
		for(int move : possibleMoves){
			// while inside the vertical scope of the board
			for(int nPos = cPos + move; nPos>-1 && nPos<64; nPos+=move){
				// check if on left border
				if(((nPos+1) % 8 == 0) && move == -1){
					//validPositions.add(new Position(0, nPos >>> 3));
					break;
				}

				// check if on right border
				if((nPos % 8 == 0) && move == 1){
					//validPositions.add(new Position(0, nPos >>> 3));
					break;
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
