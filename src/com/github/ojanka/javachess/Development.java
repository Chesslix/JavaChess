package com.github.ojanka.javachess;

import com.github.ojanka.javachess.game.Board;
import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.Arrays;
import java.util.stream.Stream;

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
        System.out.println(ChessColor.getOpposite(ChessColor.WHITE));
        System.out.println(ChessColor.getOpposite(ChessColor.BLACK));
        game.consoleBoard();
        game.getBoard().movePiece(game.getBoard().getPiece(0, 1), 0, 2);
        game.consoleBoard();
        game.getBoard().movePiece(game.getBoard().getPiece(0, 6), 0, 2);
        game.consoleBoard();
        game.getBoard().movePiece(game.getBoard().getPiece(0, 2), 0, 1);
        game.consoleBoard();

    }

    public static void main(String[] args){
        Development dev = new Development();
        dev.run();
    }
}
