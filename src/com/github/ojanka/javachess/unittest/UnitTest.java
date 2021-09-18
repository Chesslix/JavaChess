package com.github.ojanka.javachess.unittest;
import com.github.ojanka.javachess.game.pieces.Pawn;
import com.github.ojanka.javachess.util.ChessColor;

public class UnitTest{

    public static void main(String[] args) {
        Pawn pawn1 = new Pawn(0, 1, ChessColor.WHITE);
        Pawn pawn2 = new Pawn(0, 6, ChessColor.BLACK);
        pawn1.getValidPositions();
        pawn2.getValidPositions();
    }
}