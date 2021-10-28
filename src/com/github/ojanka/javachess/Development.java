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
        game.setupDefaultBoard();
        test(game);

    }

    // TODO: Migrate method to Game
    private void test(Game game){
        Piece[] testGame = {
                // WHITE:
                // Figures =====================================
                new Rook(2, 3, ChessColor.WHITE),		// left Rook
                new Knight(1, 5, ChessColor.WHITE),		// left Knight
                new King(3, 1, ChessColor.WHITE),		// 		King
                new Queen(4, 2, ChessColor.WHITE),		// 		Queen
                new Bishop(5, 0, ChessColor.WHITE),		// right Bishop
                new Knight(5, 3, ChessColor.WHITE),		// right Knight
                new Rook(6, 0, ChessColor.WHITE),		// right Rook
                // Pawns =======================================
                new Pawn(0, 1, ChessColor.WHITE),		// 1 Pawn
                new Pawn(1, 4, ChessColor.WHITE),		// 2 Pawn
                new Pawn(4, 1, ChessColor.WHITE),		// 5 Pawn
                new Pawn(5, 2, ChessColor.WHITE),		// 6 Pawn
                new Pawn(6, 2, ChessColor.WHITE),		// 7 Pawn
                new Pawn(7, 1, ChessColor.WHITE),		// 8 Pawn

                //BLACK:
                // Figures =====================================
                new Rook(0, 7, ChessColor.BLACK),		// left Rook
                new King(3, 7, ChessColor.BLACK),		// 		King
                new Queen(4, 7, ChessColor.BLACK),		// 		Queen
                new Bishop(5, 7, ChessColor.BLACK),		// right Bishop
                new Knight(6, 7, ChessColor.BLACK),		// right Knight
                // Pawns =======================================
                new Pawn(0, 2, ChessColor.BLACK),		// 1 Pawn
                new Pawn(1, 6, ChessColor.BLACK),		// 2 Pawn
                new Pawn(2, 6, ChessColor.BLACK),		// 3 Pawn
                new Pawn(3, 6, ChessColor.BLACK),		// 4 Pawn
        };
        game.setupBoard(testGame);
        Position[] positions = game.getBoard().getPiece(4, 7).getValidPositions();
        for(Position position : positions){
            System.out.println("Pos Y: "+position.getY()+"| Pos X: "+position.getX());
        }
        System.out.println();
        game.consoleBoard();

    }

    public static void main(String[] args){
//        Development dev = new Development();
//        dev.run();
    	GUIManager.getInstance().startGUI();
    }
}
