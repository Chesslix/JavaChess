package com.github.ojanka.javachess;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.game.pieces.*;

import com.github.ojanka.javachess.gui.GUIManager;

import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

public class Development {

    private void run() {
        Game game = Game.getInstance();
        game.setTeam(ChessColor.WHITE);
        game.setupDefaultBoard();
        Game.getInstance().startRound();
        test(game);

    }

    // TODO: Migrate method to Game
    private void test(Game game) {
        Piece[] testGame = {
                // WHITE:
                // Figures =====================================
                new Rook(2, 3, ChessColor.WHITE),        // left Rook
                new Knight(1, 5, ChessColor.WHITE),        // left Knight
                new King(3, 1, ChessColor.WHITE),        // 		King
                new Queen(4, 2, ChessColor.WHITE),        // 		Queen
                new Bishop(5, 0, ChessColor.WHITE),        // right Bishop
                new Knight(5, 3, ChessColor.WHITE),        // right Knight
                new Rook(6, 0, ChessColor.WHITE),        // right Rook
                // Pawns =======================================
                new Pawn(0, 1, ChessColor.WHITE),        // 1 Pawn
                new Pawn(1, 4, ChessColor.WHITE),        // 2 Pawn
                new Pawn(4, 1, ChessColor.WHITE),        // 5 Pawn
                new Pawn(5, 2, ChessColor.WHITE),        // 6 Pawn
                new Pawn(6, 2, ChessColor.WHITE),        // 7 Pawn
                new Pawn(7, 1, ChessColor.WHITE),        // 8 Pawn

                //BLACK:
                // Figures =====================================
                new Rook(0, 7, ChessColor.BLACK),        // left Rook
                new King(3, 7, ChessColor.BLACK),        // 		King
                new Queen(4, 7, ChessColor.BLACK),        // 		Queen
                new Bishop(5, 7, ChessColor.BLACK),        // right Bishop
                new Knight(6, 7, ChessColor.BLACK),        // right Knight
                // Pawns =======================================
                new Pawn(0, 2, ChessColor.BLACK),        // 1 Pawn
                new Pawn(1, 6, ChessColor.BLACK),        // 2 Pawn
                new Pawn(2, 6, ChessColor.BLACK),        // 3 Pawn
                new Pawn(3, 6, ChessColor.BLACK),        // 4 Pawn
                new Pawn(0, 1, ChessColor.WHITE),        // 4 Pawn
                new Pawn(1, 2, ChessColor.BLACK),        // 4 Pawn
                new Pawn(0, 3, ChessColor.BLACK),        // 4 Pawn
        };
        // LOGGER TEST
        game.setupDefaultBoard();
        //game.getBoard().killPiece(game.getBoard().getPiece(0,0));
        game.getBoard().killPiece(game.getBoard().getPiece(1, 7));
        game.getBoard().killPiece(game.getBoard().getPiece(2, 7));
        game.getBoard().killPiece(game.getBoard().getPiece(3, 7));

        game.getBoard().killPiece(game.getBoard().getPiece(5, 7));
        game.getBoard().killPiece(game.getBoard().getPiece(6, 7));

        game.getBoard().movePiece(game.getBoard().getPiece(3, 0), 2, 7);

        game.startRound();

        game.consoleBoard();

        King king = (King) game.getBoard().getPiece(4, 7);
        King king2 = (King) game.getBoard().getPiece(4, 0);

        System.out.println(king.isCheck());

        System.out.println(king2.isCheck());
    }
}
