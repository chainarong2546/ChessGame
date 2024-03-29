package com.chainarong.chessgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ChessGame {

    private static GUI_Control gui = new GUI_Control();
    private static DataHandler data = new DataHandler();
    private static Team turn = Team.WHITE;
    private static Timer blackTimer = null;
    private static Timer whiteTimer = null;
    private static int blackSecondsRemaining = 0;
    private static int whiteSecondsRemaining = 0;
    private static boolean isPlaying = false;

    public static GUI_Control getGui() {
        return ChessGame.gui;
    }

    public static DataHandler getData() {
        return ChessGame.data;
    }

    public static Team getTurn() {
        return ChessGame.turn;
    }

    public static int getBlackSecondsRemaining() {
        return ChessGame.blackSecondsRemaining;
    }

    public static int getWhiteSecondsRemaining() {
        return ChessGame.whiteSecondsRemaining;
    }

    public static boolean getIsPlaying() {
        return ChessGame.isPlaying;
    }

    public static void gameStart() {
        ChessGame.turn = Team.WHITE;
        ChessGame.isPlaying = true;
        ChessGame.blackSecondsRemaining = data.getSettingData().getPlayer1Time();
        ChessGame.whiteSecondsRemaining = data.getSettingData().getPlayer2Time();
        ChessGame.blackTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame.blackSecondsRemaining--;
                ChessGame.gui.getGamePlayPanel().updateTimer();
                gui.getGamePlayPanel().getBoard().checkWin();
            }
        });
        ChessGame.whiteTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame.whiteSecondsRemaining--;
                ChessGame.gui.getGamePlayPanel().updateTimer();
                gui.getGamePlayPanel().getBoard().checkWin();
            }
        });

        ChessGame.gui.getGamePlayPanel().getBoard().setBoard();
        ChessGame.gui.getGamePlayPanel().getBoard().setCanMoveAll();
        ChessGame.whiteTimer.start();
    }

    public static void gameEnd() {
        if (ChessGame.blackTimer != null) {
            ChessGame.blackTimer.stop();
            ChessGame.blackTimer = null;
        }
        if (ChessGame.whiteTimer != null) {
            ChessGame.whiteTimer.stop();
            ChessGame.whiteTimer = null;
        }
        if (ChessGame.isPlaying) {
            ChessGame.isPlaying = false;
            ChessGame.data.saveMoveHistory();
        }
    }

    public static void switchTurn() {
        if (ChessGame.turn == Team.WHITE) {
            ChessGame.turn = Team.BLACK;
            ChessGame.blackTimer.start();
            ChessGame.whiteTimer.stop();
        } else {
            ChessGame.turn = Team.WHITE;
            ChessGame.blackTimer.stop();
            ChessGame.whiteTimer.start();
        }
    }

    public static void main(String[] args) {
        
        gui.showMainMenuPanel();
    }

}
