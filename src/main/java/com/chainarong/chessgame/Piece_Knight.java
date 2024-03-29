package com.chainarong.chessgame;

class Piece_Knight extends Piece {

    public Piece_Knight(String name, Team team, Position position) {
        super(name, team, position, false, team == Team.BLACK ? "/images/Black_Knight.png" : "/images/White_Knight.png");
    }

    @Override
    public void setCanMovePositions() {

        int row = this.getPosition().getRow();
        int col = this.getPosition().getCol();

        Position[] movePatterns = {
                new Position(row + 1, col - 2),
                new Position(row + 2, col - 1),
                new Position(row + 2, col + 1),
                new Position(row + 1, col + 2),
                new Position(row - 1, col + 2),
                new Position(row - 2, col + 1),
                new Position(row - 2, col - 1),
                new Position(row - 1, col - 2)
        };

        this.getCanMovePositions().clear();
        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(null);

        for (Position p : movePatterns) {
            if ((p.getRow() < 8) && (p.getRow() >= 0) && (p.getCol() < 8) && (p.getCol() >= 0)) {
                if ((ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p) == null) || (ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p).getTeam() != this.getTeam())) {
                    Piece pieceTemp = ChessGame.getGui().getGamePlayPanel().getBoard().getPiece(p);
                    ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(this);
                    if (!ChessGame.getGui().getGamePlayPanel().getBoard().getKing(this.getTeam()).cCheck()) {
                        this.getCanMovePositions().add(p);
                    }
                    ChessGame.getGui().getGamePlayPanel().getBoard().getField(p).setPiece(pieceTemp);
                }
            }
        }
        
        ChessGame.getGui().getGamePlayPanel().getBoard().getField(this.getPosition()).setPiece(this);
        this.setIsCanMove(!this.getCanMovePositions().isEmpty());
    }

}
