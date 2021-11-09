package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.ArrayList;

public class King extends Piece {
    public void setFirstTurn(){
        this.firstTurn = false;
    }

    public King(int startX, int startY, ChessColor color) {
        super(startX, startY, color);
        super.firstTurn = true;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Position[] getValidPositions() {
        long bitboard = Game.getInstance().getBoard().getAlliesBitmap(this.getColor());
        long allPieces = bitboard | Game.getInstance().getBoard().getEnemiesBitmap(this.getColor());
        int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
        long possibleMovesLookUp = kingMovesLookUp(cPos);
        int[] possibleMoves = {1, 7, 8, 9, -1, -7, -8, -9};
        ArrayList<Position> validPositions = new ArrayList<>();
        for (int move : possibleMoves) {
            int nPos = cPos + move;
            if (((possibleMovesLookUp >> nPos) & 1) != 1) continue;
            if (((bitboard >> nPos) & 1) == 1) continue;
            validPositions.add(new Position(nPos & 7, nPos >>> 3));
        }

        if (this.firstTurn) {
            for (Piece piece : Game.getInstance().getBoard().getPieces()) {
                if (piece != null && piece.getColor() == this.getColor() && piece instanceof Rook) {
                    if (((Rook) piece).isFirstTurn()) {

                        //cPos+2 = Queenside
                        if (piece.getId().getX() == 0) {
                            if ((0xEL & allPieces) == 0 && ChessColor.WHITE == this.getColor())
                                validPositions.add(new Position(2, 0));
                            else if ((0xE00000000000000L & allPieces) == 0 && ChessColor.BLACK == this.getColor())
                                validPositions.add(new Position(2, 7));
                        }

                        //cPos-2 = Kingside
                        else if (piece.getId().getX() == 7) {
                            if ((0x60L & allPieces) == 0 && ChessColor.WHITE == this.getColor())
                                validPositions.add(new Position(6, 0));
                            else if ((0x6000000000000000L & allPieces) == 0 && ChessColor.BLACK == this.getColor())
                                validPositions.add(new Position(6, 7));
                        }
                    }
                }
            }
        }
        return validPositions.toArray(Position[]::new);
    }

    private long kingMovesLookUp(long kingPos) {
        // super complex hardcore bithacks! :)
        long bitboardKing = 1L << kingPos;
        long l1 = (bitboardKing >>> 1) & 0x7f7f7f7f7f7f7f7fL;
        long r1 = (bitboardKing << 1) & 0xfefefefefefefefeL;
        long h1 = l1 | r1;
        return (h1 << 8) | (h1 >>> 8) | h1 | (bitboardKing >>> 8) | (bitboardKing << 8);
    }

    private Position[] getNoCheckPositions(){
        ArrayList<Position> validPositions = new ArrayList<>();
        Position[] possiblePositions = this.getValidPositions();
        long enemyMoves = Game.getInstance().getBoard().getAllPossibleMovesBoard(this.getColor().getOpposite());
        for(Position pos : possiblePositions){
            int nPos = pos.getY() * 8 + pos.getX();
            if(((enemyMoves >> nPos) & 1) == 0){
                validPositions.add(pos);
            }
        }
        return validPositions.toArray(Position[]::new);
    }

    public boolean isCheck(){
        long enemyMoves = Game.getInstance().getBoard().getAllPossibleMovesBoard(this.getColor().getOpposite());
        int cPos = this.getCurrentPosition().getY() * 8 + this.getCurrentPosition().getX();
        return (enemyMoves & cPos) > 0;
    }


    public boolean isDraw(){
        //return this.getNoCheckPositions().length != 0 && !isCheck();

        // get enemies that threaten the king / can they be destroyed? -> no
        // get positions of directions from which king is threatened. Can allie step on these positions & attacker is no knight -> no
        return false;
    }


    public boolean isCheckmate(){
        //TODO: Implement
        return false;
    }
}
