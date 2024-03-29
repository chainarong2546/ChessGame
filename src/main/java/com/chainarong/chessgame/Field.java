package com.chainarong.chessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class Field extends JButton {

    private Piece piece;
    private Position position;
    private Color color;
    private Color highLightColor;
    private boolean showHighLight;
    private Field showHighLightBy;

    public Field(int row, int col, Color color, Color highLightColor) {
        this.piece = null;
        this.position = new Position(row, col);
        this.color = color;
        this.highLightColor = highLightColor;
        this.showHighLight = false;
        this.showHighLightBy = null;

        this.setPreferredSize(new Dimension(100, 100));
        this.setMinimumSize(new Dimension(100, 100));
        this.setSize(100, 100);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBackground(color);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMouseClick();
            }
        });
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean getShowHighLight() {
        return this.showHighLight;
    }

    public void setShowHighLight(boolean show, Field by) {
        this.showHighLight = show;
        this.showHighLightBy = show ? by : null;
        this.setBackground(show ? this.highLightColor : this.color);
    }

    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
        if (piece != null) {
            URL imageUrl = getClass().getResource(piece.getImage());
            if (imageUrl != null) {
                this.setIcon(new ImageIcon(imageUrl));
            }
        } else {
            this.setIcon(null);
        }

    }

    public void onMouseClick() {
        if (ChessGame.getIsPlaying()) {
            Board board = ChessGame.getGui().getGamePlayPanel().getBoard();
            if (this.showHighLight) {
                if ((this.showHighLightBy.getPiece() != null)
                        && (this.showHighLightBy.getPiece().getClass().getSimpleName().equals("Piece_King"))
                        && (this.getPiece() != null)
                        && (this.getPiece().getClass().getSimpleName().equals("Piece_Rook"))) {
                    if (this.getPosition().getCol() == 0) {
                        board.move(this.showHighLightBy, ChessGame.getGui().getGamePlayPanel().getBoard()
                                .getField(new Position(this.getPosition().getRow(), 2)));
                        board.move(this, ChessGame.getGui().getGamePlayPanel().getBoard()
                                .getField(new Position(this.getPosition().getRow(), 3)));
                    } else if (this.getPosition().getCol() == 7) {
                        board.move(this.showHighLightBy, ChessGame.getGui().getGamePlayPanel().getBoard()
                                .getField(new Position(this.getPosition().getRow(), 6)));
                        board.move(this, ChessGame.getGui().getGamePlayPanel().getBoard()
                                .getField(new Position(this.getPosition().getRow(), 5)));
                    }
                    board.setShowHighLight(false, this);
                } else {

                    if ((this.getPiece() == null) && (this.showHighLightBy.getPiece() != null)
                            && (this.showHighLightBy.getPiece().getClass().getSimpleName().equals("Piece_Pawn"))
                            && (this.showHighLightBy.getPiece().getMoveCount() > 1)
                            && (board.getPiece(new Position(this.showHighLightBy.getPosition().getRow(),
                                    this.getPosition().getCol())) != null)
                            && (board.getPiece(new Position(this.showHighLightBy.getPosition().getRow(),
                                    this.getPosition().getCol())).getClass().getSimpleName().equals("Piece_Pawn"))
                            && (board.getPiece(new Position(this.showHighLightBy.getPosition().getRow(),
                                    this.getPosition().getCol())).getMoveCount() == 1)) {
                        board.move(this.showHighLightBy, this);
                        board.getField(new Position(this.showHighLightBy.getPosition().getRow(), this.getPosition().getCol()))
                                .setPiece(null);
                                
                        board.setShowHighLight(false, this);
                    } else {
                        board.move(this.showHighLightBy, this);
                        board.setShowHighLight(false, this);
                    }
                }
                ChessGame.switchTurn();
                board.setCanMoveAll();
            } else {
                board.setShowHighLight(true, this);
            }
        }
    }

}