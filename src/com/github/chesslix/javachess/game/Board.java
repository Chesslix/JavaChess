package com.github.chesslix.javachess.game;

import javax.xml.validation.TypeInfoProvider;

import com.github.chesslix.javachess.game.pieces.King;
import com.github.chesslix.javachess.logger.EventLogger;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.Position;

import java.util.Objects;

public class Board {
    /**
     * Array with all Pieces. Killed pieces are replaced with null
     */
    private long whiteBitmap;
    private long blackBitmap;
    private Piece[] pieces;
    private Piece[][] playingField;

    public long getAlliesBitmap(ChessColor color) {
        if(color == ChessColor.WHITE) return whiteBitmap;
        else return blackBitmap;
    }

    public long getEnemiesBitmap(ChessColor color) {
        if(color == ChessColor.BLACK) return whiteBitmap;
        else return blackBitmap;
    }

    public void setWhiteBitmap() {
        this.whiteBitmap = getAsBitmapByColor(ChessColor.WHITE);
    }

    public void setBlackBitmap() {
        this.blackBitmap = getAsBitmapByColor(ChessColor.BLACK);
    }

    public Board(Piece[] pieces) {
        this.pieces = pieces;
        //setPlayingField();
        setBitmaps();
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

    public King getKing(ChessColor color){
        for(Piece piece : this.pieces){
            if (piece == null) continue;
            if (piece.getColor() == color && piece instanceof King){
                return (King) piece;
            }
        }
        return null;
    }
	
	public Piece getPieceByStartPos(int startPosX, int startPosY) {
		for (Piece piece : pieces) {
			if (piece == null) continue;
			if (piece.getId().equals(startPosX, startPosY)) return piece;
		}
		return null;
	}
	
	/**
	 * Removes the piece from the board
	 * @param toKill
	 */
	public void killPiece(Piece toKill) {
		for (int i = 0; i < pieces.length; i++) {
			if (pieces[i] == toKill) pieces[i] = null;
            //setPlayingField();
		}
	}
	
	/**
	 * Moves the piece to the given position and kills every other piece on the same position, no matter in which team it is (to prevent bugs). Position validation has to be done before that method.
	 * @param toMove
	 * @param x
	 * @param y
	 */
	public void movePiece(Piece toMove, int x, int y) {
		if(getPlayingField()[y][x] != null){
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
        Position oldPos = new Position(toMove.getCurrentPosition().getX(), toMove.getCurrentPosition().getY());
        toMove.setCurrentPosition(x, y);
        this.setBitmaps();
        EventLogger.getInstance().log(toMove, oldPos);
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

    public long getAsBitmapByColor(ChessColor pieceColor) {
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

    private void setBitmaps(){
        setPlayingField();
        setWhiteBitmap();
        setBlackBitmap();
    }

    // OMG what a funking bad and very long code I just wrote ¦ - nino -> true mate
    public long getAllPossibleMovesBoard(ChessColor pieceColor){
        long bitmap = 0L;
        for (Piece piece : this.pieces) {
            if (piece != null){
                if (piece.getColor() == pieceColor){
                    Position[] piecePositions = piece.getValidPositions();
                    for (Position pos : piecePositions){
                        int x = pos.getX();
                        int y = pos.getY();
                        bitmap |= 1L << (8 * y + x);
                    }
                }
            }
        }
        return bitmap;
    }
}
