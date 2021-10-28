package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.ArrayList;

public class King extends Piece {

	public King(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		long bitboard = Game.getInstance().getBoard().getAsBitmapByColor(this.getColor());
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
		long possibleMovesLookUp = kingMovesLookUp(cPos);
		int[] possibleMoves = {1, 7, 8, 9, -1, -7, -8, -9};
		ArrayList<Position> validPositions = new ArrayList<>();
		// TODO: Add king castling
		for(int move: possibleMoves){
			int nPos = cPos + move;
			if(((possibleMovesLookUp >> nPos) & 1) != 1) continue;
			if(((bitboard >> nPos) & 1) == 1) continue;
			validPositions.add(new Position(nPos & 7, nPos >>> 3));
		}
		return validPositions.toArray(Position[]::new);
	}

	/**
	 *
	 * @return 0 = no castling possible / 1 small castling possible / 2 large castling possible / both castlings possible
	 */
	// TODO: Implement this beauty :) I have depressions
	private short canCastle(){
		return 0;
	}

	// FIXME: It is fucking 00:20 and my brain stopped working. Every time a king is selected, this function needs to be activated
	private Position[] getNoCheckPositions(){
		ArrayList<Position> validPositions = new ArrayList<>();
		Position[] possiblePositions = this.getValidPositions();
		long enemyMoves = Game.getInstance().getBoard().getAllPossibleMovesBoard(this.getColor().getOpposite());
		for(Position pos : possiblePositions){
			int nPos = pos.getY() * 8 + pos.getX();
			if(((enemyMoves >> nPos) & 1) == 1){
				validPositions.add(pos);
			}
		}
		return validPositions.toArray(Position[]::new);
	}

	public boolean isCheck(){
		long enemyMoves = Game.getInstance().getBoard().getAllPossibleMovesBoard(this.getColor().getOpposite());
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
		return (enemyMoves & cPos) > 0;
	}

	// TODO: Implement Checkmate checker
	public boolean isCheckmate(){
		if((this.getValidPositions().length == 0) && this.isCheck()){
			// can he castle -> yes -> castle & try again
			// get enemies that threaten the king / can they be destroyed? -> no
			// get positions of directions from which king is threatened. Can allie step on these positions & attacker is no knight -> no
			// return true
		}
		return false;
	}

	// TODO: Implement Draw (Player can't move any piece or King is Checkmate) checker
	public boolean isDraw(){
		return false;
	}

	private long kingMovesLookUp(long kingPos){
		// super complex hardcore bithacks! :)
		long bitboardKing = 1L << kingPos;
		long l1 = (bitboardKing >>> 1) & 0x7f7f7f7f7f7f7f7fL;
		long r1 = (bitboardKing << 1) & 0xfefefefefefefefeL;
		long h1 = l1 | r1;
		return (h1<<8) | (h1>>>8) | h1 | (bitboardKing >>> 8) | (bitboardKing << 8);
	}

}
