package tictactoe.Players;

import tictactoe.Characters;
import tictactoe.TicTacToe;

public class MediumAI extends EasyAI {
    public MediumAI(char[][] grid, char character) {
        // Constructor of super class Easy AI
        super(grid, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"medium\"");

        if (canBlockOrComplete(super.getGrid(), super.getCharacter())) {
            // block or complete if can block or complete
            blockOrComplete(super.getGrid(), super.getCharacter());
        } else {
            // If can't block or complete make a random move
            super.randomMove(super.getGrid(), super.getCharacter());
        }
    }

    /**
     * Checks can block or complete
     *
     * @param grid Game board
     * @param character Player character
     * @return a boolean determining can block or complete or can't
     */
    protected boolean canBlockOrComplete(char[][] grid, char character) {
        // Makes a clone of the Game board
        char[][] cloneGrid = TicTacToe.cloneGrid(grid);

        // Opponent's character
        char opponentCharacter = TicTacToe.opponentCharacter(character);

        for (int i = 0; i < cloneGrid.length; i++) {
            for (int j = 0; j < cloneGrid.length; j++) {
                if (cloneGrid[i][j] == Characters.Empty.getCharacter()) {
                    // Adds own character and checks
                    cloneGrid[i][j] = character;
                    if (!(TicTacToe.winner(cloneGrid).equals("Draw"))) { // If it is true then it is a complete
                        return true;
                    }

                    // Adds opponent's character and checks
                    cloneGrid[i][j] = opponentCharacter;
                    if (!(TicTacToe.winner(cloneGrid).equals("Draw"))) { // If it is true then it is a block
                        return true;
                    }

                    // Back track the move
                    cloneGrid[i][j] = Characters.Empty.getCharacter();
                }
            }
        }

        return false;
    }

    /**
     * Block or complete
     *
     * @param grid Game board
     * @param character Player character
     */
    protected void blockOrComplete(char[][] grid, char character) {
        char opponentCharacter = TicTacToe.opponentCharacter(character);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == Characters.Empty.getCharacter()) {
                    grid[i][j] = character;
                    if (TicTacToe.winner(grid).equals("Draw")) {
                        grid[i][j] = Characters.Empty.getCharacter();
                    } else {
                        return;
                    }

                    grid[i][j] = opponentCharacter;
                    if (TicTacToe.winner(grid).equals("Draw")) {
                        grid[i][j] = Characters.Empty.getCharacter();
                    } else {
                        grid[i][j] = character;
                        return;
                    }
                }
            }
        }
    }
}
