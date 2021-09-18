package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.LinkedList;

public class Pawn extends Piece {

	public Pawn(int startX, int startY, ChessColor color) {
		super(startX, startY, color);
	}

	@Override
	public Position[] getValidPositions() {
		int x = super.getCurrentPosition().getX();
		int y = super.getCurrentPosition().getY();
		// currently, only for white team
		boolean firstTurn = false;
		LinkedList<Position> possibleMovementPositions = new LinkedList<>();
		LinkedList<Position> possibleKillingPositions = new LinkedList<>();
		LinkedList<Position> valideMovementPositions = new LinkedList<>();
		LinkedList<Position> valideKillingPositions = new LinkedList<>();
		if(super.getCurrentPosition().getY() == 1) {
			firstTurn = true;
		}
		// TODO: Add border of board exceptions

		// movement pattern
		possibleMovementPositions.add(new Position(x, y+1));
		if(firstTurn){
			possibleMovementPositions.add(new Position(x, y+2));
		}
		// kill pattern
		possibleKillingPositions.add(new Position(x-1, y+1));
		possibleKillingPositions.add(new Position(x+1, y+1));

		// TODO: Add obstructed pieces exception
		// validate possibleMovementPositions
		Piece[] pieces = Game.getInstance().getBoard().getPieces();

		for(Position possibleMovement : possibleMovementPositions) {
			boolean valid = true;
			for (Piece piece : pieces) {
				if (piece.getCurrentPosition() == possibleMovement) {
					valid = false;
					break;                    // performance
				}
			}
			if (valid) {
				valideMovementPositions.add(possibleMovement);
			}
		}

		// validate possibleKillPositions
		for(Position possibleKill : possibleKillingPositions){
			boolean kill = false;
			for (Piece piece : pieces) {
				if (piece.getCurrentPosition() == possibleKill && piece.getColor() != super.getColor()){
					kill = true;
					break;
				}
			}
			if (kill){
				valideKillingPositions.add(possibleKill);
			}
		}

		// convert LinkedList to Position[]
		Position[] validePositions = new Position[valideKillingPositions.size() + valideMovementPositions.size()];
		for(int i = 0; i<valideKillingPositions.size(); i++){
			validePositions[i] = valideKillingPositions.get(i);
		}
		for(int i = valideKillingPositions.size(); i<valideMovementPositions.size(); i++){
			validePositions[i] = valideMovementPositions.get(i);
		}

		// optionals?
		return validePositions;
	}

	@Override
	public boolean isPositionValid(Position arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
