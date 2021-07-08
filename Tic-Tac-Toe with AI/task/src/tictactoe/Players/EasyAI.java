package tictactoe.Players;

import tictactoe.Characters;

import java.util.Random;

public class EasyAI extends Player {
    public EasyAI(char[][] grid, char character) {
        // Constructor of super class player
        super(grid, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");

        // Makes a random move
        randomMove(super.getGrid(), super.getCharacter());
    }

    /**
     * Makes a random move at a empty spot
     *
     * @param grid Game board
     * @param character Player character
     */
    protected void randomMove(char[][] grid, char character) {
        while (true) {
            Random random = new Random();

            // Generates a random number for row and column
            int row = random.nextInt(grid.length);
            int column = random.nextInt(grid.length);

            if (grid[row][column] == Characters.Empty.getCharacter()) {
                // Places a character there if the position is empty
                grid[row][column] = character;
                break;
            }
        }
    }
}

