package com.github.ojanka.javachess;

import com.github.ojanka.javachess.game.Board;
import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.game.pieces.*;
import com.github.ojanka.javachess.logger.EventLogger;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.Arrays;
import java.util.stream.Stream;

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
                new King(3, 0, ChessColor.WHITE),		// 		King

                //BLACK:
                // Figures =====================================
                new Rook(3, 4, ChessColor.BLACK),		// left Rook
        };
        // CHECKMATE TEST
        game.setupBoard(testGame);
        Position[] rookpos = game.getBoard().getPiece(3, 4).getValidPositions();
        King king = game.getBoard().getKing(ChessColor.WHITE);
        System.out.println(king.isCheck());
        game.consoleBoard();

        /*
        for(int y = 6; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Piece piece = game.getBoard().getPiece(x, y);
                Position[] vpositions = piece.getValidPositions();
                System.out.printf("x: %d | y: %d", x, y);
                System.out.print("\tMovable positions:\t");
                for(Position vpos : vpositions){
                    System.out.printf("x%d y%d ", vpos.getX(), vpos.getY());
                }
                System.out.println();
            }
        }
         */
    }

    public static void main(String[] args){
        Development dev = new Development();
        dev.run();
    }
}
