package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 4;
    int score;
    int maxTile;
    boolean isGameEnd = false;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private Tile[][] gameTiles;
    private boolean isSaveNeeded = true;


    public Model() {
        resetGameTiles();
    }

    private void saveState(Tile[][] gameTiles) {
        Tile[][] state = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int y = 0; y < state.length; y++) {
            for (int x = 0; x < state.length; x++) {
                state[y][x] = new Tile(gameTiles[y][x].value);
            }

        }
        previousStates.push(state);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty())
            gameTiles = previousStates.pop();

        if (!previousScores.empty())
            score = previousScores.pop();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            int randomTile = (int) (emptyTiles.size() * Math.random());

            emptyTiles.get(randomTile).value = (Math.random() < 0.9) ? 2 : 4;
        }

    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (gameTiles[y][x].isEmpty())
                    result.add(gameTiles[y][x]);
            }
        }
        return result;
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                gameTiles[y][x] = new Tile();
            }
        }

        score = 0;
        maxTile = 0;

        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean changed = false;

        for (int j = 0; j < tiles.length - 1; j++) {
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].value == 0) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                    if (tiles[i].value != 0)
                        changed = true;
                }
            }
        }
        return changed;
    }

    Tile[][] getGameTiles() {
        return gameTiles;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changed = false;

        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value != 0 && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1].value = 0;

                score += tiles[i].value;
                if (tiles[i].value > maxTile)
                    maxTile = tiles[i].value;

                compressTiles(tiles);
                changed = true;
            }
        }
        return changed;
    }

    private void rotate90() {
        // Consider all squares one by one
        int max = gameTiles.length;

        for (int y = 0; y < max / 2; y++) {
            for (int x = y; x < max - y - 1; x++) {
                // store current cell in temp variable
                int temp = gameTiles[y][x].value;
                // move values from right to top
                gameTiles[y][x].value = gameTiles[max - 1 - x][y].value;
                // move values from bottom to right
                gameTiles[max - 1 - x][y].value = gameTiles[max - 1 - y][max - 1 - x].value;
                // move values from left to bottom
                gameTiles[max - 1 - y][max - 1 - x].value = gameTiles[x][max - 1 - y].value;
                // assign temp to left
                gameTiles[x][max - 1 - y].value = temp;
            }
        }
    }

    private void rotate180() {
        rotate90();
        rotate90();
    }

    private void rotate270() {
        rotate90();
        rotate90();
        rotate90();
    }

    void left() {
        if (isSaveNeeded)
            saveState(gameTiles);

        boolean changed = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                changed = true;
        }
        if (changed)
            addTile();

        isSaveNeeded = true;
    }

    void down() {
        saveState(gameTiles);
        rotate90();
        left();
        rotate270();


    }

    void right() {
        saveState(gameTiles);
        rotate180();
        left();
        rotate180();

    }

    void up() {
        saveState(gameTiles);
        rotate270();
        left();
        rotate90();
    }

    boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;

        for (int y = 0; y < gameTiles.length; y++) {
            for (int x = 0; x < gameTiles.length - 1; x++) {
                if (gameTiles[y][x].value == gameTiles[y][x + 1].value)
                    return true;
            }
        }
        for (int x = 0; x < gameTiles.length; x++) {
            for (int y = 0; y < gameTiles.length - 1; y++) {
                if (gameTiles[y][x].value == gameTiles[y + 1][x].value)
                    return true;
            }
        }
        return false;
    }

    void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
        }
    }

    private boolean hasBoardChanged() {
        int boardValue = 0;
        int stackValue = 0;
        Tile[][] stack = previousStates.peek();

        for (int y = 0; y < gameTiles.length; y++) {
            for (int x = 0; x < gameTiles.length; x++) {
                boardValue += gameTiles[y][x].value;
                stackValue += stack[y][x].value;
            }
        }

        return (boardValue != stackValue) ? true : false;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency result;
        if (hasBoardChanged())
            result = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else
            result = new MoveEfficiency(-1, 0, move);
        rollback();
        return result;

    }

    void autoMove() {
        PriorityQueue<MoveEfficiency> moves = new PriorityQueue<>(4, Collections.reverseOrder());
        moves.offer(getMoveEfficiency(this::left));
        moves.offer(getMoveEfficiency(this::right));
        moves.offer(getMoveEfficiency(this::down));
        moves.offer(getMoveEfficiency(this::up));

        moves.poll().getMove().move();
    }

    public void resetUndoStacks() {
        previousStates = new Stack<>();
        previousScores = new Stack<>();
    }
}