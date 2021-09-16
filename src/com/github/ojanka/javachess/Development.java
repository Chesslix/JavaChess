package com.github.ojanka.javachess;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.util.ChessColor;

public class Development {

    public static void main(String[] args){
        Game game = new Game();

        // TODO: handled by network manager
        game.setTeam(ChessColor.WHITE);
        game.setupDefaultBoard();
    }
}
