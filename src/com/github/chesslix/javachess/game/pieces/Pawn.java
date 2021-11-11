package com.github.chesslix.javachess.game.pieces;

import java.util.ArrayList;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.game.Piece;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.Position;

public class Pawn extends Piece {
	public Pawn(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
		super.firstTurn = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Position[] getValidPositions() {
		return this.getColor() == ChessColor.WHITE ? getWhiteValidPositions() : getBlackValidPositions();
	}

	private Position[] getWhiteValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAlliesBitmap(this.getColor());
		long bitboardEnemies = Game.getInstance().getBoard().getEnemiesBitmap(this.getColor());
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();

		long possibleMovesLookUp = whitePawnMovesLookUp(cPos);
		int[] possibleMoves = {9,7};
		ArrayList<Position> validPositions = new ArrayList<>();
		for(int move : possibleMoves){
			int nPos = cPos + move;
			if(((possibleMovesLookUp >> nPos) & 1) != 1) continue;
			if(((bitboardEnemies >> nPos) & 1) == 0) continue;
			validPositions.add(new Position(nPos & 7, nPos >>> 3));
		}

		if((cPos + 8) < 64 && ((bitboardAllies | bitboardEnemies) >>> cPos + 8 & 1) == 0){
			validPositions.add(new Position((cPos + 8) & 7, (cPos + 8) >>> 3));
			if(this.firstTurn) {
				if((cPos + 16) < 64 && ((bitboardAllies | bitboardEnemies) >>> cPos + 16 & 1) == 0){
					validPositions.add(new Position((cPos + 16) & 7, (cPos + 16) >>> 3));
				}
			}
		}

		return validPositions.toArray(Position[]::new);
	}

	private Position[] getBlackValidPositions() {
		long bitboardAllies = Game.getInstance().getBoard().getAlliesBitmap(this.getColor());
		long bitboardEnemies = Game.getInstance().getBoard().getEnemiesBitmap(this.getColor());
		int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();

		long possibleMovesLookUp = blackPawnMovesLookUp(cPos);
		int[] possibleMoves = {-9,-7};
		ArrayList<Position> validPositions = new ArrayList<>();
		for(int move : possibleMoves){
			int nPos = cPos + move;
			if(((possibleMovesLookUp >>> nPos) & 1) != 1) continue;
			if(((bitboardEnemies >>> nPos) & 1) == 0) continue;
			validPositions.add(new Position(nPos & 7, nPos >>> 3));
		}

		if((cPos - 8) < 64 && ((bitboardAllies | bitboardEnemies) >>> cPos - 8 & 1) == 0){
			validPositions.add(new Position((cPos - 8) & 7, (cPos - 8) >>> 3));
			if(this.firstTurn) {
				if((cPos - 16) < 64 && ((bitboardAllies | bitboardEnemies) >>> cPos - 16 & 1) == 0){
					validPositions.add(new Position((cPos - 16) & 7, (cPos - 16) >>> 3));
				}
			}
		}

		return validPositions.toArray(Position[]::new);
	}

	// gets all possible moves including two step firstTurn for white
	private long whitePawnMovesLookUp(long pawnPos){
		long bitboardPawn = 1L << pawnPos;
		long l1 = (bitboardPawn >>> 1) & 0x7f7f7f7f7f7f7f7fL;
		long r1 = (bitboardPawn << 1) & 0xfefefefefefefefeL;
		long h1 = l1 | r1;
		return (h1<<8) | (bitboardPawn << 8) | (bitboardPawn << 16);
	}

	// gets all possible moves including two step firstTurn for black
	private long blackPawnMovesLookUp(long pawnPos){
		long bitboardPawn = 1L << pawnPos;
		long l1 = (bitboardPawn >>> 1) & 0x7f7f7f7f7f7f7f7fL;
		long r1 = (bitboardPawn << 1) & 0xfefefefefefefefeL;
		long h1 = l1 | r1;
		return (h1>>>8) | (bitboardPawn >>> 8) | (bitboardPawn >>> 16);
	}
}
