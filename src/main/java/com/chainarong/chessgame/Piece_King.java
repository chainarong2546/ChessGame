package com.chainarong.chessgame;

class Piece_King extends Piece {

    private boolean isChecked;

    public Piece_King(String name, Team team, Position position) {
        super(name, team, position, true, team == Team.BLACK ? "/images/Black_King.png" : "/images/White_King.png");
    }

    @Override
    public void setCanMovePositions() {
        this.isChecked = cCheck();

        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();

        Position[] movePatterns = {
                new Position(row + 1, col - 1),
                new Position(row + 1, col),
                new Position(row + 1, col + 1),
                new Position(row, col - 1),
                new Position(row, col + 1),
                new Position(row - 1, col - 1),
                new Position(row - 1, col),
                new Position(row - 1, col + 1)
        };

        this.getCanMovePositions().clear();
        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(null);

        for (Position p : movePatterns) {
            if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                Piece pieceTemp = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if ((pieceTemp == null) || (pieceTemp.getTeam() != this.getTeam())) {
                    ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(this);
                    this.setPosition(p);
                    if (!cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    this.setPosition(new Position(row, col));
                    ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(pieceTemp);
                }
            }
        }

        if ((this.getMoveCount() == 0) && !this.isChecked) {
            Board x = ChessGame.getGui().getGamePlayPanel().getBoard();
            Piece piece;
            Position pp;

            pp = new Position(row, 0);
            piece = x.getPiece(pp);
            if ((piece != null) && (piece.getClass().getSimpleName().equals("Piece_Rook"))
                    && (piece.getMoveCount() == 0)) {
                if ((x.getPiece(new Position(row, 1)) == null) && (x.getPiece(new Position(row, 2)) == null)
                        && (x.getPiece(new Position(row, 3)) == null)) {

                    x.getField(new Position(row, 3)).setPiece(this);
                    this.setPosition(new Position(row, 3));
                    if (!cCheck()) {
                        x.getField(this.getPosition()).setPiece(null);
                        x.getField(new Position(row, 2)).setPiece(this);
                        this.setPosition(new Position(row, 2));
                        if (!cCheck()) {
                            this.getCanMovePositions().add(pp);
                        }
                        this.setPosition(new Position(row, col));
                        x.getField(new Position(row, 2)).setPiece(null);
                    }
                    this.setPosition(new Position(row, col));
                    x.getField(new Position(row, 3)).setPiece(null);

                }
            }

            pp = new Position(row, 7);
            piece = x.getPiece(pp);
            if ((piece != null) && (piece.getClass().getSimpleName().equals("Piece_Rook"))
                    && (piece.getMoveCount() == 0)) {
                if ((x.getPiece(new Position(row, 5)) == null) && (x.getPiece(new Position(row, 6)) == null)) {

                    x.getField(new Position(row, 5)).setPiece(this);
                    this.setPosition(new Position(row, 5));
                    if (!cCheck()) {
                        x.getField(this.getPosition()).setPiece(null);
                        x.getField(new Position(row, 6)).setPiece(this);
                        this.setPosition(new Position(row, 6));
                        if (!cCheck()) {
                            this.getCanMovePositions().add(pp);
                        }
                        this.setPosition(new Position(row, col));
                        x.getField(new Position(row, 6)).setPiece(null);
                    }
                    this.setPosition(new Position(row, col));
                    x.getField(new Position(row, 5)).setPiece(null);

                }
            }
        }

        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(this);
        this.setIsCanMove(!this.getCanMovePositions().isEmpty());
    }

