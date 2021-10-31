package com.github.ojanka.javachess.game.pieces;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.ArrayList;

public class King extends Piece {

    private boolean firstTurn = true;            // used for castling

    public void setFirstTurn(){
        this.firstTurn = false;
    }

    public King(int startX, int startY, ChessColor color) {
        super(startX, startY, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Position[] getValidPositions() {
        long bitboard = Game.getInstance().getBoard().getAlliesBitmap();
        long allPieces = bitboard | Game.getInstance().getBoard().getEnemiesBitmap();
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

        //FIXME: DEBUG ME BIG DADDY
        long test = cPos;
        if (this.firstTurn) {
            for (Piece piece : Game.getInstance().getBoard().getPieces()) {
                if (piece != null && piece.getColor() == this.getColor() && piece instanceof Rook) {
                    if (((Rook) piece).isFirstTurn()) {

                        //cPos+2 = Queenside
                        if (piece.getId().getX() == 0) {
                            if ((14L & allPieces) == 0)
                                validPositions.add(new Position(1, 0));
                            else if ((1008806316530991104L & allPieces) == 0)
                                validPositions.add(new Position(1, 7));
                        }

                        //cPos-2 = Kingside
                        else if (piece.getId().getX() == 7) {
                            if ((96L & allPieces) == 0)
                                validPositions.add(new Position(6, 0));
                            else if ((6917529027641081856L & allPieces) == 0)
                                validPositions.add(new Position(6, 7));
                        }
                    }
                }
            }
        }
        return validPositions.toArray(Position[]::new);
    }

    // TODO: Implement Check checker :)
    public boolean isCheck() {
        return false;
    }

    // TODO: Implement Checkmate checker
    public boolean isCheckmate() {
        return false;
    }

    // TODO: Implement Draw (Player can't move any piece or King is Checkmate) checker
    public boolean isDraw() {
        return false;
    }

    private long kingMovesLookUp(long kingPos) {
        // super complex hardcore bithacks! :)
        long bitboardKing = 1L << kingPos;
        long l1 = (bitboardKing >>> 1) & 0x7f7f7f7f7f7f7f7fL;
        long r1 = (bitboardKing << 1) & 0xfefefefefefefefeL;
        long h1 = l1 | r1;
        return (h1 << 8) | (h1 >>> 8) | h1 | (bitboardKing >>> 8) | (bitboardKing << 8);
    }

}
