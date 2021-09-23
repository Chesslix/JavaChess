package com.github.ojanka.javachess.game;

import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

public class Board {
    /**
     * Array with all Pieces. Killed pieces are replaced with null
     */
    private Piece[] pieces;
    private Piece[][] playingField;

    public Board(Piece[] pieces) {
        this.pieces = pieces;
        setPlayingField();
    }

    /**
     * Returns the piece at the given position.
     *
     * @param x
     * @param y
     * @return the Piece or null if there is no piece at this position
     */
    public Piece getPiece(int x, int y) {
        return getPlayingField()[y][x];
		/*
		for (Piece piece : pieces) {
			if (piece == null) continue;
			if (piece.getCurrentPosition().equals(x, y)) return piece;
		}
		return null;
		 */
    }

    /**
     * Removes the piece from the board
     *
     * @param toKill
     */
    public void killPiece(Piece toKill) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] == toKill) pieces[i] = null;
        }
    }

    /**
     * Moves the piece to the given position and kills every other piece on the same position, no matter in which team it is (to prevent bugs). Position validation has to be done before that method.
     *
     * @param toMove
     * @param x
     * @param y
     */
    public void movePiece(Piece toMove, int x, int y) {
        if (getPlayingField()[y][x] != null) {
            killPiece(getPlayingField()[y][x]);
        }
		/*
		for (int i = 0; i < pieces.length; i++) {
			if (pieces[i] == null) continue;
			if (pieces[i].getCurrentPosition().equals(new Position(x, y))) {
				killPiece(pieces[i]);
			}
		}
		 */
        toMove.setCurrentPosition(x, y);
        setPlayingField();
    }

    /**
     * Returns a 8x8 two-dimensional-array which contains all Figures at their positions.
     *
     * @return Piece[xPos][yPos]
     */
    public void setPlayingField() {
        Piece[][] board = new Piece[8][8];
        // Game.getInstance().getBoard().getPieces()
        for (Piece piece : this.pieces) {
            if (piece != null)
                board[piece.getCurrentPosition().getY()][piece.getCurrentPosition().getX()] = piece;
        }
        this.playingField = board;
    }

    public Piece[][] getPlayingField() {
        return this.playingField;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public long getAlliesAsBitmap(ChessColor pieceColor) {
        long bitmap = 0L;
        for (Piece piece : this.pieces) {
            if (piece == null) continue;
            if (piece.getColor() == pieceColor) {
                int x = piece.getCurrentPosition().getX();
                int y = piece.getCurrentPosition().getY();
                bitmap |= 1L << (x + 8 * y);
            }
        }
        return bitmap;
    }

    public long getEnemiesAsBitmap(ChessColor pieceColor) {
        long bitmap = 0L;
        for (Piece piece : this.pieces) {
            if (piece == null) continue;
            if (piece.getColor() != pieceColor) {
                int x = piece.getCurrentPosition().getX();
                int y = piece.getCurrentPosition().getY();
                bitmap |= 1L << (x + 8 * y);
            }
        }
        return bitmap;
    }
}
