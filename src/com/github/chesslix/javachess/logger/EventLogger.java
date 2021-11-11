package com.github.chesslix.javachess.logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.game.Piece;
import com.github.chesslix.javachess.game.pieces.King;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.Position;


/**
 * Singleton!
 * Event Logger is responsible for the reading and writing of the
 * Logs. It is activated by the Board after a piece was moved.
 *
 * @version 1.0
 * @author David Abderhalden
 */
public class EventLogger {
    private static EventLogger instance = null;
    // path to the logger (instanced once in game)
    private String filePath = null;
    private int index = 1;
    private Piece[][] lastboard;


    private void updateLastBoard(){
        this.lastboard = Arrays.stream(Game.getInstance().getBoard().getPlayingField()).map(Piece[]::clone).toArray(Piece[][]::new);
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

    /**
     * Creates a Buffered File Stream which saves the log.
     * @param log Log to save
     */
    private void fileOutput(String log) {
        try{
            if(this.filePath == null){
                this.filePath = "data/game_"+new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date())+".log";
            }
            File file = new File(this.filePath);
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(file,true), StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(log+"\t"+new Timestamp(System.currentTimeMillis())+"\tChesslix-JavaChess Inc."+"\r\n");
            // Maybe keep the stream running and close it on program close
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Reads a log file
     * @param path the path to the log file
     * @return Content of log file as String
     */
    private String fileInput(String path){
        StringBuilder fileContent = new StringBuilder();
        try{
            File file = new File(path);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()){
                fileContent.append(bufferedReader.readLine()).append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    /**
     * Converts Piece and old position in Chess Notation and writes it
     * to the log
     * @param p The piece that was moved
     * @param oldPos The last position of the piece
     */
    public void log(Piece p, Position oldPos){
        // Vars
        System.out.println(p.getCurrentPosition().getY()+" "+oldPos.getY());
        Position movedTo = p.getCurrentPosition();
        /*
        Position originalPos = null;
        for(int y = 0; y < this.lastboard.length; y++){
            for(int x = 0; x < this.lastboard[y].length; x++){
                if(this.lastboard[y][x] == null){ continue; }
                else if(this.lastboard[y][x].getId() == p.getId()){
                    originalPos = new Position(x, y); break;
                }
            }
        }
        */
        // need old position
        String pieceClass = p.getClass().getSimpleName();

        // Get Kill
        String kill = "";
        if(this.lastboard[movedTo.getY()][movedTo.getX()] != null){
            if(pieceClass.equals("Pawn")){
                kill = translatePositionXToNotation(oldPos);
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
        // Get enemy king
        King enemyKing = Game.getInstance().getBoard().getKing(ChessColor.getOpposite(p.getColor()));

        if(enemyKing.isCheck()){ specials = "+"; }                 // Other Team's king is Check
        else if (enemyKing.isCheckmate()){ specials = "#"; }       // Other Team's king is Checkmate

        // Build final log
        String log = "";
        // King castling case.
        assert oldPos != null;     // Always the case ... (IntelliJ is stupid sry)
        if(pieceClass.equals("King") && Math.abs(movedTo.getX()-oldPos.getX()) > 1){
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

        // Write to file
        fileOutput(log);

        // Update attributes
        updateLastBoard();
        this.index++;
    }

    /**
     * Translates the X coordinate of the board to the real Chess Board Site
     * @param pos the Position to translate
     * @return parsed Position as String
     */
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

    /**
     * Translates the X coordinate of the board to the real Chess Board Site
     * @param pos the Position to translate
     * @return parsed Position as String
     */
    private String translatePositionYToNotation(Position pos){
        return String.valueOf(pos.getY()+1);
    }
}
