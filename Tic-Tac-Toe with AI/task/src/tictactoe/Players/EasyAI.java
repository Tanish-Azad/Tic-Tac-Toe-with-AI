package tictactoe.Players;

import tictactoe.TicTacToe;

import java.util.Random;

public class EasyAI extends Player {
    public EasyAI(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");

        this.randomMove(super.getGrid(), super.getCharacter());
    }

    protected void randomMove(char[][] grid, char character) {
        while (true) {
            Random random = new Random();

            int row = random.nextInt(grid.length);
            int column = random.nextInt(grid.length);

            if (!TicTacToe.isOccupiedCoordinate(grid, row, column)) {
                grid[row][column] = character;
                break;
            }
        }
    }
}

