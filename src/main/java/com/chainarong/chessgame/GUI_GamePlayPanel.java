package com.chainarong.chessgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class GUI_GamePlayPanel extends JPanel {

    private Board board;
    private JButton back;
    private JPanel playerPanel;
    private JLabel p1;
    private JLabel p2;
    private JButton p1t;
    private JButton p2t;
    private JLabel alertText;

    private GridBagConstraints gridBagConstraints;

    public GUI_GamePlayPanel() {

        this.board = new Board(new Color(255, 206, 158), new Color(209, 139, 71));
        this.back = new JButton();
        this.playerPanel = new JPanel();
        this.p1 = new JLabel();
        this.p2 = new JLabel();
        this.p1t = new JButton();
        this.p2t = new JButton();
        this.alertText = new JLabel();

        this.back.setFont(new Font(this.back.getFont().getName(), 0, 24));
        this.back.setText("Back");
        this.back.setFocusPainted(false);
        this.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(ChessGame.getGui(), "ต้องการออกจากเกมใช่หรือไม่", "ยืนยัน", JOptionPane.YES_NO_OPTION);
                
                if (option == 0) {
                    ChessGame.getGui().showMainMenuPanel();
                    ChessGame.gameEnd();
                }
            }
        });

        this.playerPanel.setPreferredSize(new Dimension(200, 800));
        this.playerPanel.setBackground(Color.CYAN);
        GridBagLayout playerPanelLayout = new GridBagLayout();
        playerPanelLayout.rowHeights = new int[] { 100, 100, 100, 100, 100 };
        this.playerPanel.setLayout(playerPanelLayout);

        this.p1.setFont(new Font(this.p1.getFont().getName(), 0, 24));
        this.p1.setText(ChessGame.getData().getSettingData().getPlayer1Name());
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 0;
        this.playerPanel.add(this.p1, this.gridBagConstraints);

        this.p2.setFont(new Font(this.p2.getFont().getName(), 0, 24));
        this.p2.setText(ChessGame.getData().getSettingData().getPlayer2Name());
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 4;
        this.playerPanel.add(this.p2, this.gridBagConstraints);

        this.p1t.setFont(new Font(this.p1t.getFont().getName(), 0, 24));
        this.p1t.setBackground(Color.WHITE);
        this.p1t.setPreferredSize(new Dimension(120, 60));
        this.p1t.setText("00.00");
        this.p1t.setBorderPainted(false);
        this.p1t.setFocusPainted(false);
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 1;
        this.playerPanel.add(this.p1t, this.gridBagConstraints);

        this.p2t.setFont(new Font(this.p2t.getFont().getName(), 0, 24));
        this.p2t.setBackground(Color.WHITE);
        this.p2t.setPreferredSize(new Dimension(120, 60));
        this.p2t.setText("00.00");
        this.p2t.setBorderPainted(false);
        this.p2t.setFocusPainted(false);
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 3;
        this.playerPanel.add(this.p2t, this.gridBagConstraints);

        this.alertText.setFont(new Font(this.alertText.getFont().getName(), 0, 24));
        this.alertText.setText("");
        this.alertText.setForeground(Color.RED);
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 2;
        this.playerPanel.add(this.alertText, this.gridBagConstraints);

        this.setLayout(new GridBagLayout());

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 0;
        this.gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        this.add(this.back, this.gridBagConstraints);

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 1;
        this.gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        this.add(this.board, this.gridBagConstraints);

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 1;
        this.gridBagConstraints.gridy = 1;
        this.gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        this.add(this.playerPanel, this.gridBagConstraints);

    }

    public Board getBoard() {
        return this.board;
    }

    public void updateTimer() {

        int black = ChessGame.getBlackSecondsRemaining() / 10;
        int white = ChessGame.getWhiteSecondsRemaining() / 10;
        Team team = ChessGame.getTurn();

        this.p1t.setText(black / 60 + ":" + black % 60);
        this.p1t.setEnabled(team == Team.BLACK);
        this.p2t.setText(white / 60 + ":" + white % 60);
        this.p2t.setEnabled(team == Team.WHITE);
    }

    public void updateAlertText(String text) {
        this.alertText.setText(text);
        this.alertText.revalidate();
    };

}
