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
        Game game = Game.createGame();

        // TODO: handled by network manager
        game.setupDefaultBoard();
        game.setTeam(ChessColor.WHITE);

        round(game);
    }

    // TODO: Migrate method to Game
    private void round(Game game){

        Board board = game.getBoard();
        Piece[] pieces = board.getPieces();

        // Select Piece from team
        pieces = Arrays.stream(pieces).filter(piece -> piece.getColor() == game.getTeam()).toArray(Piece[]::new);

        // TODO: Implement UI Package with selected piece id
        // fake package
        Position selected = new Position(1,0);

        // select selected
        //filter piece to selected
    }

    public static void main(String[] args){
        Development dev = new Development();
        dev.run();
    }
}
