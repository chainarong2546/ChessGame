package com.chainarong.chessgame;

class SettingData {
    private String player1Name;
    private String player2Name;
    private int player1Time;
    private int player2Time;

    public SettingData() {};

    public SettingData(String player1Name, String player2Name, int player1Time, int player2Time) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Time = player1Time;
        this.player2Time = player2Time;
    }

    public String getPlayer1Name() {
        return this.player1Name;
    }

    public String getPlayer2Name() {
        return this.player2Name;
    }

    public int getPlayer1Time() {
        return this.player1Time;
    }

    public int getPlayer2Time() {
        return this.player2Time;
    }


}