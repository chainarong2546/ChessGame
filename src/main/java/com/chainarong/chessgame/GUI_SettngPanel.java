package com.chainarong.chessgame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

class GUI_SettngPanel extends JPanel {

    private JPanel panel1;
    private JPanel panel2;
    private JTextField player1NameField;
    private JTextField player2NameField;
    private JTextField player1TimeField;
    private JTextField player2TimeField;
    private NumericFilter numericFilter;
    private GridBagConstraints gridBagConstraints;

    public GUI_SettngPanel() {

        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.numericFilter = new NumericFilter();

        this.panel1.setLayout(new GridBagLayout());
        this.panel2.setLayout(new GridLayout(4, 2, 10, 10));
        this.panel2.setPreferredSize(new Dimension(300, 150));

        JLabel player1NameLabel = new JLabel("Player 1 Name:");
        this.player1NameField = new JTextField();
        this.player1NameField.setText(ChessGame.getData().getSettingData().getPlayer1Name());
        this.panel2.add(player1NameLabel);
        this.panel2.add(player1NameField);

        JLabel player2NameLabel = new JLabel("Player 2 Name:");
        this.player2NameField = new JTextField();
        this.player2NameField.setText(ChessGame.getData().getSettingData().getPlayer2Name());
        this.panel2.add(player2NameLabel);
        this.panel2.add(player2NameField);

        JLabel player1TimeLabel = new JLabel("Player 1 Time:");
        this.player1TimeField = new JTextField();
        ((AbstractDocument) this.player1TimeField.getDocument()).setDocumentFilter(this.numericFilter);
        this.player1TimeField.setText(Integer.toString(ChessGame.getData().getSettingData().getPlayer1Time() / 10));
        this.panel2.add(player1TimeLabel);
        this.panel2.add(player1TimeField);

        JLabel player2TimeLabel = new JLabel("Player 2 Time:");
        this.player2TimeField = new JTextField();
        ((AbstractDocument) this.player2TimeField.getDocument()).setDocumentFilter(this.numericFilter);
        this.player2TimeField.setText(Integer.toString(ChessGame.getData().getSettingData().getPlayer2Time() / 10));
        this.panel2.add(player2TimeLabel);
        this.panel2.add(player2TimeField);

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200, 50));
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 0;
        this.gridBagConstraints.weightx = 1.0;
        this.gridBagConstraints.weighty = 1.0;
        this.panel1.add(panel2, gridBagConstraints);

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 1;
        this.gridBagConstraints.weightx = 0.0;
        this.gridBagConstraints.weighty = 0.0;
        this.gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        this.panel1.add(saveButton, gridBagConstraints);

        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = 0;
        this.gridBagConstraints.weightx = 1.0;
        this.gridBagConstraints.weighty = 1.0;
        this.gridBagConstraints.insets = new Insets(100, 150, 80, 150);
        this.setLayout(new GridBagLayout());
        this.add(panel1, gridBagConstraints);
    }

    private void saveSettings() {
        String player1Name = player1NameField.getText();
        String player2Name = player2NameField.getText();
        int player1Time = Integer.parseInt(player1TimeField.getText()) * 10;
        int player2Time = Integer.parseInt(player2TimeField.getText()) * 10;

        ChessGame.getData().setSettingData(player1Name, player2Name, player1Time, player2Time);
        ChessGame.getGui().showMainMenuPanel();
    }

}
