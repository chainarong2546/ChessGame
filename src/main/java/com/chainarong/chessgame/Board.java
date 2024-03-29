package com.chainarong.chessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class Board extends JPanel {

    private Field[][] fields;
    private Color color1;
    private Color color2;
    private Position lastMoveFrom;
    private Position lastMoveTo;

    public Board(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        this.fields = new Field[8][8];
        this.lastMoveFrom = null;
        this.lastMoveTo = null;

        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(new GridBagLayout());

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                fields[row][col] = new Field(row, col, (row + col) % 2 == 0 ? this.color1 : this.color2, Color.GREEN);
                GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = col;
                gridBagConstraints.gridy = row;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.fill = GridBagConstraints.BOTH;
                this.add(fields[row][col], gridBagConstraints);
            }
        }

    }

    public Field getField(Position p) {
        return fields[p.getRow()][p.getCol()];
    }

    public Piece getPiece(Position p) {
        return getField(p).getPiece();
    }

    public Piece_King getKing(Team team) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(new Position(row, col));
                if (piece != null) {
                    if ((piece.getIsKing()) && (piece.getTeam() == team)) {
                        return (Piece_King) piece;
                    }
                }
            }
        }
        return null;
    }

    public Position getLastMoveFrom() {
        return this.lastMoveFrom;
    }

    public Position getLastMoveTo() {
        return this.lastMoveTo;
    }

    public void setShowHighLight(boolean hightLight, Field by) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pp = new Position(row, col);
                Piece piece = by.getPiece();
                if (piece != null) {
                    if (piece.getTeam() == ChessGame.getTurn()) {
                        getField(pp).setShowHighLight(piece.getCanMovePositions().contains(pp) && hightLight, by);
                    } else {
                        getField(pp).setShowHighLight(false, by);
                    }
                } else {
                    getField(pp).setShowHighLight(false, by);
                }

            }
        }
    }

    public void move(Field f1, Field f2) {
        if (f1.getPiece().move(f2.getPosition())) {
            f2.setPiece(f1.getPiece());
            f1.setPiece(null);
            this.lastMoveFrom = f1.getPosition();
            this.lastMoveTo = f2.getPosition();

            this.checkPawnPromotion(f2);
            ChessGame.getData().addMoveHistory(f1.getPosition(), f2.getPosition());
        }
    }

    public void checkPawnPromotion(Field f) {
        Piece pp = f.getPiece();
        if ((pp != null) && pp.getClass().getSimpleName().equals("Piece_Pawn")) {
            Piece_Pawn piece = (Piece_Pawn) pp;
            if (piece.getPosition().getRow() == (piece.getTeam() == Team.BLACK ? 7 : 0)) {

                Promotion[] options = { Promotion.QUEEN, Promotion.ROOK, Promotion.KNIGHT, Promotion.BISHOP };

                JOptionPane optionPane = new JOptionPane("Choose an option to Promotion", JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.DEFAULT_OPTION, null, options, null);

                JDialog dialog = optionPane.createDialog("Promotion");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);

                Object choice = optionPane.getValue(); // รับค่าที่ผู้ใช้เลือก

                switch (choice) {
                    case Promotion.ROOK:
                        piece.promotion(Promotion.ROOK);
                        break;
                    case Promotion.KNIGHT:
                        piece.promotion(Promotion.KNIGHT);
                        break;
                    case Promotion.BISHOP:
                        piece.promotion(Promotion.BISHOP);
                        break;
                    case Promotion.QUEEN:
                        piece.promotion(Promotion.QUEEN);
                        break;
                    default:
                        piece.promotion(Promotion.QUEEN);
                        break;
                }

            }
        }

    }

    public void setBoard() {
        for (int i = 0; i < 8; i++) {
            fields[1][i].setPiece(new Piece_Pawn("B_Pawn" + i, Team.BLACK, new Position(1, i)));
            fields[6][i].setPiece(new Piece_Pawn("W_Pawn" + i, Team.WHITE, new Position(6, i)));
        }
        fields[0][0].setPiece(new Piece_Rook("B_Rook1", Team.BLACK, new Position(0, 0)));
        fields[0][1].setPiece(new Piece_Knight("B_Knight1", Team.BLACK, new Position(0, 1)));
        fields[0][2].setPiece(new Piece_Bishop("B_Bishop1", Team.BLACK, new Position(0, 2)));
        fields[0][3].setPiece(new Piece_Queen("B_Queen", Team.BLACK, new Position(0, 3)));
        fields[0][4].setPiece(new Piece_King("B_King", Team.BLACK, new Position(0, 4)));
        fields[0][5].setPiece(new Piece_Bishop("B_Bishop2", Team.BLACK, new Position(0, 5)));
        fields[0][6].setPiece(new Piece_Knight("B_Knight2", Team.BLACK, new Position(0, 6)));
        fields[0][7].setPiece(new Piece_Rook("B_Rook2", Team.BLACK, new Position(0, 7)));
        fields[7][0].setPiece(new Piece_Rook("W_Rook1", Team.WHITE, new Position(7, 0)));
        fields[7][1].setPiece(new Piece_Knight("W_Knight1", Team.WHITE, new Position(7, 1)));
        fields[7][2].setPiece(new Piece_Bishop("W_Bishop1", Team.WHITE, new Position(7, 2)));
        fields[7][3].setPiece(new Piece_Queen("W_Queen", Team.WHITE, new Position(7, 3)));
        fields[7][4].setPiece(new Piece_King("W_King", Team.WHITE, new Position(7, 4)));
        fields[7][5].setPiece(new Piece_Bishop("W_Bishop2", Team.WHITE, new Position(7, 5)));
        fields[7][6].setPiece(new Piece_Knight("W_Knight2", Team.WHITE, new Position(7, 6)));
        fields[7][7].setPiece(new Piece_Rook("W_Rook2", Team.WHITE, new Position(7, 7)));
    }

    public void setCanMoveAll() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece != null) {
                    piece.setCanMovePositions();
                }
            }
        }
    }

    public void checkWin() {
        int canMove = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece != null && piece.getTeam() == ChessGame.getTurn()) {
                    canMove = piece.getIsCanMove() ? canMove + 1 : canMove;
                }
            }
        }
        if (canMove == 0) {
            if (this.getKing(ChessGame.getTurn()).getIsChecked()) {
                ChessGame.getGui().getGamePlayPanel()
                        .updateAlertText(ChessGame.getTurn() == Team.BLACK ? "WHITE WIN." : "BLACK WIN.");
                JOptionPane.showMessageDialog(ChessGame.getGui(),
                        (ChessGame.getTurn() == Team.BLACK ? "WHITE WIN." : "BLACK WIN.") + " By Checkmate!");
                ChessGame.gameEnd();
            } else {
                ChessGame.getGui().getGamePlayPanel().updateAlertText("DRAW");

                JOptionPane.showMessageDialog(ChessGame.getGui(), "DRAW");
                ChessGame.gameEnd();
            }
        } else {
            if (ChessGame.getBlackSecondsRemaining() == 0) {
                ChessGame.getGui().getGamePlayPanel().updateAlertText("WHITE WIN.");

                JOptionPane.showMessageDialog(ChessGame.getGui(), "WHITE WIN. By Timeout");
                ChessGame.gameEnd();
            }
            if (ChessGame.getWhiteSecondsRemaining() == 0) {
                ChessGame.getGui().getGamePlayPanel().updateAlertText("BLACK WIN.");

                JOptionPane.showMessageDialog(ChessGame.getGui(), "BLACK WIN. By Timeout");
                ChessGame.gameEnd();
            }
        }
    }

}
