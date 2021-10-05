package com.github.ojanka.javachess.logger;

import com.github.ojanka.javachess.game.Game;
import com.github.ojanka.javachess.game.Piece;
import com.github.ojanka.javachess.game.pieces.King;
import com.github.ojanka.javachess.util.ChessColor;
import com.github.ojanka.javachess.util.Position;

import java.util.HashMap;

public class EventLogger {
    private static EventLogger instance = null;
    private int index = 1;
    private Piece[][] lastboard;

    private void updateLastBoard(){
        this.lastboard = Game.getInstance().getBoard().getPlayingField();
        System.out.println("updated last board");
    }

    public EventLogger(){
        updateLastBoard();
    }

    public static EventLogger getInstance(){
        if(instance == null){
            instance = new EventLogger();
        }
        return instance;
    }

    private void fileOutput(){

    }

    private void fileInput(){

    }

    public void log(Piece p){
        // Vars
        Position movedTo = p.getCurrentPosition();
        Position originalPos = null;
        for(int y = 0; y < this.lastboard.length; y++){
            for(int x = 0; x < this.lastboard[y].length; x++){
                if(this.lastboard[y][x] == null){ continue; }
                else if(this.lastboard[y][x].getId() == p.getId()){
                    originalPos = new Position(x, y); break;
                }
            }
        }
        // need old position
        String pieceClass = p.getClass().getSimpleName();

        // Get Kill
        String kill = "";
        if(this.lastboard[movedTo.getY()][movedTo.getX()] != null){
            if(pieceClass.equals("Pawn")){
                kill = ""; // last X-Position
            }
            kill += "x";
        }

        // Get Prefix
        String piecePrefix = pieceClass.substring(0,1).toUpperCase();
        // Exceptions
        if(pieceClass.equals("Knight")) { piecePrefix = "N"; }
        else if(pieceClass.equals("Pawn")) { piecePrefix = ""; }

        // Get specials
        String specials = "";
        King enemyKing = null;
        // Get enemy king
        for(Piece piece : Game.getInstance().getBoard().getPieces()){
            if(piece.getClass().getSimpleName().equals("King") && piece.getColor() == ChessColor.getOpposite(p.getColor())){
                enemyKing = (King) piece; // DO NOT TRY AT HOME
                break;
            }
        }
        assert enemyKing != null;
        if(enemyKing.isCheck()){ specials = "+"; }                 // Other Team's king is Check
        else if (enemyKing.isCheckmate()){ specials = "#"; }       // Other Team's king is Checkmate

        // Build final log
        String log = "";
        // King castling case.
        assert originalPos != null;     // Always the case ... (IntelliJ is stupid sry)
        if(pieceClass.equals("King") && Math.abs(movedTo.getX()-originalPos.getX()) > 1){
            if(movedTo.getX() == 5){ log += this.index+". 0-0-0"; }            // King side castling
            else if (movedTo.getX() == 1){ log += this.index+". 0-0"; }    // Queen side castling
        }
        else{
            log =
                this.index + ". "
                + piecePrefix + kill
                + translatePositionXToNotation(movedTo) + translatePositionYToNotation(movedTo)
                + specials;
        }
        // Game End
        if(specials.equals("#")){
            if(p.getColor() == ChessColor.WHITE){
                log += "\n" +(this.index+1)+ ". 1-0";
            }
            else{
                log += "\n" +(this.index+1)+ ". 0-1";
            }
        }
        else if(enemyKing.isDraw()){
            log += "\n" +(this.index+1)+ ". 1/2-1/2";
        }

        // TESTING
        System.out.println(log);

        // do stuff
        // Update attributes
        updateLastBoard();
        this.index++;
    }

    private String translatePositionXToNotation(Position pos){
        HashMap<Integer, String> dict = new HashMap<>(){{
            put(0, "a");
            put(1, "b");
            put(2, "c");
            put(3, "d");
            put(4, "e");
            put(5, "f");
            put(6, "g");
            put(7, "h");
        }};
        return dict.get(pos.getX());
    }
    private String translatePositionYToNotation(Position pos){
        return String.valueOf(pos.getY()+1);
    }
}
