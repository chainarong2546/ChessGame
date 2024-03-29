package com.chainarong.chessgame;

class Piece_Pawn extends Piece {

    public Piece_Pawn(String name, Team team, Position position) {
        super(name, team, position, false, team == Team.BLACK ? "/images/Black_Pawn.png" : "/images/White_Pawn.png");
    }

    @Override
    public void setCanMovePositions() {

        this.getCanMovePositions().clear();

        Board board = ChessGame.getGui().getGamePlayPanel().getBoard();
        Position pp = this.getPosition();
        int row = pp.getRow();
        int col = pp.getCol();

        board.getField(pp).setPiece(null);
        if (this.getTeam() == Team.BLACK) {
            if (row + 1 < 8) {
                Position p = new Position(row + 1, col);
                if (board.getPiece(p) == null) {
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(null);
                }
            }
            if ((row + 2 < 8) && (this.getMoveCount() == 0)) {
                Position p = new Position(row + 2, col);
                if (board.getPiece(p) == null) {
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(null);
                }
            }
            if ((row + 1 < 8) && (col - 1 >= 0)) {
                Position p = new Position(row + 1, col - 1);
                if ((board.getPiece(p) != null)
                        && (board.getPiece(p).getTeam() != this.getTeam())) {
                    Piece pieceTemp = board.getPiece(p);
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(pieceTemp);
                } else {
                    if ((board.getLastMoveTo() != null) && (board.getLastMoveTo().equals(new Position(row, col - 1)))
                            && (board.getLastMoveFrom() != null)
                            && (board.getLastMoveFrom().equals(new Position(row + 1, col - 1))
                                    || board.getLastMoveFrom().equals(new Position(row + 2, col - 1)))) {
                        Piece x = board.getPiece(new Position(row, col - 1));
                        if ((x != null) && (x.getClass().getSimpleName().equals("Piece_Pawn"))
                                && (x.getMoveCount() == 1)) {
                            this.getCanMovePositions().add(p);
                        }
                    }
                }
            }
            if ((row + 1 < 8) && (col + 1 < 8)) {
                Position p = new Position(row + 1, col + 1);
                if ((board.getPiece(p) != null)
                        && (board.getPiece(p).getTeam() != this.getTeam())) {
                    Piece pieceTemp = board.getPiece(p);
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(pieceTemp);
                } else {
                    if ((board.getLastMoveTo() != null) && (board.getLastMoveTo().equals(new Position(row, col + 1)))
                            && (board.getLastMoveFrom() != null)
                            && (board.getLastMoveFrom().equals(new Position(row + 1, col + 1))
                                    || board.getLastMoveFrom().equals(new Position(row + 2, col + 1)))) {
                        Piece x = board.getPiece(new Position(row, col + 1));
                        if ((x != null) && (x.getClass().getSimpleName().equals("Piece_Pawn"))
                                && (x.getMoveCount() == 1)) {
                            this.getCanMovePositions().add(p);
                        }
                    }
                }
            }

        } else {
            if (row - 1 >= 0) {
                Position p = new Position(row - 1, col);
                if (board.getPiece(p) == null) {
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(null);
                }
            }
            if ((row - 2 >= 0) && (this.getMoveCount() == 0)) {
                Position p = new Position(row - 2, col);
                if (board.getPiece(p) == null) {
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(null);
                }
            }
            if ((row - 1 >= 0) && (col - 1 >= 0)) {
                Position p = new Position(row - 1, col - 1);
                if ((board.getPiece(p) != null)
                        && (board.getPiece(p).getTeam() != this.getTeam())) {
                    Piece pieceTemp = board.getPiece(p);
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(pieceTemp);
                } else {
                    if ((board.getLastMoveTo() != null) && (board.getLastMoveTo().equals(new Position(row, col - 1)))
                            && (board.getLastMoveFrom() != null)
                            && (board.getLastMoveFrom().equals(new Position(row - 1, col - 1))
                                    || board.getLastMoveFrom().equals(new Position(row - 2, col - 1)))) {
                        Piece x = board.getPiece(new Position(row, col - 1));
                        if ((x != null) && (x.getClass().getSimpleName().equals("Piece_Pawn"))
                                && (x.getMoveCount() == 1)) {
                            this.getCanMovePositions().add(p);
                        }
                    }
                }
            }
            if ((row - 1 >= 0) && (col + 1 < 8)) {
                Position p = new Position(row - 1, col + 1);
                if ((board.getPiece(p) != null)
                        && (board.getPiece(p).getTeam() != this.getTeam())) {
                    Piece pieceTemp = board.getPiece(p);
                    board.getField(p).setPiece(this);
                    if (!board.getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    board.getField(p).setPiece(pieceTemp);
                } else {
                    if ((board.getLastMoveTo() != null) && (board.getLastMoveTo().equals(new Position(row, col + 1)))
                            && (board.getLastMoveFrom() != null)
                            && (board.getLastMoveFrom().equals(new Position(row - 1, col + 1))
                                    || board.getLastMoveFrom().equals(new Position(row - 2, col + 1)))) {
                        Piece x = board.getPiece(new Position(row, col + 1));
                        if ((x != null) && (x.getClass().getSimpleName().equals("Piece_Pawn"))
                                && (x.getMoveCount() == 1)) {
                            this.getCanMovePositions().add(p);
                        }
                    }
                }
            }
        }

        board.getField(this.getPosition()).setPiece(this);
        this.setIsCanMove(!this.getCanMovePositions().isEmpty());

    }

    public void promotion(Promotion newPiece) {
        Piece x;
        switch (newPiece) {
            case Promotion.QUEEN:
                x = new Piece_Queen(this.getName(), this.getTeam(), this.getPosition());
                break;
            case Promotion.ROOK:
                x = new Piece_Rook(this.getName(), this.getTeam(), this.getPosition());
                break;
            case Promotion.BISHOP:
                x = new Piece_Bishop(this.getName(), this.getTeam(), this.getPosition());
                break;
            case Promotion.KNIGHT:
                x = new Piece_Knight(this.getName(), this.getTeam(), this.getPosition());
                break;
            default:
                x = new Piece_Queen(this.getName(), this.getTeam(), this.getPosition());
                break;
        }

        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(x);
        ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(this.getPosition()).setMoveCount(this.getMoveCount());

    }

}
