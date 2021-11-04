package com.github.ojanka.javachess.gui.screens;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.game.pieces.King;
import com.github.ojanka.javachess.gui.GUIManager;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends Screen {
	private PApplet p = GUIManager.getInstance().getApplet();
	private PImage img;
	private PImage bishop_b;
	private PImage bishop_w;
	private PImage king_b;
	private PImage king_w;
	private PImage knight_b;
	private PImage knight_w;
	private PImage pawn_b;
	private PImage pawn_w;
	private PImage queen_b;
	private PImage queen_w;
	private PImage rook_b;
	private PImage rook_w;
	
	private Position[] validPositions;

	@Override
	public void init() {
		img = p.loadImage("com/github/ojanka/javachess/gui/util/statics/chessbackground.jpg");
		bishop_b = p.loadImage("./assets/textures/pieces/bishop_b.png");
		bishop_w = p.loadImage("./assets/textures/pieces/bishop_w.png");
		king_b = p.loadImage("./assets/textures/pieces/king_b.png");
		king_w = p.loadImage("./assets/textures/pieces/king_w.png");
		knight_b = p.loadImage("./assets/textures/pieces/knight_b.png");
		knight_w = p.loadImage("./assets/textures/pieces/knight_w.png");
		pawn_b = p.loadImage("./assets/textures/pieces/pawn_b.png");
		pawn_w = p.loadImage("./assets/textures/pieces/pawn_w.png");
		queen_b = p.loadImage("./assets/textures/pieces/queen_b.png");
		queen_w = p.loadImage("./assets/textures/pieces/queen_w.png");
		rook_b = p.loadImage("./assets/textures/pieces/rook_b.png");
		rook_w = p.loadImage("./assets/textures/pieces/rook_w.png");
		super.init();
	}

	@Override
	public void draw() {
		p.background(0);
		p.image(img, 0, 0, p.width, p.height);
		
		if(this.validPositions != null) {
			for(Position position : validPositions) {
				int[] dimensions = getFieldDimensions(position.getX(), position.getY());
				p.fill(p.color(66, 138, 245));
				p.rect(dimensions[0], dimensions[1], dimensions[2], dimensions[3]);
				p.fill(p.color(0,0,0));
			}
		}
		
		for (Piece piece : Game.getInstance().getBoard().getPieces()) {
			int[] dimensions = getFieldDimensions(piece.getCurrentPosition().getX(), piece.getCurrentPosition().getY());
			PImage imageToDraw = null;

			if (piece.getColor() == ChessColor.BLACK) {
				switch (piece.getClass().getSimpleName()) {
				case "Bishop": {
					imageToDraw = bishop_b;
					break;
				}
				case "King": {
					imageToDraw = king_b;
					break;
				}
				case "Knight": {
					imageToDraw = knight_b;
					break;
				}
				case "Pawn": {
					imageToDraw = pawn_b;
					break;
				}
				case "Queen": {
					imageToDraw = queen_b;
					break;
				}
				case "Rook": {
					imageToDraw = rook_b;
					break;
				}
				}

			} else {
				switch (piece.getClass().getSimpleName()) {
				case "Bishop": {
					imageToDraw = bishop_w;
					break;
				}
				case "King": {
					imageToDraw = king_w;
					break;
				}
				case "Knight": {
					imageToDraw = knight_w;
					break;
				}
				case "Pawn": {
					imageToDraw = pawn_w;
					break;
				}
				case "Queen": {
					imageToDraw = queen_w;
					break;
				}
				case "Rook": {
					imageToDraw = rook_w;
					break;
				}
				}
			}

			if (imageToDraw != null) {
				p.image(imageToDraw, dimensions[0], dimensions[1], dimensions[2], dimensions[3]);
			}
		}
		super.draw();
	}

	public int[] getFieldDimensions(int x, int y) {
		int[] resultarray = new int[4];
		resultarray[0] = p.width / 8 * x;
		resultarray[1] = p.height / 8 * y;
		resultarray[2] = p.width / 8;
		resultarray[3] = p.height / 8;
		return resultarray;
	}

	@Override
	public void event(String name) {
		if (name.equals("mouseRelease")) {
			System.out.println(returnField(p.mouseX) + " " + returnField(p.mouseY));
			Piece selectedPiece = Game.getInstance().getBoard().getPiece(returnField(p.mouseX), returnField(p.mouseY));
			if(selectedPiece != null) {
				this.validPositions = selectedPiece.getValidPositions();
			}
		}
		super.event(name);
	}

	private int returnField(int pixelposition) {
		int counter = 0;
		int fieldWidth = p.width / 8;
		while (pixelposition - fieldWidth >= 0) {
			pixelposition = pixelposition - fieldWidth;
			counter++;
		}
		return counter;
	}

}
