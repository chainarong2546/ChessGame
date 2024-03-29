package com.chainarong.chessgame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class GUI_MainMenuPanel extends JPanel {

    private JPanel panel1;
    private JPanel panel2;
    private JButton playButton;
    private JButton historyButton;
    private JButton settingButton;

    public GUI_MainMenuPanel() {
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.playButton = new JButton();
        this.historyButton = new JButton();
        this.settingButton = new JButton();

        this.panel1.setPreferredSize(new Dimension(400, 200));
        this.panel1.setLayout(new GridLayout(2, 1, 10, 10));
        this.panel2.setLayout(new GridLayout(1, 2, 10, 10));

        this.playButton.setFont(new Font(this.playButton.getFont().getName(), 0, 36));
        this.playButton.setText("Play");
        this.playButton.setFocusPainted(false);
        this.playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame.getGui().showGamePlayPanel();
            }
        });
        this.panel1.add(this.playButton);

        this.settingButton.setFont(new Font(this.settingButton.getFont().getName(), 0, 24));
        this.settingButton.setText("Setting");
        this.settingButton.setFocusPainted(false);
        this.settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame.getGui().showSettngPanel();
            }
        });
        this.panel2.add(this.settingButton);

        this.historyButton.setFont(new Font(this.historyButton.getFont().getName(), 0, 24));
        this.historyButton.setText("History");
        this.historyButton.setFocusPainted(false);
        this.historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame.getGui().showHistoryPanel();
            }
        });
        this.panel2.add(this.historyButton);

        this.panel1.add(this.panel2);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(100, 100, 100, 100);
        this.setLayout(new GridBagLayout());
        this.add(this.panel1, gridBagConstraints);
    }

}
