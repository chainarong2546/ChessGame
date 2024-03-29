package com.chainarong.chessgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class DataHandler {

    private final String dataDirectoryName = "ChessGame_Data";
    private final String historyDirectoryName = "history";
    private final String settingFileName = "setting.json";
    private SettingData data;
    private String moveHistory;

    public DataHandler() {
        this.data = new SettingData();
        this.moveHistory = "";

        Path dataPath = Paths.get(this.dataDirectoryName);
        Path historyPath = dataPath.resolve(this.historyDirectoryName);
        Path settingFile = dataPath.resolve(this.settingFileName);
        String content = "";

        try {
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }
            if (!Files.exists(historyPath)) {
                Files.createDirectories(historyPath);
            }
            if (!Files.exists(settingFile)) {
                InputStream inputStream = getClass().getResourceAsStream("/defaultSetting.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    content = content + line + "\n";
                }
                reader.close();
                Files.writeString(settingFile, content);
            } else {
                content = Files.readString(settingFile);
            }

            this.data = this.jsonToSettingData(content);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(ChessGame.getGui(), "เกิดข้อผิดพลาดในการจัดการไฟล์หรือโฟเดอร์:\n" + e.getMessage(), "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
        }

    }

    private SettingData jsonToSettingData(String content) {
        Gson gson = new Gson();
        SettingData data = gson.fromJson(content, SettingData.class);
        return data;
    }

    private String settingDataToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(this.data);
        return content;
    }

    public SettingData getSettingData() {
        return this.data;
    }

    public void setSettingData(String player1Name, String player2Name, int player1Time, int player2Time) {
        this.data = new SettingData(player1Name, player2Name, player1Time, player2Time);
        String content = settingDataToJson();

        Path dataPath = Paths.get(this.dataDirectoryName);
        Path historyPath = dataPath.resolve(this.historyDirectoryName);
        Path settingFile = dataPath.resolve(this.settingFileName);

        try {
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }
            if (!Files.exists(historyPath)) {
                Files.createDirectories(historyPath);
            }
            Files.writeString(settingFile, content);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(ChessGame.getGui(), "เกิดข้อผิดพลาดในการจัดการไฟล์หรือโฟเดอร์:\n" + e.getMessage(), "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addMoveHistory(Position p1, Position p2) {
        String x = "move   " + p1.getRow() + ":" + p1.getCol() + "   to   " + p2.getRow() + ":" + p2.getCol() + "\n";
        this.moveHistory = this.moveHistory + x;
    }

    public void saveMoveHistory() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        Path dataPath = Paths.get(this.dataDirectoryName);
        Path historyPath = dataPath.resolve(this.historyDirectoryName);
        Path historyFile = historyPath.resolve(dtf.format(now));

        try {
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
            }
            if (!Files.exists(historyPath)) {
                Files.createDirectories(historyPath);
            }
            Files.writeString(historyFile, this.moveHistory);
            this.moveHistory = "";

        } catch (IOException e) {
            JOptionPane.showMessageDialog(ChessGame.getGui(), "เกิดข้อผิดพลาดในการจัดการไฟล์หรือโฟเดอร์:\n" + e.getMessage(), "เกิดข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
        }
    }

}
