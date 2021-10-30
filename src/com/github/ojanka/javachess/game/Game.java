package com.github.ojanka.javachess.game;

import com.github.ojanka.javachess.game.pieces.*;
import com.github.ojanka.javachess.logger.EventLogger;
import com.github.ojanka.javachess.networking.NetworkManager;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.GameState;
import com.github.ojanka.javachess.util.Position;
import org.w3c.dom.events.Event;

public class Game {
	private static Game instance = null;
	private ChessColor team;
	private Board board;
	private EventLogger logger;     // first logger creation
	private GameState gameState;
	public boolean myTurn;

	private Game(){
	}
	
	public ChessColor getTeam() {
		return team;
	}
	
	public void setTeam(ChessColor team) {
		this.team = team;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setupDefaultBoard() {
		Piece[] pieces = {
				// WHITE:
				// Figures =====================================
				new Rook(0, 0, ChessColor.WHITE),		// left Rook
				new Knight(1, 0, ChessColor.WHITE),		// left Knight
				new Bishop(2, 0, ChessColor.WHITE),		// left Bishop
				new King(3, 0, ChessColor.WHITE),		// 		King
				new Queen(4, 0, ChessColor.WHITE),		// 		Queen
				new Bishop(5, 0, ChessColor.WHITE),		// right Bishop
				new Knight(6, 0, ChessColor.WHITE),		// right Knight
				new Rook(7, 0, ChessColor.WHITE),		// right Rook
				// Pawns =======================================
				new Pawn(0, 1, ChessColor.WHITE),		// 1 Pawn
				new Pawn(1, 1, ChessColor.WHITE),		// 2 Pawn
				new Pawn(2, 1, ChessColor.WHITE),		// 3 Pawn
				new Pawn(3, 1, ChessColor.WHITE),		// 4 Pawn
				new Pawn(4, 1, ChessColor.WHITE),		// 5 Pawn
				new Pawn(5, 1, ChessColor.WHITE),		// 6 Pawn
				new Pawn(6, 1, ChessColor.WHITE),		// 7 Pawn
				new Pawn(7, 1, ChessColor.WHITE),		// 8 Pawn

				//BLACK:
				// Figures =====================================
				new Rook(0, 7, ChessColor.BLACK),		// left Rook
				new Knight(1, 7, ChessColor.BLACK),		// left Knight
				new Bishop(2, 7, ChessColor.BLACK),		// left Bishop
				new King(3, 7, ChessColor.BLACK),		// 		King
				new Queen(4, 7, ChessColor.BLACK),		// 		Queen
				new Bishop(5, 7, ChessColor.BLACK),		// right Bishop
				new Knight(6, 7, ChessColor.BLACK),		// right Knight
				new Rook(7, 7, ChessColor.BLACK),		// right Rook
				// Pawns =======================================
				new Pawn(0, 6, ChessColor.BLACK),		// 1 Pawn
				new Pawn(1, 6, ChessColor.BLACK),		// 2 Pawn
				new Pawn(2, 6, ChessColor.BLACK),		// 3 Pawn
				new Pawn(3, 6, ChessColor.BLACK),		// 4 Pawn
				new Pawn(4, 6, ChessColor.BLACK),		// 5 Pawn
				new Pawn(5, 6, ChessColor.BLACK),		// 6 Pawn
				new Pawn(6, 6, ChessColor.BLACK),		// 7 Pawn
				new Pawn(7, 6, ChessColor.BLACK),		// 8 Pawn
		};
		this.board = new Board(pieces);
		this.logger = EventLogger.getInstance();
	}

	// TODO: For testing
	public void setupBoard(Piece[] pieces){
		this.board = new Board(pieces);
		this.logger = EventLogger.getInstance();
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public static Game getInstance() {
		if(instance == null){
			instance = new Game();
		}
		return instance;
	}

	public void consoleBoard(){
		for(int i = board.getPlayingField().length-1; i>=0; i--) {
			for (Piece p : board.getPlayingField()[i]) {
				if (p != null) {
					System.out.print("[ " + p.getClassName() + " ]");
				} else {
					System.out.print("[     ]");
				}
			}
			System.out.println("  "+i);
		}
		System.out.println("   0      1      2      3      4      5      6      7\n");
	}
	
	public void shutDown() {
		System.exit(0);
	}
}
