package com.chainarong.chessgame;

import java.util.ArrayList;

abstract class Piece {

    private String name;
    private Team team;
    private Position position;
    private int moveCount;
    private boolean isKing;
    private boolean isCanMove;
    private ArrayList<Position> canMovePositions;
    private String image;

    protected Piece(String name, Team team, Position position, boolean isKing, String image) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.moveCount = 0;
        this.isKing = isKing;
        this.isCanMove = false;
        this.canMovePositions = new ArrayList<>();
        this.image = image;
    }

    protected String getName() {
        return this.name;
    }

    protected Team getTeam() {
        return this.team;
    }

    protected Position getPosition() {
        return this.position;
    }

    protected int getMoveCount() {
        return this.moveCount;
    }

    protected boolean getIsKing() {
        return this.isKing;
    }

    protected boolean getIsCanMove() {
        return this.isCanMove;
    }

    protected ArrayList<Position> getCanMovePositions() {
        return this.canMovePositions;
    }

    protected String getImage() {
        return this.image;
    }

    protected void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }

    protected void setIsCanMove(boolean isCanMove) {
        this.isCanMove = isCanMove;
    }

    protected boolean move(Position position) {
        if ((position.getRow() < 8) && (position.getRow() >= 0) && (position.getCol() < 8) && (position.getCol() >= 0)) {
            this.position = position;
            moveCount++;
            return true;
        }
        return false;
    }

    abstract protected void setCanMovePositions();

}