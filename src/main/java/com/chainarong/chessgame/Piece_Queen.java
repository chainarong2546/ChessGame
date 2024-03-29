package com.chainarong.chessgame;

class Piece_Queen extends Piece {

    public Piece_Queen(String name, Team team, Position position) {
        super(name, team, position, false, team == Team.BLACK ? "/images/Black_Queen.png" : "/images/White_Queen.png");
    }

    @Override
    public void setCanMovePositions() {

        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();

        Position[][] movePatterns = { {
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
        }, {
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

        this.getCanMovePositions().clear();
        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(null);

        for (Position[] pattern : movePatterns) {
            for (Position p : pattern) {
                if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                    Piece pieceTemp = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                    if (pieceTemp == null) {
                        ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(this);
                        if (!ChessGame.getGui().getGamePlayPanel().getBoard().getKing(this.getTeam()).cCheck()) {
                            this.getCanMovePositions().add(p);
                        }
                        ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(null);

                    } else {
                        if (pieceTemp.getTeam() != this.getTeam()) {
                            ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(this);
                            if (!ChessGame.getGui().getGamePlayPanel().getBoard().getKing(this.getTeam()).cCheck()) {
                                this.getCanMovePositions().add(p);
                            }
                            ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(pieceTemp);
                        }
                        break;
                    }
                }
            }
        }
        
        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(this);
        this.setIsCanMove(!this.getCanMovePositions().isEmpty());
    }

}
