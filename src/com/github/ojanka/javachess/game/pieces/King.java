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
		for(int move: possibleMoves){
			int nPos = cPos + move;
			if(((possibleMovesLookUp >> nPos) & 1) != 1) continue;
			if(((bitboard >> nPos) & 1) == 1) continue;
			validPositions.add(new Position(nPos & 7, nPos >>> 3));
		}
		return validPositions.toArray(Position[]::new);
	}

	// TODO: Implement Check checker :)
	public boolean isCheck(){
		return false;
	}

	// TODO: Implement Checkmate checker
	public boolean isCheckmate(){
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
