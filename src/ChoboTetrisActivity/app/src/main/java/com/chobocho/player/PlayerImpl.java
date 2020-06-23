package com.chobocho.player;

import android.content.Intent;

import com.chobocho.tetris.*;

public class PlayerImpl implements Player, ITetrisObserver {
    private ITetris tetris;
    private PlayerObserver gameViewObserver = null;

    public PlayerImpl(int width, int height) {
        tetris = new Tetris(width, height);
        tetris.register(this);
    }

    public void init() {
        tetris.init();
    }

    public int getWidth() {
        return tetris.getWidth();
    }

    public int getHeight() {
        return tetris.getHeight();
    }

    public int[][] getBoard() {
        return tetris.getBoard();
    }

    public int getScore() {
        return tetris.getScore();
    }

    public int getHighScore() {
        return tetris.getHighScore();
    }

    public int getRemovedLineCount() {
        return tetris.getRemovedLineCount();
    }

    public boolean setInputDevice(PlayerInput playerInput) {
        playerInput.registerPlayer(this);
        return true;
    }

    public boolean setView(PlayerUI pu) {
        pu.registerPlayer(this);
        return true;
    }

    public boolean setSCore(Score score) {
        return tetris.setScore(score);
    }


    public boolean MoveLeft() {
        if (tetris == null) {
            return false;
        }
        tetris.moveLeft();
        return true;
    }

    public boolean MoveRight() {
        if (tetris == null) {
            return false;
        }
        tetris.moveRight();
        return true;
    }

    public boolean MoveDown() {
        if (tetris == null) {
            return false;
        }
        tetris.moveDown();
        return true;
    }

    public boolean rotate() {
        if (tetris == null) {
            return false;
        }

        if (tetris.isPauseState()) {
            if (tetris.isEnableShadow()) {
                tetris.disableShadow();
            } else {
                tetris.enableShadow();
            }
            return true;
        }

        tetris.rotate();
        return true;
    }

    public boolean MoveBottom() {
        if (tetris == null) {
            return false;
        }
        tetris.moveBottom();
        return true;
    }

    public boolean clickStartButton() {
        if (tetris == null) {
            return false;
        }

        if (tetris.isIdleState()) {
            tetris.play();
            gameViewObserver.startGame();
            return true;
        }

        if (tetris.isGameOverState()) {
//            tetris.init();
//            return true;
            TetrisLog.d("DDDDDDDDDDDDDDDDDDDD");
            return true;
        }

        if (tetris.isPauseState()) {
            tetris.resume();
            gameViewObserver.startGame();
            return true;
        }

        return false;
    }

    public boolean play() {
        if (tetris == null) {
            return false;
        }

        if (tetris.isIdleState()) {
            tetris.play();
            gameViewObserver.startGame();
            return true;
        }

        if (tetris.isGameOverState()) {
            tetris.init();
            return true;
        }

        if (tetris.isPauseState()) {
            tetris.resume();
            gameViewObserver.startGame();
            return true;
        }

        if (tetris.isPlayState()) {
            tetris.pause();
            return true;
        }

        return false;
    }

    public boolean pause() {
        boolean isPlayState = (tetris != null  &&  tetris.isPlayState());

        if (!isPlayState) {
            return false;
        }

        tetris.pause();
        return true;
    }

    public boolean isIdleState() { return tetris.isIdleState(); }
    public boolean isGameOverState() { return tetris.isGameOverState(); }
    public boolean isPlayState() { return tetris.isPlayState(); }
    public boolean isPauseState() { return tetris.isPauseState(); }
    public boolean isEnableShadow() { return tetris.isEnableShadow(); }

    public Tetrominos getCurrentBlock() {
        return tetris.getCurrentBlock();
    }
    public Tetrominos getNextBlock() {
        return tetris.getNextBlock();
    }
    public Tetrominos getShadowBlock() {
        return tetris.getShadowBlock();
    }

    @Override
    public void register(PlayerObserver observer) {
        gameViewObserver = observer;
    }

    @Override
    public void update() {
        gameViewObserver.update();
    }
}
