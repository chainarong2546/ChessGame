package com.chainarong.chessgame;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

class GUI_Control extends JFrame {

    private GUI_MainMenuPanel mainMenuPanel;
    private GUI_GamePlayPanel gamePlayPanel;
    private GUI_HistoryPanel historyPanel;
    private GUI_SettngPanel settngPanel;

    public GUI_Control() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ตั้งค่าธีมไม่สำเร็จ", JOptionPane.ERROR_MESSAGE);
        }
        
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 16);
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, thaiFont);
            }
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Chess Game V.1");
        this.setVisible(true);
    }

    public GUI_MainMenuPanel getMainMenuPanel() {
        return this.mainMenuPanel;
    }

    public GUI_GamePlayPanel getGamePlayPanel() {
        return this.gamePlayPanel;
    }

    public GUI_HistoryPanel getHistoryPanel() {
        return this.historyPanel;
    }

    public GUI_SettngPanel getSettngPanel() {
        return this.settngPanel;
    }

    public void showMainMenuPanel() {
        if (this.mainMenuPanel == null) {
            this.mainMenuPanel = new GUI_MainMenuPanel();
        }

        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(this.mainMenuPanel);
        this.revalidate();

        this.setMinimumSize(new Dimension());
        this.pack();
        this.setMinimumSize(this.getSize());
    }

    public void showGamePlayPanel() {
        this.gamePlayPanel = new GUI_GamePlayPanel();

        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(this.gamePlayPanel);
        this.revalidate();

        this.setMinimumSize(new Dimension());
        this.pack();
        this.setMinimumSize(this.getSize());

        ChessGame.gameStart();
    }

    public void showSettngPanel() {
        if (this.settngPanel == null) {
            this.settngPanel = new GUI_SettngPanel();
        }

        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(this.settngPanel);
        this.revalidate();

        this.setMinimumSize(new Dimension());
        this.pack();
        this.setMinimumSize(this.getSize());
    }

    public void showHistoryPanel() {
        if (this.historyPanel == null) {
            this.historyPanel = new GUI_HistoryPanel();
        }
        this.historyPanel.loadFileList();

        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(this.historyPanel);
        this.revalidate();

        this.setMinimumSize(new Dimension());
        this.pack();
        this.setMinimumSize(this.getSize());
    }

}