    public boolean cCheck() {

        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();

        Position[][] movePatternsRook = { {
                new Position(row + 1, col),
                new Position(row + 2, col),
                new Position(row + 3, col),
                new Position(row + 4, col),
                new Position(row + 5, col),
                new Position(row + 6, col),
                new Position(row + 7, col),
        }, {
                new Position(row - 1, col),
                new Position(row - 2, col),
                new Position(row - 3, col),
                new Position(row - 4, col),
                new Position(row - 5, col),
                new Position(row - 6, col),
                new Position(row - 7, col),
        }, {
                new Position(row, col + 1),
                new Position(row, col + 2),
                new Position(row, col + 3),
                new Position(row, col + 4),
                new Position(row, col + 5),
                new Position(row, col + 6),
                new Position(row, col + 7),
        }, {
                new Position(row, col - 1),
                new Position(row, col - 2),
                new Position(row, col - 3),
                new Position(row, col - 4),
                new Position(row, col - 5),
                new Position(row, col - 6),
                new Position(row, col - 7)
        } };
        Position[][] movePatternsBishop = { {
                new Position(row + 1, col - 1),
                new Position(row + 2, col - 2),
                new Position(row + 3, col - 3),
                new Position(row + 4, col - 4),
                new Position(row + 5, col - 5),
                new Position(row + 6, col - 6),
                new Position(row + 7, col - 7),
        }, {
                new Position(row + 1, col + 1),
                new Position(row + 2, col + 2),
                new Position(row + 3, col + 3),
                new Position(row + 4, col + 4),
                new Position(row + 5, col + 5),
                new Position(row + 6, col + 6),
                new Position(row + 7, col + 7),
        }, {
                new Position(row - 1, col + 1),
                new Position(row - 2, col + 2),
                new Position(row - 3, col + 3),
                new Position(row - 4, col + 4),
                new Position(row - 5, col + 5),
                new Position(row - 6, col + 6),
                new Position(row - 7, col + 7),
        }, {
                new Position(row - 1, col - 1),
                new Position(row - 2, col - 2),
                new Position(row - 3, col - 3),
                new Position(row - 4, col - 4),
                new Position(row - 5, col - 5),
                new Position(row - 6, col - 6),
                new Position(row - 7, col - 7),
        } };
        Position[] movePatternsKnight = {
                new Position(row + 1, col - 2),
                new Position(row + 2, col - 1),
                new Position(row + 2, col + 1),
                new Position(row + 1, col + 2),
                new Position(row - 1, col + 2),
                new Position(row - 2, col + 1),
                new Position(row - 2, col - 1),
                new Position(row - 1, col - 2)
        };
        Position[] movePatternsKing = {
                new Position(row + 1, col - 1),
                new Position(row + 1, col),
                new Position(row + 1, col + 1),
                new Position(row, col - 1),
                new Position(row, col + 1),
                new Position(row - 1, col - 1),
                new Position(row - 1, col),
                new Position(row - 1, col + 1)
        };

        for (Position[] movePattern : movePatternsRook) {
            for (Position p : movePattern) {
                if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                    Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                    if (piece != null) {
                        if (piece.getTeam() != this.getTeam()) {
                            if (piece.getClass().getSimpleName().equals("Piece_Rook")
                                    || piece.getClass().getSimpleName().equals("Piece_Queen")) {
                                return true;
                            }
                        }
                        break;
                    }
                }
            }
        }
        for (Position[] movePattern : movePatternsBishop) {
            for (Position p : movePattern) {
                if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                    Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                    if (piece != null) {
                        if (piece.getTeam() != this.getTeam()) {
                            if (piece.getClass().getSimpleName().equals("Piece_Bishop")
                                    || piece.getClass().getSimpleName().equals("Piece_Queen")) {
                                return true;
                            }
                        }
                        break;
                    }
                }
            }
        }
        for (Position p : movePatternsKnight) {
            if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if (piece != null) {
                    if (piece.getTeam() != this.getTeam()) {
                        if (piece.getClass().getSimpleName().equals("Piece_Knight")) {
                            return true;
                        }
                    }
                }
            }
        }
        for (Position p : movePatternsKing) {
            if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if (piece != null) {
                    if (piece.getTeam() != this.getTeam()) {
                        if (piece.getClass().getSimpleName().equals("Piece_King")) {
                            return true;
                        }
                    }
                }
            }
        }

        if (this.getTeam() == Team.BLACK) {
            if ((row + 1 < 8) && (col - 1 >= 0)) {
                Position p = new Position(row + 1, col - 1);
                Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if ((piece != null) && (piece.getTeam() != this.getTeam())
                        && (piece.getClass().getSimpleName().equals("Piece_Pawn"))) {
                    return true;
                }
            }
            if ((row + 1 < 8) && (col + 1 < 8)) {
                Position p = new Position(row + 1, col + 1);
                Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if ((piece != null) && (piece.getTeam() != this.getTeam())
                        && (piece.getClass().getSimpleName().equals("Piece_Pawn"))) {
                    return true;
                }
            }
        } else {
            if ((row - 1 < 8) && (col - 1 >= 0)) {
                Position p = new Position(row - 1, col - 1);
                Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if ((piece != null) && (piece.getTeam() != this.getTeam())
                        && (piece.getClass().getSimpleName().equals("Piece_Pawn"))) {
                    return true;
                }
            }
            if ((row - 1 < 8) && (col + 1 < 8)) {
                Position p = new Position(row - 1, col + 1);
                Piece piece = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                if ((piece != null) && (piece.getTeam() != this.getTeam())
                        && (piece.getClass().getSimpleName().equals("Piece_Pawn"))) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

}
