package com.github.ojanka.javachess;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.game.pieces.*;

import com.github.ojanka.javachess.gui.GUIManager;

import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

public class Development {

    private void run(){
        Game game = Game.getInstance();
        game.setTeam(ChessColor.WHITE);

        // TODO: handled by network manager
        //game.setupDefaultBoard();
        test(game);

    }

    // TODO: Migrate method to Game
    private void test(Game game){
        Piece[] testGame = {
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
                //new King(3, 4, ChessColor.BLACK),		// 		King
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
        // CHECKMATE TEST
        game.setupBoard(testGame);
        game.consoleBoard();
        King king = game.getBoard().getKing(ChessColor.WHITE);
        System.out.println(king.isCheck());
        Position[] positions = king.getValidPositions();
    }

    public static void main(String[] args){
//        Development dev = new Development();
//        dev.run();
    	GUIManager.getInstance().startGUI();
    }
}
