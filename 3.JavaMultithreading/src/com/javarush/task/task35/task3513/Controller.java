package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private static final int WINNING_TILE = 2048;
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public View getView() {
        return view;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();

        if (!model.canMove()) {
            model.isGameEnd = true;
            view.showGameOverDialog();
        }
        if (model.maxTile == WINNING_TILE) {
            model.isGameEnd = true;
            view.showWonDialog();
        }

        if (!model.isGameEnd) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
                case KeyEvent.VK_Z:
                    model.rollback();
                    break;
                case KeyEvent.VK_R:
                    model.randomMove();
                    break;
                case KeyEvent.VK_A:
                    model.autoMove();
                    break;
            }
        }

        view.repaint();
    }

    Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    int getScore() {
        return model.score;
    }

    void resetGame() {
        model.score = 0;
        model.isGameEnd = false;
        model.resetGameTiles();
        model.resetUndoStacks();
    }


}
