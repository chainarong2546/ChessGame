package com.chainarong.chessgame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class GUI_HistoryPanel extends JPanel {

    private JButton back;
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane scrollPanel1;
    private JScrollPane scrollPanel2;
    private JList<String> list1;
    private JList<String> list2;
    private DefaultListModel<String> list1Model;
    private DefaultListModel<String> list2Model;

    private GridBagConstraints gridBagConstraints;

    public GUI_HistoryPanel() {
        this.back = new JButton();
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.scrollPanel1 = new JScrollPane();
        this.scrollPanel2 = new JScrollPane();
        this.list1 = new JList<>();
        this.list2 = new JList<>();
        this.list1Model = new DefaultListModel<>();
        this.list2Model = new DefaultListModel<>();

        this.list1.setModel(list1Model);
        this.list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedFile = list1.getSelectedValue();
                    displayFileContent(selectedFile);
                }
            }
        });
        this.scrollPanel1.setViewportView(this.list1);

        this.list2.setModel(list2Model);
        this.scrollPanel2.setViewportView(this.list2);

        this.panel2.setLayout(new GridLayout(1, 2, 20, 20));
        this.panel2.setPreferredSize(new Dimension(400, 600));
        this.panel2.add(this.scrollPanel1);
        this.panel2.add(this.scrollPanel2);

        this.back.setText("Back");
        this.back.setFont(new Font(this.back.getFont().getName(), 0, 18));
        this.back.setFocusPainted(false);
        this.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame.getGui().showMainMenuPanel();
            }
        });

        this.panel1.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        this.panel1.add(back, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        this.panel1.add(panel2, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        this.setLayout(new GridBagLayout());
        this.add(this.panel1, gridBagConstraints);
    }

    public void loadFileList() {
        File directory = new File("./ChessGame_Data/history");
        this.list1Model.clear();
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        this.list1Model.addElement(file.getName());
                    }
                }
            }
        }
    }

    private void displayFileContent(String filename) {
        this.list2Model.clear();
        if (filename != null && !filename.isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("./ChessGame_Data/history/" + filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    this.list2Model.addElement(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
